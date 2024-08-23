package tr.gov.gib.sposservice.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.gov.gib.gibcore.exception.GibException;
import tr.gov.gib.gibcore.object.response.GibResponse;
import tr.gov.gib.gibcore.object.request.GibRequest;
import tr.gov.gib.gibcore.util.ServiceMessage;
import tr.gov.gib.gibcore.object.enums.FposSposNakitDurum;
import tr.gov.gib.sposservice.entity.SanalPos;
import tr.gov.gib.sposservice.object.request.SposRequest;
import tr.gov.gib.sposservice.object.response.SposResponse;
import tr.gov.gib.sposservice.object.response.BankaServerResponse;
import tr.gov.gib.sposservice.object.request.BankaServerRequest;
import tr.gov.gib.sposservice.repository.SanalPosRepository;

import tr.gov.gib.gibcore.util.HashUtil;
import tr.gov.gib.sposservice.service.SposService;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service("SposService")
@RequiredArgsConstructor
public class SposServiceImp implements SposService {

    @Value("${banka.servis.url}")
    private String bankaServisUrl;

    @Value("${banka.salt}")
    private String salt;  // Tuz değerini tanımlama

    private final SanalPosRepository sanalPosRepository;

    @Override
    public GibResponse<SposResponse> processPayment(GibRequest<SposRequest> request) {
        SposRequest sposRequest = request.getData();

        // Hash parametreleri
        String generatedHash = HashUtil.generateSHA256(
                sposRequest.getOid(),
                sposRequest.getKartNo(),
                String.valueOf(sposRequest.getOdenecekMiktar()),
                salt
        );

        // sanalPosABC oluştur
        SanalPos sanalPos = new SanalPos();
        sanalPos.setOid(sposRequest.getOid());
        sanalPos.setOdeme(sposRequest.getOdemeOid());
        sanalPos.setKartSahibi(sposRequest.getKartSahibi());
//        sanalPosABC.setKartBanka(sposRequest.getKartBanka());

        // bankaya istek gönder
        RestTemplate restTemplate = new RestTemplate();
        try {
            BankaServerRequest requestEntity = BankaServerRequest.builder()
                    .oid(sposRequest.getOid())
                    .odenecekTutar(sposRequest.getOdenecekMiktar())
                    .kartNo(sposRequest.getKartNo())
                    .ccv(sposRequest.getCcv())
                    .sonKullanimTarihiAy(sposRequest.getSonKullanimTarihiAy())
                    .sonKullanimTarihiYil(sposRequest.getSonKullanimTarihiYil())
                    .kartSahibi(sposRequest.getKartSahibi())
                    .build();
            HttpEntity<BankaServerRequest> httpEntity = new HttpEntity<>(requestEntity);

            BankaServerResponse bankaResponse = restTemplate.exchange(
                    bankaServisUrl, HttpMethod.POST, httpEntity, BankaServerResponse.class).getBody();

            // Hash doğrulama
            if (bankaResponse != null && bankaResponse.getOid() != null) {
                if (!generatedHash.equals(bankaResponse.getHash())) {
                    // Hash uyuşmazlığı
                    sanalPos.setDurum(FposSposNakitDurum.HATA_OLUSTU.getSposFposNakitDurumKodu());
                    sanalPos.setOptime(OffsetDateTime.now(ZoneId.systemDefault()));
                    sanalPosRepository.save(sanalPos);
                    return GibResponse.<SposResponse>builder()
                            .service(ServiceMessage.FAIL)
                            .data(null)
                            .build();
                }

                // Banka yanıtına göre durum atama
                if (bankaResponse.getStatus().equals("FAILURE")) {
                    sanalPos.setDurum(FposSposNakitDurum.BASARISIZ_ODEME.getSposFposNakitDurumKodu());
                } else if (bankaResponse.getStatus().equals("SUCCESS")) {
                    sanalPos.setDurum(FposSposNakitDurum.BASARILI_ODEME.getSposFposNakitDurumKodu());
                }
            }

            sanalPos.setOptime(OffsetDateTime.now(ZoneId.systemDefault()));
            sanalPos.setKartBanka(bankaResponse.getBankaAdi());
            sanalPosRepository.save(sanalPos);

            SposResponse sposResponse = SposResponse.builder()
                    .oid(bankaResponse.getOid())
                    .odemeOid(sanalPos.getOdeme())
                    .durum(FposSposNakitDurum.BASARILI_ODEME.getSposFposNakitDurumKodu())
                    .bankaAdi(bankaResponse.getBankaAdi())
                    .build();

            return GibResponse.<SposResponse>builder()
                    .service(ServiceMessage.OK)
                    .data(sposResponse)
                    .build();

        } catch (Throwable e) {
            sanalPos.setDurum(FposSposNakitDurum.HATA_OLUSTU.getSposFposNakitDurumKodu());
            sanalPos.setOptime(OffsetDateTime.now(ZoneId.systemDefault()));
            sanalPosRepository.save(sanalPos);
            throw new GibException(e.getMessage());
        }
    }
}