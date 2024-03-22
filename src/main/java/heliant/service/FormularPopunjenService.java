package heliant.service;

import heliant.entity.Formular;
import heliant.entity.FormularPopunjen;
import heliant.entity.PoljePopunjeno;
import heliant.exception.ResourceNotFoundException;
import heliant.exception.ResourceNotValidException;
import heliant.exception.TypeNotSupportedException;
import heliant.repository.FormularPopunjenRepository;
import heliant.repository.FormularRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FormularPopunjenService {

    private final FormularPopunjenRepository formularPopunjenRepository;
    private final FormularRepository formularRepository;
    private final PoljePopunjenoService poljePopunjenoService;

    public FormularPopunjen kreirajFormularPopunjen(
            int idFormular,
            List<PoljePopunjeno> poljePopunjenoList
    ) {
        if(poljePopunjenoList.isEmpty()){
            throw new ResourceNotFoundException("Popunjena polja moraju biti prosledjena");
        }

        Formular formular = formularRepository.findById(idFormular)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Formular " + idFormular + " ne postoji")
                );

        if(formularPopunjenRepository.findByFormularId(idFormular).isPresent()) {
            throw new ResourceNotValidException("Formular " + idFormular + " je vec popunjen");
        }

        FormularPopunjen formularPopunjen = new FormularPopunjen();
        formularPopunjen.setFormular(formular);

        List<PoljePopunjeno> listToSave = poljePopunjenoList.stream().map(p -> {
            if(p.getPolje().getFormular().getId() != idFormular) {
                throw new ResourceNotValidException(
                        "Polje " + p.getPolje().getId() + " ne pripada formularu " + idFormular
                );
            }

            if(!poljePopunjenoService.poljeTypeValid(p)) {
                throw new TypeNotSupportedException(
                        "Polje mora biti popunjeno vrednoscu tipa " + p.getPolje().getTip()
                );
            }

            p.setFormularPopunjen(formularPopunjen);
            return p;
        }).toList();

        formularPopunjen.setPoljePopunjenoList(listToSave);

        return formularPopunjenRepository.save(formularPopunjen);
    }



    public FormularPopunjen procitajFormularPopunjen(int id) {
        return formularPopunjenRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Popunjen formular " + id + " nije pronadjen")
                );
    }

    public FormularPopunjen procitajFormularPopunjenZaFormular(int idFormular) {
        return formularPopunjenRepository.findByFormularId(idFormular).orElseThrow(
                () -> new ResourceNotFoundException("Formular " + idFormular + " nema popunjen formular")
        );
    }

    public Stream<FormularPopunjen> procitajSveFormularePopunjene() {
        Iterable<FormularPopunjen> allFormularPopunjen = formularPopunjenRepository.findAll();
        return StreamSupport.stream(allFormularPopunjen.spliterator(), false);
    }

    public void obrisiFormularPopunjen(int id) {
        formularPopunjenRepository.deleteById(id);
    }

}
