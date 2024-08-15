package tr.gov.gib.borc.object.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorcSorguRequest implements Serializable {

    private Integer vergiId;

    @NotNull
    private String tckn;
}
