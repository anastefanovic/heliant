package heliant.mapper;

import heliant.dto.FormularDto;
import heliant.entity.Formular;
import heliant.entity.Korisnik;
import org.springframework.stereotype.Component;

@Component
public class FormularMapper implements Mapper<FormularDto, Formular> {

    @Override
    public Formular dtoToEntity(FormularDto dto) {
        return new Formular(dto.getNaziv());
    }

    @Override
    public FormularDto entityToDto(Formular entity) {
        Korisnik korisnikKreirao = entity.getKorisnikKreirao();
        Korisnik korisnikPoslednjiAzurirao = entity.getKorisnikPoslednjiAzurirao();

        return new FormularDto(
                entity.getId(),
                entity.getNaziv(),
                entity.getVremeKreiranja(),
                entity.getVremePoslednjeIzmene(),
                (korisnikKreirao != null) ? korisnikKreirao.getId() : -1,
                (korisnikPoslednjiAzurirao != null) ? korisnikPoslednjiAzurirao.getId() : -1
        );
    }

}
