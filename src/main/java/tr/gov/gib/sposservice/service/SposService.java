package tr.gov.gib.sposservice.service;

import tr.gov.gib.gibcore.object.response.GibResponse;
import tr.gov.gib.gibcore.object.request.GibRequest;
import tr.gov.gib.sposservice.object.request.SposRequest;
import tr.gov.gib.sposservice.object.response.SposResponse;

public interface SposService {
    GibResponse<SposResponse> processPayment(GibRequest<SposRequest> request);
}
