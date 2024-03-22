package heliant.mapper;

import heliant.dto.PoljeDto;
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
        return new PoljeDto(
                entity.getId(),
                entity.getNaziv(),
                entity.getPrikazniRedosled(),
                entity.getTip(),
                entity.getVremeKreiranja(),
                entity.getVremePoslednjeIzmene(),
                entity.getFormular().getId()
        );
    }
}
