package cz.czechitas.webapp;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class HlavniController {

    Long sekvence = 1000L;
    private Map<Long, Kontakt> mapaKontaktu;

    public HlavniController() {
        mapaKontaktu = new TreeMap<Long, Kontakt>();
        mapaKontaktu.put(sekvence++, new Kontakt("Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        mapaKontaktu.put(sekvence++, new Kontakt("Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));

    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex() {
        return new ModelAndView("redirect:/seznam");
    }

    @RequestMapping(value = "/seznam", method = RequestMethod.GET)
    public ModelAndView zobrazSeznam() {
        ModelAndView drzak = new ModelAndView("index");
        if (mapaKontaktu.size() == 0) {
            drzak.addObject("hlaseni", "V seznamu není žádný kontakt, zkuste si nějaký vytvořit");
        }
        drzak.addObject("seznamKontaktu", mapaKontaktu);
        return drzak;
    }

    @RequestMapping(value = "/seznam/{idKontaktu}", method = RequestMethod.POST, params = "_method=DELETE")
    public ModelAndView smazClanek(@PathVariable("idKontaktu") Long idKontaktu) {


        smazKontaktPodleId(idKontaktu);
        return new ModelAndView("redirect:/seznam");
    }

    @RequestMapping(value = "/detail/{idKontaktu:[0-9]+}", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable Long idKontaktu) {
        ModelAndView drzak = new ModelAndView("detail");
        Kontakt jeden = mapaKontaktu.get(idKontaktu);
        drzak.addObject("jedenKontakt", jeden);
        drzak.addObject("idKontaktu", idKontaktu);
        return drzak;
    }

    @RequestMapping(value = "/uprava/{idKontaktu:[0-9]+}", method = RequestMethod.GET)
    public ModelAndView UmozniUpravu(@PathVariable Long idKontaktu) {
        ModelAndView drzak = new ModelAndView("uprava");
        Kontakt jeden = ziskejKontakt(idKontaktu);
        //Kontakt jeden = findById(idKontaktu);
        drzak.addObject("jedenKontakt", jeden);
        drzak.addObject("zadani", "Zde můžete upravit pohádkovou postavu");
        return drzak;
    }

    @RequestMapping(value = "/uprava/{idKontaktu:[0-9]+}", method = RequestMethod.POST)
    public ModelAndView UpravKontakt(@PathVariable("idKontaktu") Long idKontaktu, DetailForm detailForm) {
        upravKontakt(idKontaktu, detailForm);
        return new ModelAndView("redirect:/seznam");
    }

    @RequestMapping(value = "/novy", method = RequestMethod.GET)
    public ModelAndView zobrazNovy() {
        ModelAndView drzak = new ModelAndView("novy");
        drzak.addObject("jedenKontakt", new DetailForm());
        return drzak;
    }

    @RequestMapping(value = "/novy", method = RequestMethod.POST)
    public ModelAndView zpracujNovy(DetailForm detailForm) {
        ModelAndView drzak = new ModelAndView("uprava");
        ulozKontakt(detailForm); //ok metoda
        return new ModelAndView("redirect:/seznam");
    }

    private void ulozKontakt(DetailForm detailform) {
        String jmeno = detailform.getJmeno();
        String povolani = detailform.getPovolani();
        String bydliste = detailform.getBydliste();
        String email = detailform.getEmail();
        String fotka = detailform.getFotka();
        Kontakt novyKontakt = new Kontakt(jmeno, povolani, bydliste, email, fotka);
        //Kontakt novyKontakt = new Kontakt(sekvence, jmeno, povolani, bydliste, email, fotka);
        mapaKontaktu.put(sekvence++, novyKontakt);
        //seznamKontaktu.add(novykontakt);
    }

    private void upravKontakt(Long idKontaktu, DetailForm detailForm) {
        Kontakt upravovanyKontakt = mapaKontaktu.get(idKontaktu);
        //Kontakt upravovanyKontakt = ziskejKontakt(idKontaktu);
        if (!detailForm.getFotka().isEmpty()) {
            upravovanyKontakt.setFotka(detailForm.getFotka());
        }
        if (!detailForm.getJmeno().isEmpty()) {
            upravovanyKontakt.setJmeno(detailForm.getJmeno());
        }
        if (!detailForm.getPovolani().isEmpty()) {
            upravovanyKontakt.setPovolani(detailForm.getPovolani());
        }
        if (!detailForm.getBydliste().isEmpty()) {
            upravovanyKontakt.setBydliste(detailForm.getBydliste());
        }
        if (!detailForm.getEmail().isEmpty()) {
            upravovanyKontakt.setEmail(detailForm.getEmail());
        }
    }

    private void smazKontaktPodleId(Long idKontaktu) {
        Kontakt kontakt = ziskejKontakt(idKontaktu);
        //Kontakt kontakt = findById(idKontaktu);
        mapaKontaktu.remove(idKontaktu);
        //seznamKontaktu.remove(kontakt);
    }

    private Kontakt ziskejKontakt(Long idKontaktu) {
        return mapaKontaktu.get(idKontaktu);
    }

    //private Kontakt findById(Long idHledanehoKontaktu) {
    //    for (Kontakt kontakt : seznamKontaktu) {
    //        if (kontakt.getIdKontaktu().equals(idHledanehoKontaktu)) {
    //           return kontakt;
    //        }
    //    }
    //    return null;

    //}


}





