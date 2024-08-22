package tr.gov.gib.sposservice.object.request;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SposRequest {
    private String oid;                // Ödemenin benzersiz tanımlayıcısı
    private Integer odemeOid;          // Odeme tablosundaki referans numarası
    private String kartNo;             // Kart numarası
    private Integer ccv;               // Kartın güvenlik kodu
    private Integer sonKullanimTarihiAy; // Kartın son kullanma ayı
    private Integer sonKullanimTarihiYil; // Kartın son kullanma yılı
    private String kartSahibi;         // Kart sahibinin adı
    private String kartBanka;          // Kartın bağlı olduğu banka
    private BigDecimal odenecekMiktar;          // Ödenecek tutar
}
