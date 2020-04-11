package cz.czechitas.webapp;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class HlavniController {

    Long sekvence = 1000L;
    Long nova_sekvence = 1000L;
    private Map<Long, Kontakt> mapaKontaktu = new Map<Long, Kontakt>() {

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object o) {
            return false;
        }

        @Override
        public boolean containsValue(Object o) {
            return false;
        }

        @Override
        public Kontakt get(Object o) {
            return null;
        }

        @Override
        public Kontakt put(Long aLong, Kontakt kontakt) {
            return null;
        }

        @Override
        public Kontakt remove(Object o) {
            return null;
        }

        @Override
        public void putAll(Map<? extends Long, ? extends Kontakt> map) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<Long> keySet() {
            return null;
        }

        @Override
        public Collection<Kontakt> values() {
            return null;
        }

        @Override
        public Set<Entry<Long, Kontakt>> entrySet() {
            return null;
        }
    };

    private List<Kontakt> seznamKontaktu;

    public HlavniController() {
        mapaKontaktu.put(nova_sekvence++, new Kontakt("Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        mapaKontaktu.put(nova_sekvence++, new Kontakt("Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));

        seznamKontaktu = new ArrayList<Kontakt>();
        //seznamKontaktu.add(new Kontakt(sekvence++, "Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        //seznamKontaktu.add(new Kontakt(sekvence++, "Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        //seznamKontaktu.add(new Kontakt(sekvence++, "Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        //seznamKontaktu.add(new Kontakt(sekvence++, "Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        //seznamKontaktu.add(new Kontakt(sekvence++, "Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        //seznamKontaktu.add(new Kontakt(sekvence++, "Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        //seznamKontaktu.add(new Kontakt(sekvence++, "Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex() {
        return new ModelAndView("redirect:/seznam");
    }

    @RequestMapping(value = "/seznam", method = RequestMethod.GET)
    public ModelAndView zobrazSeznam() {
        ModelAndView drzak = new ModelAndView("index");
        drzak.addObject("seznamKontaktu", mapaKontaktu.values());
        //drzak.addObject("seznamKontaktu", seznamKontaktu);
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
        Kontakt jeden = ziskejKontakt(idKontaktu);
        //Kontakt jeden = findById(idKontaktu);
        drzak.addObject("jedenKontakt", jeden);
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
        ulozKontakt(detailForm);
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
        Kontakt upravovanyKontakt = ziskejKontakt(idKontaktu);
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





