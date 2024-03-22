package heliant.service;

import heliant.entity.Formular;
import heliant.entity.Polje;
import heliant.exception.ResourceNotFoundException;
import heliant.repository.FormularRepository;
import heliant.repository.PoljeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PoljeService {

    private final PoljeRepository poljeRepository;
    private final FormularRepository formularRepository;

    public Polje kreirajPolje(int idFormular, Polje polje) {
        Formular formular = formularRepository.findById(idFormular).orElseThrow(
                () -> new ResourceNotFoundException("Formular " + idFormular + " ne postoji")
                );

        polje.setFormular(formular);

        generatePrikazniRedosled(polje);

        return poljeRepository.save(polje);
    }

    private void generatePrikazniRedosled(Polje polje) {
         int prikazniRedosled = poljeRepository
                 .findMaxPrikazniRedosledByFormularId(polje.getFormular().getId()).orElse(0);
         prikazniRedosled++;
         polje.setPrikazniRedosled(prikazniRedosled);
    }

    public Polje procitajPolje(int id) {
        return poljeRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Polje " + id + " ne postoji")
                );
    }

    public Stream<Polje> procitajSvaPoljaZaFormular(int idFormular) {
        if(!formularRepository.existsById(idFormular)) {
            throw new ResourceNotFoundException("Formular " + idFormular + " ne postoji");
        }

        Iterable<Polje> allPolje = poljeRepository.findAllByFormularId(idFormular);
        return StreamSupport.stream(allPolje.spliterator(), false);
    }

    public Polje azurirajPolje(int id, Polje polje) {
        Polje poljeToUpdate = poljeRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Polje " + id + " ne postoji")
                );

        poljeToUpdate.setNaziv(polje.getNaziv());
        poljeToUpdate.setPrikazniRedosled(polje.getPrikazniRedosled());
        poljeToUpdate.setTip(polje.getTip());

        return poljeRepository.save(poljeToUpdate);
    }

    public void obrisiPolje(int id) {
        poljeRepository.deleteById(id);
    }

}
