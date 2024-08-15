package tr.gov.gib.sposservice.object.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankaServerResponse {
    private String bankaAdi;
    private String message;
    private String oid;
    private Integer posId;
    private String status;
}
