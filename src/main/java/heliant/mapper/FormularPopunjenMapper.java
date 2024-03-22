package heliant.mapper;

import heliant.dto.FormularPopunjenDto;
import heliant.entity.FormularPopunjen;
import org.springframework.stereotype.Component;

@Component
public class FormularPopunjenMapper implements Mapper<FormularPopunjenDto, FormularPopunjen> {
    @Override
    public FormularPopunjen dtoToEntity(FormularPopunjenDto dto) {
        return new FormularPopunjen();
    }

    @Override
    public FormularPopunjenDto entityToDto(FormularPopunjen entity) {
        return new FormularPopunjenDto(
                entity.getId(),
                entity.getVremeKreiranja(),
                entity.getVremePoslednjeIzmene(),
                entity.getFormular().getId()
        );
    }
}
