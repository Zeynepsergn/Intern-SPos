package tr.gov.gib.sposservice.object.reponse;


import lombok.Data;

@Data
public class BankaResponse {
    private Short durum;      // 0: Hata, 1: Başarısız, 2: Başarılı, 3: İptal Edildi
    private String hataMesaji; // Eğer durum 0 veya 1 ise hata mesajı buraya gelir

}
