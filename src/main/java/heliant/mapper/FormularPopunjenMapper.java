package heliant.mapper;

import heliant.dto.FormularPopunjenDto;
import heliant.entity.FormularPopunjen;
import heliant.entity.Korisnik;
import org.springframework.stereotype.Component;

@Component
public class FormularPopunjenMapper implements Mapper<FormularPopunjenDto, FormularPopunjen> {
    @Override
    public FormularPopunjen dtoToEntity(FormularPopunjenDto dto) {
        return new FormularPopunjen();
    }

    @Override
    public FormularPopunjenDto entityToDto(FormularPopunjen entity) {
        Korisnik korisnikKreirao = entity.getKorisnikKreirao();
        Korisnik korisnikPoslednjiAzurirao = entity.getKorisnikPoslednjiAzurirao();

        return new FormularPopunjenDto(
                entity.getId(),
                entity.getVremeKreiranja(),
                entity.getVremePoslednjeIzmene(),
                entity.getFormular().getId(),
                (korisnikKreirao != null) ? korisnikKreirao.getId() : -1,
                (korisnikPoslednjiAzurirao != null) ? korisnikPoslednjiAzurirao.getId() : -1
        );
    }
}
