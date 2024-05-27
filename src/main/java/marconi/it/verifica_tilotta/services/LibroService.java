package marconi.it.verifica_tilotta.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

//import marconi.it.verifica_tilotta.domains.Libro;
import marconi.it.verifica_tilotta.domains.LibroForm;

@Service
public class LibroService {
    
     // creo un finto "database" dove salvare i libri registrati
     //private ArrayList<Libro> libri = new ArrayList<>();
     private ArrayList<LibroForm> libri = new ArrayList<>();

     public ArrayList<LibroForm> getLibri() {
         return libri;
     }
 
     public void addLibro(LibroForm newLibro) {
         libri.add(newLibro);
     }

     
 
     public Optional<LibroForm> getLibroByLibroTitle(String LibroTitle) {
 
         for(LibroForm l : libri) {
             if(l.getTitolo().equals(LibroTitle)) {
                 return Optional.of(l);
             }
         }
         return Optional.empty();
     }
 
     public void deleteAll() {
         libri.clear(); // VEDI
     }
 
     public Optional<LibroForm> deleteLibro(String LibroTitle) {
         for(LibroForm l : libri) {
             if(l.getTitolo().equals(LibroTitle)) {
                 return Optional.of(l);
             }
         }
         return Optional.empty();
     }
 
     public Optional<LibroForm> deleteLibrobyIsbn(String LibroIsbn) {
         for(LibroForm l : libri) {
             if(l.getIsbn().equals(LibroIsbn)) {
                 libri.remove(l);
                 return Optional.of(l);
             }
         }
         return Optional.empty();
     }
 
     public Optional<LibroForm> getLibrobyIsbn(String LibroIsbn) {
         for(LibroForm l : libri) {
             if(l.getIsbn().equals(LibroIsbn)) {
                 return Optional.of(l);
             }
         }
         return Optional.empty();
     }
 
}
