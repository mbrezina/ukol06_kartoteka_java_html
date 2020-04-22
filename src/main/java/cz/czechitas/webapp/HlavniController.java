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
    private KontaktRepository kontaktRepository;

    public HlavniController(KontaktRepository kontaktRepository) {
        this.kontaktRepository = new KontaktRepository();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView zobrazIndex() {
        return new ModelAndView("redirect:/seznam");
    }

    @RequestMapping(value = "/seznam", method = RequestMethod.GET)
    public ModelAndView zobrazSeznam() {
        ModelAndView drzak = new ModelAndView("index");
        if (kontaktRepository.getSize() == 0) {
            drzak.addObject("hlaseni", "V seznamu není žádný kontakt, zkuste si nějaký vytvořit");
        }

        drzak.addObject("seznamKontaktu", new ArrayList<Kontakt>(kontaktRepository.getMapaKontaktu().values()));
        return drzak;
    }

    @RequestMapping(value = "/uprava/{idKontaktu:[0-9]+}", method = RequestMethod.POST, params = "_method=DELETE")
    public ModelAndView zpracujSmazaniKontaktu(@PathVariable("idKontaktu") Long idKontaktu) {
        kontaktRepository.smazKontaktPodleId(idKontaktu);
        return new ModelAndView("redirect:/seznam");
    }

    @RequestMapping(value = "/detail/{idKontaktu:[0-9]+}", method = RequestMethod.GET)
    public ModelAndView zobrazDetail(@PathVariable Long idKontaktu) {
        ModelAndView drzak = new ModelAndView("detail");
        Kontakt jeden = kontaktRepository.findById(idKontaktu);
        drzak.addObject("jedenKontakt", jeden);
        return drzak;
    }

    @RequestMapping(value = "/uprava/{idKontaktu:[0-9]+}", method = RequestMethod.GET)
    public String zobrazUpravu(@PathVariable Long idKontaktu, ModelMap predvyplnenyDrzakNaData) {
        predvyplnenyDrzakNaData.put("zadani", "Zde můžete upravit pohádkovou postavu");
        if (!predvyplnenyDrzakNaData.containsKey("formular")) {
            Kontakt jeden = kontaktRepository.findById(idKontaktu);
            DetailForm formular = kontaktRepository.nactiKontakt(jeden);
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

        kontaktRepository.zmenKontakt(idKontaktu, vstup);
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
        kontaktRepository.pridejKontakt(vstup);
        return "redirect:/seznam";
    }
}
