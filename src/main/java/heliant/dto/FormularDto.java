package heliant.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FormularDto {
    private int id;
    private String naziv;
    private LocalDateTime vremeKreiranja;
    private LocalDateTime vremePoslednjeIzmene;
}
