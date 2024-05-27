package marconi.it.verifica_tilotta.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    
    private String isbn;
    private String titolo;
    private String autore;
    private String genere;
    private String annoPubblicazione;
}
