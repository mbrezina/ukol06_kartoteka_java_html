package cz.czechitas.webapp;

public class DetailForm {
    private String jmeno;
    private String bydliste;
    private String povolani;
    private String email;
    private String fotka;

    public void setFotka(String fotka) {
        this.fotka = fotka;
    }

    public String getFotka() {
        return fotka;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public void setBydliste(String bydliste) {
        this.bydliste = bydliste;
    }

    public void setPovolani(String povolani) {
        this.povolani = povolani;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getBydliste() {
        return bydliste;
    }

    public String getPovolani() {
        return povolani;
    }

    public String getEmail() {
        return email;
    }
}
