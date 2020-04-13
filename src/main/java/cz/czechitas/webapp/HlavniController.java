package cz.czechitas.webapp;

import org.springframework.stereotype.*;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
public class HlavniController {

    Long sekvence = 1000L;
    private Map<Long, Kontakt> mapaKontaktu;

    public HlavniController() {
        mapaKontaktu = new TreeMap<Long, Kontakt>();
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
        drzak.addObject("seznamKontaktu", new ArrayList<>(mapaKontaktu.values()));
        return drzak;
    }

    @RequestMapping(value = "/uprava/{idKontaktu:[0-9]+}", method = RequestMethod.POST, params = "_method=DELETE")
    public ModelAndView zpracujSmazaniKontaktu(@PathVariable("idKontaktu") Long idKontaktu) {
        smazKontaktPodleId(idKontaktu);
        return new ModelAndView("redirect:/seznam");
    }

    @RequestMapping(value = "/detail/{idKontaktu:[0-9]+}", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable Long idKontaktu) {
        ModelAndView drzak = new ModelAndView("detail");
        Kontakt jeden = findById(idKontaktu);
        drzak.addObject("jedenKontakt", jeden);
        return drzak;
    }

    @RequestMapping(value = "/uprava/{idKontaktu:[0-9]+}", method = RequestMethod.GET)
    public String zobrazUpravu(@PathVariable Long idKontaktu, ModelMap predvyplnenyDrzakNaData) {
        predvyplnenyDrzakNaData.put("zadani", "Zde můžete upravit pohádkovou postavu");
        if (!predvyplnenyDrzakNaData.containsKey("formular")) {
            Kontakt jeden = findById(idKontaktu);
            DetailForm formular = nactiKontakt(jeden);
            predvyplnenyDrzakNaData.put("formular", formular);
        }
        return "uprava";
    }

    @RequestMapping(value = "/uprava/{idKontaktu:[0-9]+}", method = RequestMethod.POST)
    public String zpracujUpravu(@PathVariable("idKontaktu") Long idKontaktu,
                                @Valid @ModelAttribute("formular") DetailForm vstup,
                                BindingResult validacniChyby,
                                RedirectAttributes flashScope) {
        if (validacniChyby.hasErrors()) {
            flashScope.addFlashAttribute("formular", vstup);
            flashScope.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "formular", validacniChyby);
            return "redirect:/uprava/" + idKontaktu;
        }

        zmenKontakt(idKontaktu, vstup);
        return "redirect:/seznam";
    }

    @RequestMapping(value = "/novy", method = RequestMethod.GET)
    public String zobrazNovy(ModelMap predvyplnenyDrzakNaData) {
        predvyplnenyDrzakNaData.putIfAbsent("formular", new DetailForm());
        return "uprava";
    }

    @RequestMapping(value = "/novy", method = RequestMethod.POST)
    public String zpracujNovy(@Valid @ModelAttribute("formular") DetailForm vstup,
                                    BindingResult validacniChyby,
                                    RedirectAttributes flashScope) {
        if (validacniChyby.hasErrors()) {
            flashScope.addFlashAttribute("formular", vstup);
            flashScope.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "formular", validacniChyby);
            return "redirect:/novy";
        }

        pridejKontakt(vstup);
        return "redirect:/seznam";
    }

    //-------------------------------------------------------------------------

    private DetailForm nactiKontakt(Kontakt puvodniKontakt) {
        DetailForm detailform = new DetailForm();
        detailform.setJmeno(puvodniKontakt.getJmeno());
        detailform.setBydliste(puvodniKontakt.getBydliste());
        detailform.setPovolani(puvodniKontakt.getPovolani());
        detailform.setEmail(puvodniKontakt.getEmail());
        detailform.setFotka(puvodniKontakt.getFotka());
        return detailform;
    }

    private void pridejKontakt(DetailForm detailform) {
        String jmeno = detailform.getJmeno();
        String povolani = detailform.getPovolani();
        String bydliste = detailform.getBydliste();
        String email = detailform.getEmail();
        String fotka = detailform.getFotka();
        Kontakt novyKontakt = new Kontakt(sekvence++, jmeno, povolani, bydliste, email, fotka);
        mapaKontaktu.put(sekvence, novyKontakt);
    }

    private void zmenKontakt(Long idKontaktu, DetailForm detailForm) {
        Kontakt upravovanyKontakt = findById(idKontaktu);
        upravovanyKontakt.setFotka(detailForm.getFotka());
        upravovanyKontakt.setJmeno(detailForm.getJmeno());
        upravovanyKontakt.setPovolani(detailForm.getPovolani());
        upravovanyKontakt.setBydliste(detailForm.getBydliste());
        upravovanyKontakt.setEmail(detailForm.getEmail());
    }

    private void smazKontaktPodleId(Long idKontaktu) {
        mapaKontaktu.remove(idKontaktu);
    }

    private Kontakt findById(Long idHledanehoKontaktu) {
        return mapaKontaktu.get(idHledanehoKontaktu);
    }
}
