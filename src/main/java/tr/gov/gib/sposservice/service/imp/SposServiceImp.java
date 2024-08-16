package tr.gov.gib.sposservice.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tr.gov.gib.gibcore.exception.GibException;
import tr.gov.gib.gibcore.object.response.GibResponse;
import tr.gov.gib.gibcore.object.reuest.GibRequest;
import tr.gov.gib.gibcore.util.ServiceMessage;
import tr.gov.gib.gibcore.util.enums.SanalPosDurum;
import tr.gov.gib.sposservice.entity.SanalPos;
import tr.gov.gib.sposservice.object.request.SposRequest;
import tr.gov.gib.sposservice.object.reponse.SposResponse;
import tr.gov.gib.sposservice.object.reponse.BankaServerResponse;
import tr.gov.gib.sposservice.object.request.BankaServerRequest;
import tr.gov.gib.sposservice.repository.SanalPosRepository;
import tr.gov.gib.sanalpos.service.SposService;
import tr.gov.gib.gibcore.util.HashUtil;

import java.util.Date;

@Service("SposService")
@RequiredArgsConstructor
public class SposServiceImp implements SposService {

    @Value("${banka.servis.url}")
    private String bankaServisUrl;

    private final SanalPosRepository sanalPosRepository;

    @Override
    public GibResponse<SposResponse> processPayment(GibRequest<SposRequest> request) {
        SposRequest sposRequest = request.getData();

        // Hash parametreleri
        String generatedHash = HashUtil.generateSHA256(
                sposRequest.getOid(),
                sposRequest.getKartNo(),
                String.valueOf(sposRequest.getTutar())
        );

        // sanalPos oluştur
        SanalPos sanalPos = new SanalPos();
        sanalPos.setOid(sposRequest.getOid());
        sanalPos.setOdemeId(sposRequest.getOdemeOid());
        sanalPos.setKartSahibi(sposRequest.getKartSahibi());
        sanalPos.setKartBanka(sposRequest.getKartBanka());

        // bankaya istek gönder
        RestTemplate restTemplate = new RestTemplate();
        try {
            BankaServerRequest requestEntity = BankaServerRequest.builder()
                    .oid(sposRequest.getOid())
                    .odenecekTutar(sposRequest.getTutar())
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
                    sanalPos.setDurum(SanalPosDurum.HATA.getdurumKodu());
                    sanalPos.setOptime(new Date());
                    sanalPosRepository.save(sanalPos);
                    return GibResponse.<SposResponse>builder()
                            .service(ServiceMessage.FAIL)
                            .data(null)
                            .build();
                }

                // Banka yanıtına göre durum atama
                if (bankaResponse.getStatus().equals("FAILURE")) {
                    sanalPos.setDurum(SanalPosDurum.BASARISIZ.getdurumKodu());
                } else if (bankaResponse.getStatus().equals("SUCCESS")) {
                    sanalPos.setDurum(SanalPosDurum.BASARILI.getdurumKodu());
                }
            }

            sanalPos.setOptime(new Date());
            sanalPosRepository.save(sanalPos);

            SposResponse sposResponse = SposResponse.builder()
                    .oid(bankaResponse.getOid())
                    .odemeOid(sanalPos.getOdemeId())
                    .durum(SanalPosDurum.BASARILI.getdurumKodu())
                    .bankaAdi(bankaResponse.getBankaAdi())
                    .build();

            return GibResponse.<SposResponse>builder()
                    .service(ServiceMessage.OK)
                    .data(sposResponse)
                    .build();

        } catch (Throwable e) {
            sanalPos.setDurum(SanalPosDurum.HATA.getdurumKodu());
            sanalPos.setOptime(new Date());
            sanalPosRepository.save(sanalPos);
            throw new GibException(e.getMessage());
        }
    }
}