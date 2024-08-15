package tr.gov.gib.sposodeme.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;
@Getter
@Setter
@Entity
@Table(name = "odeme_detay", schema = "gsths")
public class OdemeDetay {
    @Id
    @ColumnDefault("nextval('gsths.odeme_detay_odeme_detay_id_seq'::regclass)")
    @Column(name = "odeme_detay_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukellef_kullanici_id")
    private MukellefKullanici mukellefKullaniciId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vergi_id")
    private Vergi vergiId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "odeme_id")
    private Odeme odeme;  // Burayı Integer yerine Odeme olarak güncelledik

    @Size(max = 100)
    @Column(name = "aciklama", length = 100)
    private String aciklama;

    @Column(name = "odenen_borc_miktari")
    private Double odenenBorcMiktari;

    @Column(name = "odeme_detay_durum")
    private Short odemeDetayDurum;

    @Column(name = "iade_zamani")
    private OffsetDateTime iadeZamani;

    @Column(name = "optime")
    private OffsetDateTime optime;
}
