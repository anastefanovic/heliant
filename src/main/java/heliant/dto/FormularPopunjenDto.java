package heliant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FormularPopunjenDto {
    private int id;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;
    private int idFormular;
    private int idKorisnikKreirao;
    private int idKorisnikPoslednjiAzurirao;
}
