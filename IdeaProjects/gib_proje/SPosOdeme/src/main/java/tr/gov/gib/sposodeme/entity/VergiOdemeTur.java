package tr.gov.gib.sposodeme.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "vergi_odeme_tur", schema = "gsths")
public class VergiOdemeTur {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vergi_odeme_tur_id_gen")
    @SequenceGenerator(name = "vergi_odeme_tur_id_gen", sequenceName = "vergi_odeme_tur_vergi_tur_id_seq", allocationSize = 1)
    @Column(name = "vergi_tur_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vergi_id")
    private Vergi vergiId;

    @Column(name = "vergi_odeme_tur")
    private Short vergiOdemeTur;

}