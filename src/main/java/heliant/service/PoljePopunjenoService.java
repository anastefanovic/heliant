package heliant.service;

import heliant.entity.FormularPopunjen;
import heliant.entity.Korisnik;
import heliant.entity.Polje;
import heliant.entity.PoljePopunjeno;
import heliant.enumeration.Tip;
import heliant.exception.ResourceNotFoundException;
import heliant.exception.ResourceNotValidException;
import heliant.exception.TypeNotSupportedException;
import heliant.repository.FormularPopunjenRepository;
import heliant.repository.PoljePopunjenoRepository;
import heliant.repository.PoljeRepository;
import heliant.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PoljePopunjenoService {

    private final PoljePopunjenoRepository poljePopunjenoRepository;
    private final FormularPopunjenRepository formularPopunjenRepository;
    private final PoljeRepository poljeRepository;
    private final AuthenticationService authenticationService;

    public PoljePopunjeno kreirajPoljePopunjeno(
            int idFormularPopunjen,
            int idPolje,
            PoljePopunjeno poljepopunjeno) {
        FormularPopunjen formularPopunjen = formularPopunjenRepository.findById(idFormularPopunjen)
                .orElseThrow(()->
                        new ResourceNotValidException("Popunjen formular " + idFormularPopunjen + " ne postoji")
                );

        Polje polje = poljeRepository.findById(idPolje)
                .orElseThrow(()->
                        new ResourceNotValidException("Polje " + idPolje + " ne postoji ")
        );

        poljepopunjeno.setPolje(polje);
        poljepopunjeno.setFormularPopunjen(formularPopunjen);

        Korisnik korisnikKreirao = authenticationService.extractUser();
        poljepopunjeno.setKorisnikKreirao(korisnikKreirao);

        if(polje.getFormular().getId() != formularPopunjen.getFormular().getId()) {
            throw new ResourceNotValidException(
                    "Polje " + polje.getId() + " ne pripada formularu " + formularPopunjen.getFormular().getId()
            );
        }

        if(poljePopunjenoRepository.findByFormularPopunjenIdAndPoljeId(formularPopunjen.getId(), polje.getId())
                .isPresent()) {
            throw new ResourceNotValidException("Polje " + polje.getId() + " je vec popunjeno");
        }

        if(!poljeTypeValid(poljepopunjeno)) {
            throw new TypeNotSupportedException(
                    "Polje mora biti popunjeno vrednoscu tipa " + polje.getTip()
            );
        }

        return poljePopunjenoRepository.save(poljepopunjeno);
    }

    boolean poljeTypeValid(PoljePopunjeno poljePopunjeno) {
        Tip tip = poljePopunjeno.getPolje().getTip();
        return (tip == Tip.BROJ && poljePopunjeno.getVrednostBroj() != 0)
                || (tip == Tip.TEKST && poljePopunjeno.getVrednostTekst() != null);
    }

    public PoljePopunjeno procitajPoljePopunjeno(int id) {
        return poljePopunjenoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Polje popunjeno " + id + " ne postoji")
        );
    }

    public Stream<PoljePopunjeno> procitajSvaPoljaPopunjenaZaFormularPopunjen(int idFormularPopunjen) {
        if(!formularPopunjenRepository.existsById(idFormularPopunjen)) {
            throw new ResourceNotFoundException("Popunjen formular " + idFormularPopunjen + " ne postoji");
        }

        Iterable<PoljePopunjeno> allPoljePopunjeno = poljePopunjenoRepository.findAllByFormularPopunjenId(idFormularPopunjen);
        return StreamSupport.stream(allPoljePopunjeno.spliterator(), false);
    }

    public PoljePopunjeno azurirajPoljePopunjeno(int id, PoljePopunjeno poljePopunjeno) {
        PoljePopunjeno poljePopunjenoToUpdate = poljePopunjenoRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Popunjeno polje " + id + " ne postoji")
                );

        poljePopunjenoToUpdate.setVrednostTekst(poljePopunjeno.getVrednostTekst());
        poljePopunjenoToUpdate.setVrednostBroj(poljePopunjeno.getVrednostBroj());

        Korisnik korisnikPoslednjiAzurirao = authenticationService.extractUser();
        poljePopunjenoToUpdate.setKorisnikPoslednjiAzurirao(korisnikPoslednjiAzurirao);

        return poljePopunjenoRepository.save(poljePopunjenoToUpdate);
    }

    public void obrisiPoljePopunjeno(int id) {
        poljePopunjenoRepository.deleteById(id);
    }

}
