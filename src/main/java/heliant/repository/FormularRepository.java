package heliant.repository;

import heliant.entity.Formular;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface FormularRepository extends CrudRepository<Formular, Integer> {

    Page<Formular> findAll(Pageable pageable);

}
