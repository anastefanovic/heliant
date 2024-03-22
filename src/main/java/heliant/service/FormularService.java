package heliant.service;

import heliant.entity.Formular;
import heliant.entity.Korisnik;
import heliant.exception.ResourceNotFoundException;
import heliant.repository.FormularRepository;
import heliant.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FormularService {

    private final FormularRepository formularRepository;
    private final AuthenticationService authenticationService;

    public Formular kreirajFormular(Formular formular) {
        Korisnik korisnikKreirao = authenticationService.extractUser();
        formular.setKorisnikKreirao(korisnikKreirao);

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

        Korisnik korisnikPoslednjiAzurirao = authenticationService.extractUser();
        formularToUpdate.setKorisnikPoslednjiAzurirao(korisnikPoslednjiAzurirao);

        return formularRepository.save(formularToUpdate);
    }

    public void obrisiFormular(int id) {
        formularRepository.deleteById(id);
    }
}
