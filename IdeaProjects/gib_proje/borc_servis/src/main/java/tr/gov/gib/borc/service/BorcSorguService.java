package tr.gov.gib.borc.service;

import tr.gov.gib.borc.exception.GibException;
import tr.gov.gib.borc.object.request.BorcSorguRequest;
import tr.gov.gib.borc.object.response.BorcSorguReponse;
import tr.gov.gib.gibcore.object.response.GibResponse;

import java.util.List;

public interface BorcSorguService {

    GibResponse<List<BorcSorguReponse>> borcSorgula(BorcSorguRequest request) throws GibException;

    GibResponse<List<BorcSorguReponse>> tumBorcSorgula(BorcSorguRequest request) throws GibException;
}
