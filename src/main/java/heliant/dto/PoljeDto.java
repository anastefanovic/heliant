package heliant.dto;

import heliant.enumeration.Tip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PoljeDto {
    private int id;
    private String naziv;
    private int prikazniRedosled;
    private Tip tip;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;
    private int idFormular;
    private int idKorisnikKreirao;
    private int idKorisnikPoslednjiAzurirao;
}
