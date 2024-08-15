package tr.gov.gib.sposodeme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

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
    @Column(name = "tckn", length = 11)
    private String tckn;

    @Size(max = 10)
    @Column(name = "vkn", length = 10)
    private String vkn;

    @Size(max = 100)
    @Column(name = "mukellef_ad", length = 100)
    private String mukellefAd;

    @Size(max = 100)
    @Column(name = "mukellef_soyad", length = 100)
    private String mukellefSoyad;

    @Size(max = 100)
    @Column(name = "mukellef_unvan", length = 100)
    private String mukellefUnvan;

    @OneToMany(mappedBy = "mukellefKullaniciId")
    private Set<MukellefBorc> mukellefBorcs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "mukellefKullaniciId")
    private Set<OdemeDetay> odemeDetays = new LinkedHashSet<>();

}