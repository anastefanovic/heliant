package heliant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "formular")
@Entity
@Getter @Setter @NoArgsConstructor
public class Formular {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "vreme_kreiranja")
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;

    @OneToMany(mappedBy = "formular", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Polje> polja;

    @OneToOne(mappedBy = "formular", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private FormularPopunjen formularPopunjen;

    @PrePersist
    private void setVremeKreiranja() {
        vremeKreiranja = LocalDateTime.now();
    }

    @PreUpdate
    private void setVremePoslednjeIzmene() {
        vremePoslednjeIzmene = LocalDateTime.now();
    }

    public Formular(String naziv) {
        this.naziv = naziv;
    }
}
