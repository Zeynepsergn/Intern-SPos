package tr.gov.gib.borc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "odeme_detay", schema = "gsths")
public class OdemeDetay {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odeme_detay_id_gen")
    @SequenceGenerator(name = "odeme_detay_id_gen", sequenceName = "odeme_detay_odeme_detay_id_seq", allocationSize = 1)
    @Column(name = "odeme_detay_id", nullable = false)
    private Integer id;

    @Size(max = 10)
    @NotNull
    @Column(name = "oid", nullable = false, length = 10)
    private String oid;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "odeme_id", nullable = false)
    private Odeme odeme;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vergi_id", nullable = false)
    private Vergi vergi;

    @NotNull
    @Column(name = "odenen_borc_miktari", nullable = false, precision = 12, scale = 2)
    private BigDecimal odenenBorcMiktari;

    @NotNull
    @Column(name = "odeme_detay_durum", nullable = false)
    private Integer odemeDetayDurum;

    @NotNull
    @Column(name = "iade_zamani", nullable = false)
    private OffsetDateTime iadeZamani;

    @NotNull
    @Column(name = "optime", nullable = false)
    private OffsetDateTime optime;

}