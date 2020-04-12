package cz.czechitas.webapp;

import org.springframework.stereotype.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HlavniController {

    Long sekvence = 1000L;
    private List<Kontakt> seznamKontaktu;

    public HlavniController() {
        seznamKontaktu = new ArrayList<Kontakt>();
        seznamKontaktu.add(new Kontakt(sekvence++, "Amálka", "víla", "lesní studánka", "amalka@post.cz", "amal.jpg"));
        seznamKontaktu.add(new Kontakt(sekvence++, "Elza", "Ledová královna ", "Ledový zámek", "elza@post.cz", "elza.jpg"));
        seznamKontaktu.add(new Kontakt(sekvence++, "Mach", "Žák 3.B", "Činžovní dům", "mach@post.cz", "sebes.jpg"));
        seznamKontaktu.add(new Kontakt(sekvence++, "Večerníček", "Moderátor", "TV", "vecernicek@post.cz", "vecer.jpg"));
        seznamKontaktu.add(new Kontakt(sekvence++, "Pú", "Medvídek", "Stokorcový les", "pu@post.cz", "pooh.jpg"));
        seznamKontaktu.add(new Kontakt(sekvence++, "Peppa", "Skákat v kalužích", "U mámy a táty", "peppa@post.cz", "peppa.jpg"));
        seznamKontaktu.add(new Kontakt(sekvence++, "Rumcajs", "Loupežník", "Řáholec", "rumcajs@post.cz", "rum.jpg"));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex() {
        return new ModelAndView("redirect:/seznam");
    }

    @RequestMapping(value = "/seznam", method = RequestMethod.GET)
    public ModelAndView zobrazSeznam() {
        ModelAndView drzak = new ModelAndView("index");
        drzak.addObject("seznamKontaktu", seznamKontaktu);
        return drzak;
    }

    @RequestMapping(value = "/seznam/{idKontaktu}", method = RequestMethod.POST, params = "_method=DELETE")
    public ModelAndView smazKontakt(@PathVariable("idKontaktu") Long idKontaktu) {
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
    public ModelAndView UmozniUpravu(@PathVariable Long idKontaktu) {
        ModelAndView drzak = new ModelAndView("uprava");
        Kontakt jeden = findById(idKontaktu);
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
        drzak.addObject("formular", new DetailForm());
        return drzak;
    }

    @RequestMapping(value = "/novy", method = RequestMethod.POST)
    public ModelAndView zpracujNovy(@Valid @ModelAttribute("formular") DetailForm vstup,
                                    BindingResult validacniChyby,
                                    RedirectAttributes flashScope) {
        if (validacniChyby.hasErrors()) {
            ModelAndView data = new ModelAndView("redirect:/novy");
            flashScope.addFlashAttribute("formular", vstup);
            flashScope.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "formular", validacniChyby);
            flashScope.addFlashAttribute("oprava_nutna", "Nezadali jste všechny odpovědi, doplňte to.");
            return data;
        }

        ModelAndView drzak = new ModelAndView("novy");
        ulozKontakt(vstup);
        return new ModelAndView("redirect:/seznam");

        }   //konec request mapping novy POST

    private void ulozKontakt(DetailForm detailform) {
        String jmeno = detailform.getJmeno();
        String povolani = detailform.getPovolani();
        String bydliste = detailform.getBydliste();
        String email = detailform.getEmail();
        String fotka = detailform.getFotka();
        Kontakt novykontakt = new Kontakt(sekvence++, jmeno, povolani, bydliste, email, fotka);
        seznamKontaktu.add(novykontakt);
    }

    private void upravKontakt(Long idKontaktu, DetailForm detailForm) {
        Kontakt upravovanyKontakt = findById(idKontaktu);
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
        Kontakt kontakt = findById(idKontaktu);
        seznamKontaktu.remove(kontakt);
    }

    private Kontakt findById(Long idHledanehoKontaktu) {
        for (Kontakt kontakt : seznamKontaktu) {
            if (kontakt.getIdKontaktu().equals(idHledanehoKontaktu)) {
                return kontakt;
            }
        }
        return null;
    }
}





