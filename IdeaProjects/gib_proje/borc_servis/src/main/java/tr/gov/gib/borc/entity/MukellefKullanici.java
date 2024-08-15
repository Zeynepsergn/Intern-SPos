package tr.gov.gib.borc.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mukellef_kullanici", schema = "gsths")
public class MukellefKullanici {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mukellef_kullanici_id_gen")
    @SequenceGenerator(name = "mukellef_kullanici_id_gen", sequenceName = "mukellef_kullanici_mukellef_kullanici_id_seq", allocationSize = 1)
    @Column(name = "mukellef_kullanici_id", nullable = false)
    private Integer id;

    @Size(max = 11)
    @NotNull
    @Column(name = "tc_kn", nullable = false, length = 11)
    private String tcKn;

    @Size(max = 10)
    @NotNull
    @Column(name = "vergi_kn", nullable = false, length = 10)
    private String vergiKn;

    @Size(max = 100)
    @NotNull
    @Column(name = "mukellef_ad", nullable = false, length = 100)
    private String mukellefAd;

    @Size(max = 100)
    @NotNull
    @Column(name = "mukellef_soyad", nullable = false, length = 100)
    private String mukellefSoyad;

    @Size(max = 100)
    @NotNull
    @Column(name = "mukellef_unvan", nullable = false, length = 100)
    private String mukellefUnvan;

}