package tr.gov.gib.sposservice.entity;

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
    @Size(max = 20)
    @SequenceGenerator(name = "sanal_pos_id_gen", sequenceName = "odeme_odeme_id_seq", allocationSize = 1)
    @Column(name = "oid", nullable = false, length = 20)
    private String oid;

    @Column(name = "odeme_id")
    private Integer odeme;

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