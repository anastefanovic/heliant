package heliant.repository;

import heliant.entity.FormularPopunjen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FormularPopunjenRepository extends CrudRepository<FormularPopunjen, Integer> {

    @Query("SELECT f FROM FormularPopunjen f WHERE f.formular.id = :formularId")
    Optional<FormularPopunjen> findByFormularId(@Param("formularId") int formularId);

}
