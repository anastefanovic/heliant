package heliant.repository;

import heliant.entity.Korisnik;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KorisnikRepository extends CrudRepository<Korisnik, Integer> {
    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
}
