package tr.gov.gib.borc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "mukellef_borc", schema = "gsths")
public class MukellefBorc {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mukellef_borc_id_gen")
    @SequenceGenerator(name = "mukellef_borc_id_gen", sequenceName = "mukellef_borc_mukellef_borc_id_seq", allocationSize = 1)
    @Column(name = "mukellef_borc_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vergi_id", nullable = false)
    private Vergi vergi;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mukellef_kullanici_id", nullable = false)
    private MukellefKullanici mukellefKullanici;

    @NotNull
    @Column(name = "mukellef_borc", nullable = false, precision = 12, scale = 2)
    private BigDecimal mukellefBorc;

    @NotNull
    @Column(name = "odenen_borc", nullable = false, precision = 12, scale = 2)
    private BigDecimal odenenBorc;

}