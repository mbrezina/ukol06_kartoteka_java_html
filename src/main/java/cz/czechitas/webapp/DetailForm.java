package cz.czechitas.webapp;

import javax.validation.constraints.Pattern;

public class DetailForm {
    @Pattern(regexp = "[a-zA-Zá-žÁ-Ž]+", message = "Jméno je povinné, používejte pouze písmena české abecedy")
    private String jmeno;
    @Pattern(regexp = "[a-zA-Zá-žÁ-Ž]+", message = "Jméno je povinné, používejte pouze písmena české abecedy")
    private String bydliste;
    @Pattern(regexp = "[a-zA-Zá-žÁ-Ž]+", message = "Jméno je povinné, používejte pouze písmena české abecedy")
    private String povolani;
    @Pattern(regexp = "[a-zA-Zá-žÁ-Ž]+", message = "Jméno je povinné, používejte pouze písmena české abecedy")
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
