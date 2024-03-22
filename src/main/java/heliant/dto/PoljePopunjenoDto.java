package heliant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PoljePopunjenoDto {
    private int id;
    private String vrednostTekst;
    private double vrednostBroj;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;
    private int idFormularPopunjen;
    private int idPolje;
}
