package heliant.entity;

import heliant.enumeration.Tip;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "polje")
@Entity
@Getter @Setter @NoArgsConstructor
public class Polje {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "naziv")
    private String naziv;

    @Column(name = "prikazni_redosled", nullable = false)
    private int prikazniRedosled;

    @Enumerated(EnumType.STRING)
    @Column(name = "tip", nullable = false)
    private Tip tip;

    @Column(name = "vreme_kreiranja")
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_formular", referencedColumnName = "id", nullable = false)
    private Formular formular;

    @OneToOne(mappedBy = "polje", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PoljePopunjeno poljePopunjeno;

    @PrePersist
    private void setVremeKreiranja() {
        vremeKreiranja = LocalDateTime.now();
    }

    @PreUpdate
    private void setVremePoslednjeIzmene() {
        vremePoslednjeIzmene = LocalDateTime.now();
    }

    public Polje(String naziv, Tip tip) {
        this.naziv = naziv;
        this.tip = tip;
    }
}

