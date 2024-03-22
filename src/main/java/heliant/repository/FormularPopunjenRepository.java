package heliant.repository;

import heliant.entity.FormularPopunjen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface FormularPopunjenRepository extends CrudRepository<FormularPopunjen, Integer> {

    @Query("SELECT f FROM FormularPopunjen f WHERE f.formular.id = :formularId")
    Optional<FormularPopunjen> findByFormularId(@Param("formularId") int formularId);

    @Query("SELECT COUNT(f) FROM FormularPopunjen f WHERE DATE(f.vremeKreiranja) = :yesterday")
    int countFormularPopunjenByDate(LocalDate yesterday);

}
