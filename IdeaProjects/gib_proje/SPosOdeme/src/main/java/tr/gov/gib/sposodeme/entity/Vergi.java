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
@Table(name = "vergi", schema = "gsths")
public class Vergi {
    @Id
    @Column(name = "vergi_id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "vergi_aciklama")
    private String vergiAciklama;

    @Column(name = "vergi_durum")
    private Short vergiDurum;

    @OneToMany(mappedBy = "vergiId")
    private Set<MukellefBorc> mukellefBorcs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vergiId")
    private Set<OdemeDetay> odemeDetays = new LinkedHashSet<>();

    @OneToMany(mappedBy = "vergiId")
    private Set<VergiOdemeTur> vergiOdemeTurs = new LinkedHashSet<>();

}