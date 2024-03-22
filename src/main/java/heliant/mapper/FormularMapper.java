package heliant.mapper;

import heliant.dto.FormularDto;
import heliant.entity.Formular;
import org.springframework.stereotype.Component;

@Component
public class FormularMapper implements Mapper<FormularDto, Formular> {

    @Override
    public Formular dtoToEntity(FormularDto dto) {
        return new Formular(dto.getNaziv());
    }

    @Override
    public FormularDto entityToDto(Formular entity) {
        return new FormularDto(
                entity.getId(),
                entity.getNaziv(),
                entity.getVremeKreiranja(),
                entity.getVremePoslednjeIzmene()
        );
    }

}
