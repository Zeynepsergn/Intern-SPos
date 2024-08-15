package tr.gov.gib.borc.object.enums;

import lombok.Getter;

import java.util.Hashtable;

@Getter
public enum VergiEnum {

    SPOS6100(6100,'S',"Sanalpos Servis"),
    SPOS6200(6200,'S',"Sanalpos Servis"),
    FPOS6300(6300,'F',"Fizikselpos Servis"),
    FPOS6400(6400,'F',"Fizikselpos Servis"),
    NAKIT6500(6500,'N',"Nakit Servis");

    private final Integer vergiId;
    private final Character odemeTur;
    private final String odemeTurDsc;

    public static Hashtable<Integer,Character> vergiPool = new Hashtable<>();

    VergiEnum(Integer vergiId, Character odemeTur,String odemeTurDsc) {
        this.vergiId = vergiId;
        this.odemeTur = odemeTur;
        this.odemeTurDsc = odemeTurDsc;
    }

    public static Character getOdemeTur(Integer vergiId) {
        for(VergiEnum vergi : values()) {
            if(vergi.vergiId.equals(vergiId)) {
                return vergi.getOdemeTur();
            }
        }
        return null;
    }

    public static String getOdemeTurDsc(Integer vergiId) {
        for(VergiEnum vergi : values()) {
            if(vergi.vergiId.equals(vergiId)) {
                return vergi.getOdemeTurDsc();
            }
        }
        return null;
    }
}
