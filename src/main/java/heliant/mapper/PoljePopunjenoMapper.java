package heliant.mapper;

import heliant.dto.PoljePopunjenoDto;
import heliant.entity.Korisnik;
import heliant.entity.PoljePopunjeno;
import org.springframework.stereotype.Component;

@Component
public class PoljePopunjenoMapper implements Mapper<PoljePopunjenoDto, PoljePopunjeno> {
    @Override
    public PoljePopunjeno dtoToEntity(PoljePopunjenoDto dto) {
        return new PoljePopunjeno(
                dto.getVrednostTekst(),
                dto.getVrednostBroj()
        );
    }

    @Override
    public PoljePopunjenoDto entityToDto(PoljePopunjeno entity) {
        Korisnik korisnikKreirao = entity.getKorisnikKreirao();
        Korisnik korisnikPoslednjiAzurirao = entity.getKorisnikPoslednjiAzurirao();

        return new PoljePopunjenoDto(
                entity.getId(),
                entity.getVrednostTekst(),
                entity.getVrednostBroj(),
                entity.getVremeKreiranja(),
                entity.getVremePoslednjeIzmene(),
                entity.getFormularPopunjen().getId(),
                entity.getPolje().getId(),
                (korisnikKreirao != null) ? korisnikKreirao.getId() : -1,
                (korisnikPoslednjiAzurirao != null) ? korisnikPoslednjiAzurirao.getId() : -1
        );
    }
}
