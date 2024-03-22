package heliant.repository;

import heliant.entity.PoljePopunjeno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PoljePopunjenoRepository extends CrudRepository<PoljePopunjeno, Integer> {

    @Query("SELECT p FROM PoljePopunjeno p " +
            "WHERE p.formularPopunjen.id = :formularPopunjenId " +
            "ORDER BY p.polje.prikazniRedosled")
    Iterable<PoljePopunjeno> findAllByFormularPopunjenId(@Param("formularPopunjenId") int formularPopunjenId);

    @Query("SELECT p FROM PoljePopunjeno p " +
            "WHERE p.formularPopunjen.id = :formularPopunjenId " +
            "AND p.polje.id = :poljeId " +
            "ORDER BY p.polje.prikazniRedosled")
    Optional<PoljePopunjeno> findByFormularPopunjenIdAndPoljeId(
            @Param("formularPopunjenId") int formularPopunjenId,
            @Param("poljeId") int poljeId
    );

}
