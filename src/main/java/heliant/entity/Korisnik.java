package heliant.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "korisnik")
@Entity
@Getter @Setter @NoArgsConstructor
public class Korisnik {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "korisnicko_ime", nullable = false)
    private String korisnickoIme;

    @Column(name = "lozinka", nullable = false)
    private String lozinka;

    @Column(name = "vreme_kreiranja")
    private LocalDateTime vremeKreiranja;

    @Column(name = "vreme_poslednje_izmene")
    private LocalDateTime vremePoslednjeIzmene;

    @PrePersist
    private void setVremeKreiranja() {
        vremeKreiranja = LocalDateTime.now();
    }

    @PreUpdate
    private void setVremePoslednjeIzmene() {
        vremePoslednjeIzmene = LocalDateTime.now();
    }

    public Korisnik(String korisnickoIme, String lozinka) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }
}
