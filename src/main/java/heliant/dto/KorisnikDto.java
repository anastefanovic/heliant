package heliant.dto;

import heliant.enumeration.Rola;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class KorisnikDto {
    private String korisnickoIme;
    private String lozinka;
    private Rola rola;
}
