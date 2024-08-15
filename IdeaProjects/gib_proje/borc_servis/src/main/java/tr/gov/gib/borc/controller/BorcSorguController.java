package tr.gov.gib.borc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.gov.gib.borc.object.request.BorcSorguRequest;
import tr.gov.gib.borc.object.response.BorcSorguReponse;
import tr.gov.gib.borc.service.BorcSorguService;
import tr.gov.gib.gibcore.object.response.GibResponse;
import tr.gov.gib.gibcore.object.reuest.GibRequest;

import java.util.List;

@RestController
@RequestMapping("/borc")
public class BorcSorguController {

    private final BorcSorguService borcSorguService;

    public BorcSorguController(BorcSorguService borcSorguService) {
        this.borcSorguService = borcSorguService;
    }

    @PostMapping("/sorgula")
    public ResponseEntity<GibResponse<List<BorcSorguReponse>>> sorgula(@RequestBody GibRequest<BorcSorguRequest> request) {
        GibResponse<List<BorcSorguReponse>> mukellefBorcs = borcSorguService.borcSorgula(request.getData());
        return new ResponseEntity<>(mukellefBorcs, HttpStatus.OK);
    }

    @PostMapping("/tumborc-sorgula")
    public ResponseEntity<GibResponse<List<BorcSorguReponse>>> tumBorcSorgula(@RequestBody GibRequest<BorcSorguRequest> request) {
        GibResponse<List<BorcSorguReponse>> mukellefBorcs = borcSorguService.tumBorcSorgula(request.getData());
        return new ResponseEntity<>(mukellefBorcs, HttpStatus.OK);
    }

}
