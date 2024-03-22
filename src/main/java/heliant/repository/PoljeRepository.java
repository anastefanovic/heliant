package heliant.repository;

import heliant.entity.Polje;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PoljeRepository extends CrudRepository<Polje, Integer> {

    @Query("SELECT p FROM Polje p WHERE p.formular.id = :formularId ORDER BY p.prikazniRedosled")
    Iterable<Polje> findAllByFormularId(@Param("formularId") int formularId);

    @Query("SELECT MAX(p.prikazniRedosled) FROM Polje p WHERE p.formular.id = :formularId GROUP BY p.formular.id")
    Optional<Integer> findMaxPrikazniRedosledByFormularId(@Param("formularId") int formularId);

}
