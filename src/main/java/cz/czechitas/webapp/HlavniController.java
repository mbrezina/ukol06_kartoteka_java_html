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
    public ModelAndView smazKontakt(@PathVariable("idKontaktu") Long idKontaktu) {
        mapaKontaktu.remove(idKontaktu);
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
        Kontakt jeden = mapaKontaktu.get(idKontaktu);
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
    public String zobrazNovy(ModelMap predvyplnenyDrzakNaData) {
        //ModelAndView drzak = new ModelAndView("novy");
        predvyplnenyDrzakNaData.putIfAbsent("formular", new DetailForm());
        //drzak.addObject("jedenKontakt", new DetailForm());
        return "novy";
    }

    @RequestMapping(value = "/novy", method = RequestMethod.POST)
    public ModelAndView zpracujNovy(@Valid @ModelAttribute("formular") DetailForm detailForm,
                                    BindingResult validacniChyby,
                                    RedirectAttributes flashScope) {
        if (validacniChyby.hasErrors()) {
            ModelAndView data = new ModelAndView("redirect:/novy");
            flashScope.addFlashAttribute("formular", detailForm);
            flashScope.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "formular", validacniChyby);
            flashScope.addFlashAttribute("oprava_nutna", "Nezadali jste všechny položky kontaktu, doplňte to.");
            return data;
        }
        ModelAndView drzak = new ModelAndView("novy");
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
        mapaKontaktu.put(sekvence++, novyKontakt);
    }

    private void upravKontakt(Long idKontaktu, DetailForm detailForm) {
        Kontakt upravovanyKontakt = mapaKontaktu.get(idKontaktu);
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

}
