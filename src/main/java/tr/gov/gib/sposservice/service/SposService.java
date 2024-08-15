package tr.gov.gib.sanalpos.service;

import tr.gov.gib.gibcore.object.response.GibResponse;
import tr.gov.gib.gibcore.object.reuest.GibRequest;
import tr.gov.gib.sposservice.object.request.SposRequest;
import tr.gov.gib.sposservice.object.reponse.SposResponse;

public interface SposService {
    GibResponse<SposResponse> processPayment(GibRequest<SposRequest> request);
}
