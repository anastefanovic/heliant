package heliant.mapper;

import heliant.dto.PoljePopunjenoDto;
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
        return new PoljePopunjenoDto(
                entity.getId(),
                entity.getVrednostTekst(),
                entity.getVrednostBroj(),
                entity.getVremeKreiranja(),
                entity.getVremePoslednjeIzmene(),
                entity.getFormularPopunjen().getId(),
                entity.getPolje().getId()
        );
    }
}
