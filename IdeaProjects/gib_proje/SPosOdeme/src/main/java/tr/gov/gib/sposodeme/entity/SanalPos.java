package tr.gov.gib.sposodeme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "sanal_pos", schema = "gsths")
public class SanalPos {
    @Id
    @Size(max = 10)
    @SequenceGenerator(name = "sanal_pos_id_gen", sequenceName = "vergi_odeme_tur_vergi_tur_id_seq", allocationSize = 1)
    @Column(name = "oid", nullable = false, length = 10)
    private String oid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odeme_id")
    private Odeme odemeId;

    @Size(max = 100)
    @Column(name = "kart_sahibi", length = 100)
    private String kartSahibi;

    @Size(max = 100)
    @Column(name = "kart_banka", length = 100)
    private String kartBanka;

    @Column(name = "optime")
    private OffsetDateTime optime;

    @Column(name = "durum")
    private Short durum;

}