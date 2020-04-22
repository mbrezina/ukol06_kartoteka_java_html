package cz.czechitas.webapp;

import java.util.*;

public class KontaktRepository {

    private Long sekvence = 1000L;
    private TreeMap<Long, Kontakt> mapaKontaktu = new TreeMap<Long, Kontakt>();

    //inicializační blok, provede se nejdřív, pak se provede kontruktor
    /*{
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));
    } */

    KontaktRepository() {
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        mapaKontaktu.put(++sekvence, new Kontakt(sekvence, "Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));
    }

    public int getSize() {
        return mapaKontaktu.size();
    }

    public Map<Long, Kontakt> getMapaKontaktu() {
        return mapaKontaktu;
    }

    public DetailForm nactiKontakt(Kontakt puvodniKontakt) {
        DetailForm detailform = new DetailForm();
        detailform.setJmeno(puvodniKontakt.getJmeno());
        detailform.setBydliste(puvodniKontakt.getBydliste());
        detailform.setPovolani(puvodniKontakt.getPovolani());
        detailform.setEmail(puvodniKontakt.getEmail());
        detailform.setFotka(puvodniKontakt.getFotka());
        return detailform;
    }

    public void pridejKontakt(DetailForm detailform) {
        String jmeno = detailform.getJmeno();
        String povolani = detailform.getPovolani();
        String bydliste = detailform.getBydliste();
        String email = detailform.getEmail();
        String fotka = detailform.getFotka();
        Kontakt novyKontakt = new Kontakt(sekvence++, jmeno, povolani, bydliste, email, fotka);
        mapaKontaktu.put(sekvence, novyKontakt);
    }

    public void zmenKontakt(Long idKontaktu, DetailForm detailForm) {
        Kontakt upravovanyKontakt = findById(idKontaktu);
        upravovanyKontakt.setFotka(detailForm.getFotka());
        upravovanyKontakt.setJmeno(detailForm.getJmeno());
        upravovanyKontakt.setPovolani(detailForm.getPovolani());
        upravovanyKontakt.setBydliste(detailForm.getBydliste());
        upravovanyKontakt.setEmail(detailForm.getEmail());
    }

    public void smazKontaktPodleId(Long idKontaktu) {
        mapaKontaktu.remove(idKontaktu);
    }

    public Kontakt findById(Long idHledanehoKontaktu) {
        return mapaKontaktu.get(idHledanehoKontaktu);
    }

}
