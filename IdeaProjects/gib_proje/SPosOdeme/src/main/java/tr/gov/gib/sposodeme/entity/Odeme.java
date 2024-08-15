package tr.gov.gib.sposodeme.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "odeme", schema = "gsths")
public class Odeme {
    @Id
    @ColumnDefault("nextval('gsths.odeme_odeme_id_seq'::regclass)")
    @Column(name = "odeme_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mukellef_borc_id")
    private MukellefBorc mukellefBorcId;

    @Column(name = "optime")
    private OffsetDateTime optime;

    @Column(name = "odeme_durum")
    private Short odemeDurum;

}