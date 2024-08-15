package tr.gov.gib.borc.service.impl;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tr.gov.gib.borc.entity.MukellefBorc;
import tr.gov.gib.borc.entity.Vergi;
import tr.gov.gib.borc.exception.GibException;
import tr.gov.gib.borc.object.enums.VergiEnum;
import tr.gov.gib.borc.object.request.BorcSorguRequest;
import tr.gov.gib.borc.object.response.BorcSorguReponse;
import tr.gov.gib.borc.repository.MukellefBorcRepository;
import tr.gov.gib.borc.repository.VergiRepository;
import tr.gov.gib.borc.service.BorcSorguService;
import tr.gov.gib.gibcore.object.response.GibResponse;
import tr.gov.gib.gibcore.util.ServiceMessage;
import java.util.ArrayList;
import java.util.List;

@Service("BorcSorguService")
public class BorcSorguServiceImpl implements BorcSorguService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorcSorguServiceImpl.class);
    private final MukellefBorcRepository mukellefBorcRepository;
    private final VergiRepository vergiRepository;

    public BorcSorguServiceImpl(MukellefBorcRepository mukellefBorcRepository, VergiRepository vergiRepository) {
        this.mukellefBorcRepository = mukellefBorcRepository;
        this.vergiRepository = vergiRepository;
    }

    @PostConstruct
    public void init(){
        List<Vergi> vergiList = vergiRepository.findAllActiveVergi();
        for(Vergi vergi : vergiList){
            VergiEnum.vergiPool.put(vergi.getId(), VergiEnum.getOdemeTur(vergi.getId()));
        }
        LOGGER.info("Vergi pool init edildi.:");
    }

    @Override
    public GibResponse<List<BorcSorguReponse>> borcSorgula(BorcSorguRequest request) throws GibException {

        try {
            List<MukellefBorc> mukellefBorcs = mukellefBorcRepository.getMukkellefBorcByVergiTur(request.getVergiId(), request.getTckn());
            List<BorcSorguReponse> responses = new ArrayList<BorcSorguReponse>();
            borcResponseOlustur(mukellefBorcs, responses);
            return GibResponse.<List<BorcSorguReponse>>builder()
                    .service(ServiceMessage.OK)
                    .data(responses)
                    .build();
        } catch (Exception e) {
            throw new GibException(ServiceMessage.NO_OK,e.getMessage());
        }
    }

    @Override
    public GibResponse<List<BorcSorguReponse>> tumBorcSorgula(BorcSorguRequest request) throws GibException {
        try {
            List<MukellefBorc> mukellefBorcs = mukellefBorcRepository.getMukkellefBorcByVergiTur(request.getTckn());
            List<BorcSorguReponse> responses = new ArrayList<>();
            borcResponseOlustur(mukellefBorcs, responses);
            return GibResponse.<List<BorcSorguReponse>>builder()
                    .service(ServiceMessage.OK)
                    .data(responses)
                    .build();
        } catch (Exception e) {
            throw new GibException(ServiceMessage.NO_OK,e.getMessage());
        }
    }

    private void borcResponseOlustur(List<MukellefBorc> mukellefBorcs,List<BorcSorguReponse> responses){
        for (MukellefBorc mukellefBorc : mukellefBorcs) {
            responses.add(
                    BorcSorguReponse.builder()
                            .borcId(mukellefBorc.getId())
                            .vergiId(mukellefBorc.getVergi().getId())
                            .vergiAdi(mukellefBorc.getVergi().getVergiAciklama())
                            .tckn(mukellefBorc.getMukellefKullanici().getTcKn())
                            .mukellef(mukellefBorc.getMukellefKullanici().getMukellefAd() + " " + mukellefBorc.getMukellefKullanici().getMukellefSoyad())
                            .borc(mukellefBorc.getMukellefBorc())
                            .odemeTur(VergiEnum.vergiPool.get(mukellefBorc.getVergi().getId()))
                            .odemeTurDsc(VergiEnum.getOdemeTurDsc(mukellefBorc.getVergi().getId()))
                            .build()
            );
        }
    }
}
