package tr.gov.gib.sposservice.object.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SposResponse implements Serializable {
    private String oid;             // Unique identifier for the payment
    private Integer odemeOid;       // Odeme tablosundan referans alıyor
    private Short durum;            // Ödeme durumu
    private String bankaAdi;
}
