package marconi.it.verifica_tilotta.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import marconi.it.verifica_tilotta.domains.Libro;
import marconi.it.verifica_tilotta.domains.LibroForm;
import marconi.it.verifica_tilotta.services.LibroService;

@Controller
@RequestMapping("/")
public class LibriController {
// non era necessario vista la mancanza di db, ma è comunque buona pratica
    @Autowired
    LibroService libroService;

    @GetMapping
    public ModelAndView viewHomePage() {

        return new ModelAndView("homepage");
    }

    @GetMapping("/libri")
    public ModelAndView viewCatalogo() {

        return new ModelAndView("libri-catalogo").addObject("libri", libroService.getLibri());

        // messaggio se tabella è vuota !!!!!!!!  --> c'è v
    }


    //localhost:8090/home/libro?type=
    @GetMapping("/libri/nuovo")
    public ModelAndView handlerLibroAction() {

        // in base al parametro, mostro la pagina relativa
            return new ModelAndView("libro-registrazione").addObject("libro", new Libro());// VEDI

        // se il parametro è errato, pagina non trovata 
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pagina non trovata!");
    }

    


   /*  @PostMapping("/libri/nuovo")
    public ModelAndView handlerNewLibro(@ModelAttribute Libro libro ) {

        // salvo il libro nel "database"
        libroService.addLibro(libro);

        // MANCA BindingResult !!!!!!!!
        

        String isbn = libro.getIsbn(); // VEDI        
        //return new ModelAndView("redirect:/Libro" + nome);
        return new ModelAndView("redirect:/libri/" + isbn);
    }*/

// --------------------------
    @PostMapping("/libri/nuovo")
    public ModelAndView handlerNewLibro(@ModelAttribute @Valid LibroForm libro, BindingResult br ) {

         // MI DA PROBLEMI
         // verifico la presenza di errori di validazione, e ricarico la pagina
         if (br.hasErrors())
            return new ModelAndView("libro-registrazione");

         else
            // salvo il libro nel "database"
            libroService.addLibro(libro);

        // MANCA BindingResult !!!!!!!!
        

        String isbn = libro.getIsbn(); // VEDI        
        //return new ModelAndView("redirect:/Libro" + nome);
        return new ModelAndView("redirect:/libri/" + isbn);
    }

    //-----------------------------



    @GetMapping("/libri/{isbn}")
    public ModelAndView showLibro(@PathVariable("isbn") String isbn) {

        Optional<LibroForm> libro = libroService.getLibrobyIsbn(isbn); // VEDI

        // controllo se il servizio mi ha passato un dato esistente
        if (libro.isPresent())
            return new ModelAndView("libro-dettagli").addObject("libroD", libro.get()); // VEDI
        else // se è null sollevo un errore
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato");
    }



    @GetMapping("/svuota")
    public ModelAndView viewSvuota() {

        libroService.deleteAll();

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/delete/{isbn}")
    public ModelAndView deleteLibro(
            @PathVariable("isbn") String isbn,
            RedirectAttributes attr) {
        libroService.deleteLibrobyIsbn(isbn); // --> VEDI Isbn

        // aggiunto un boolean agli attributi del redirect
        attr.addFlashAttribute("deleted", true); // VEDI
        return new ModelAndView("redirect:/libri");
    }

}