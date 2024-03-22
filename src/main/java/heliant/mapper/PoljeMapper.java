package heliant.mapper;

import heliant.dto.PoljeDto;
import heliant.entity.Korisnik;
import heliant.entity.Polje;
import org.springframework.stereotype.Component;

@Component
public class PoljeMapper implements Mapper<PoljeDto, Polje> {
    @Override
    public Polje dtoToEntity(PoljeDto dto) {
        return new Polje(
                dto.getNaziv(),
                dto.getTip()
        );
    }

    @Override
    public PoljeDto entityToDto(Polje entity) {
        Korisnik korisnikKreirao = entity.getKorisnikKreirao();
        Korisnik korisnikPoslednjiAzurirao = entity.getKorisnikPoslednjiAzurirao();

        return new PoljeDto(
                entity.getId(),
                entity.getNaziv(),
                entity.getPrikazniRedosled(),
                entity.getTip(),
                entity.getVremeKreiranja(),
                entity.getVremePoslednjeIzmene(),
                entity.getFormular().getId(),
                (korisnikKreirao != null) ? korisnikKreirao.getId() : -1,
                (korisnikPoslednjiAzurirao != null) ? korisnikPoslednjiAzurirao.getId() : -1
        );
    }
}
