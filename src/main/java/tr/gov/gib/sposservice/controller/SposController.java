package tr.gov.gib.sposservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tr.gov.gib.gibcore.exception.GibExceptionHandler;
import tr.gov.gib.gibcore.object.response.GibResponse;
import tr.gov.gib.gibcore.object.request.GibRequest;
import tr.gov.gib.sposservice.object.request.SposRequest;
import tr.gov.gib.sposservice.object.response.SposResponse;
import tr.gov.gib.sposservice.service.SposService;


@RestController
@RequiredArgsConstructor
public class SposController extends GibExceptionHandler {

    private final SposService sposService;

    @RequestMapping(path = "/sposOdemeYap", method = RequestMethod.POST)
    public GibResponse<SposResponse> sposOdemeYap(@RequestBody GibRequest<SposRequest> request) {
        return sposService.processPayment(request);
    }
}
