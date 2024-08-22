package tr.gov.gib.odeme.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "odeme", schema = "gsths")
public class Odeme {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "odeme_id_gen")
    @SequenceGenerator(name = "odeme_id_gen", sequenceName = "odeme_odeme_id_seq", allocationSize = 1)
    @Column(name = "odeme_id", nullable = false)
    private Integer id;

    @Column(name = "mukellef_borc_id")
    private Integer mukellefBorc;

    @Column(name = "optime")
    private OffsetDateTime optime;

    @Column(name = "odeme_durum")
    private Short odemeDurum;

    @Column(name = "vergi_tur_id")
    private Integer vergiTur;

    @Column(name = "mukellef_kullanici_id")
    private Long mukellefKullaniciId;

}