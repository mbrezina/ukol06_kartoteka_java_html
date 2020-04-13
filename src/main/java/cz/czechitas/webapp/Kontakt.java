package cz.czechitas.webapp;

public class Kontakt {

    private Long idKontaktu;
    private String jmeno;
    private String povolani;
    private String bydliste;
    private String email;
    private String fotka;

    public Kontakt(Long idKontaktu, String jmeno, String povolani, String bydliste, String email, String fotka) {
        this.idKontaktu = idKontaktu;
        this.povolani = povolani;
        this.bydliste = bydliste;
        this.jmeno = jmeno;
        this.email = email;
        this.fotka = fotka;
    }

    public void setFotka(String fotka) {
        this.fotka = fotka;
    }

    public String getFotka() {
        return fotka;
    }

    public void setIdKontaktu(Long idKontaktu) {
        this.idKontaktu = idKontaktu;
    }

    public void setPovolani(String povolani) {
        this.povolani = povolani;
    }

    public void setBydliste(String bydliste) {
        this.bydliste = bydliste;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdKontaktu() {
        return idKontaktu;
    }

    public String getPovolani() {
        return povolani;
    }

    public String getBydliste() {
        return bydliste;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getEmail() {
        return email;
    }
}
