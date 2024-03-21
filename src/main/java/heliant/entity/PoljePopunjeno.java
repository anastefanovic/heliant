package heliant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "polje_popunjeno")
@Entity
@Getter @Setter @NoArgsConstructor
public class PoljePopunjeno {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vrednost_tekst")
    private String vrednostTekst;

    @Column(name = "vrednost_broj")
    private Double vrednostBroj;

    @Column(name = "vreme_kreiranja")
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_polje", referencedColumnName = "id", nullable = false)
    private Polje polje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formular_popunjen", referencedColumnName = "id", nullable = false)
    private FormularPopunjen formularPopunjen;

    @PrePersist
    private void setVremeKreiranja() {
        vremeKreiranja = LocalDateTime.now();
    }

    @PreUpdate
    private void setVremePoslednjeIzmene() {
        vremePoslednjeIzmene = LocalDateTime.now();
    }

    public PoljePopunjeno(String vrednostTekst, Double vrednostBroj) {
        this.vrednostTekst = vrednostTekst;
        this.vrednostBroj = vrednostBroj;
    }
}
