package tr.gov.gib.borc.object.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorcSorguReponse {
    private Integer borcId;
    private Integer vergiId;
    private String vergiAdi;
    private String mukellef;
    private String tckn;
    private BigDecimal borc;
    private Character odemeTur;
    private String odemeTurDsc;
}
