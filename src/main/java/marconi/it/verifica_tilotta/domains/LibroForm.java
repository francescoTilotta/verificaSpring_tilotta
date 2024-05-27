package marconi.it.verifica_tilotta.domains;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroForm {
    
    @NotEmpty
    private String isbn;
    
    @NotEmpty
    private String titolo;

    @NotEmpty
    private String autore;

    @NotEmpty
    private String genere;

    @NotEmpty
    private String annoPubblicazione;
}
