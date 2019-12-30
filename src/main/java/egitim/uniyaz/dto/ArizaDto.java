package egitim.uniyaz.dto;

import java.util.Date;

public class ArizaDto {
    private Long id;
    private String musteriTC;
    private String musteriIsim;
    private String musteriSoyisim;
    private Long musteriId;
    private String icerik;
    private ArizaDurum arizaDurum;
    private Date baslangicTarihi;

    public ArizaDurum getArizaDurum() {
        return arizaDurum;
    }

    public void setArizaDurum(ArizaDurum arizaDurum) {
        this.arizaDurum = arizaDurum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMusteriTC() {
        return musteriTC;
    }

    public void setMusteriTC(String musteriTC) {
        this.musteriTC = musteriTC;
    }

    public String getMusteriIsim() {
        return musteriIsim;
    }

    public void setMusteriIsim(String musteriIsim) {
        this.musteriIsim = musteriIsim;
    }

    public String getMusteriSoyisim() {
        return musteriSoyisim;
    }

    public void setMusteriSoyisim(String musteriSoyisim) {
        this.musteriSoyisim = musteriSoyisim;
    }

    public Long getMusteriId() {
        return musteriId;
    }

    public void setMusteriId(Long musteriId) {
        this.musteriId = musteriId;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }



    public Date getBaslangicTarihi() {
        return baslangicTarihi;
    }

    public void setBaslangicTarihi(Date baslangicTarihi) {
        this.baslangicTarihi = baslangicTarihi;
    }
}
