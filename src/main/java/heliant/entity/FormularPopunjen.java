package heliant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "formular_popunjen")
@Entity
@Getter @Setter @NoArgsConstructor
public class FormularPopunjen {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vreme_kreiranja")
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnik_kreirao", referencedColumnName = "id")
    private Korisnik korisnikKreirao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_korisnik_poslednji_azurirao", referencedColumnName = "id")
    private Korisnik korisnikPoslednjiAzurirao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formular", referencedColumnName = "id", nullable = false)
    private Formular formular;

    @OneToMany(mappedBy = "formularPopunjen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PoljePopunjeno> poljePopunjenoList;

    @PrePersist
    private void setVremeKreiranja() {
        vremeKreiranja = LocalDateTime.now();
    }

    @PreUpdate
    private void setVremePoslednjeIzmene() {
        vremePoslednjeIzmene = LocalDateTime.now();
    }

}
