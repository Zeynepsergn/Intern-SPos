package tr.gov.gib.sposservice.util.enums;

public enum SanalPosDurum {

    HATA("Hata oluştu", (short) 0),
    BASARISIZ("Ödeme Başarısız", (short) 1),
    BASARILI("Ödeme Başarılı", (short) 2),
    IPTAL_EDILDI("Ödeme İşlemi İptal Edildi", (short) 3);

    private String detayDurumu;
    private Short durumKodu;

    public String getdetayDurumu() {
        return detayDurumu;
    }

    public Short getdurumKodu() {
        return durumKodu;
    }

    SanalPosDurum(String detayDurumu, Short durumKodu) {
        this.detayDurumu = detayDurumu;
        this.durumKodu = durumKodu;
    }
}
