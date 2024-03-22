package heliant.service;

import heliant.entity.Formular;
import heliant.exception.ResourceNotFoundException;
import heliant.repository.FormularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FormularService {

    private final FormularRepository formularRepository;

    public Formular kreirajFormular(Formular formular) {
        return formularRepository.save(formular);
    }

    public Formular procitajFormular(int id) {
        return formularRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Formular " + id + " nije pronadjen")
                );
    }

    public Stream<Formular> procitajSveFormulare() {
        Iterable<Formular> allFormular = formularRepository.findAll();
        return StreamSupport.stream(allFormular.spliterator(), false);
    }

    public Formular azurirajFormular(int id, Formular formular) {
        Formular formularToUpdate = formularRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Formular " + id + " nije pronadjen")
                );

        formularToUpdate.setNaziv(formular.getNaziv());

        return formularRepository.save(formularToUpdate);
    }

    public void obrisiFormular(int id) {
        formularRepository.deleteById(id);
    }
}
