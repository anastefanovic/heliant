package heliant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "statistika")
@Entity
@Getter @Setter @NoArgsConstructor
public class Statistika {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "datum", nullable = false)
    private LocalDate datum;

    @Column(name = "broj_popunjenih_formulara", nullable = false)
    private int brojPopunjenihFormulara;

    public Statistika(LocalDate datum, int brojPopunjenihFormulara) {
        this.datum = datum;
        this.brojPopunjenihFormulara = brojPopunjenihFormulara;
    }
}
