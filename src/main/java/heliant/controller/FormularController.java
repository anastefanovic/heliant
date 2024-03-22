package heliant.controller;

import heliant.dto.FormularDto;
import heliant.entity.Formular;
import heliant.mapper.FormularMapper;
import heliant.service.FormularService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/formular")
@RequiredArgsConstructor
public class FormularController {

    private final FormularMapper formularMapper;
    private final FormularService formularService;

    @PostMapping("/create")
    public FormularDto kreirajFormular(@RequestBody FormularDto formularDto) {
        Formular formular = formularMapper.dtoToEntity(formularDto);
        Formular created = formularService.kreirajFormular(formular);
        return formularMapper.entityToDto(created);
    }

    @GetMapping("/read/{id}")
    public FormularDto procitajFormular(@PathVariable int id) {
        Formular formular = formularService.procitajFormular(id);
        return formularMapper.entityToDto(formular);
    }

    @GetMapping("/read")
    public List<FormularDto> procitajSveFormulare() {
        Stream<Formular> allFormular = formularService.procitajSveFormulare();
        return allFormular.map(formularMapper::entityToDto).toList();
    }

    @PutMapping("/update")
    public FormularDto azurirajFormular(@RequestBody FormularDto formularDto) {
        Formular formular = formularMapper.dtoToEntity(formularDto);
        Formular updatedFormular = formularService.azurirajFormular(formularDto.getId(), formular);
        return formularMapper.entityToDto(updatedFormular);
    }

    @DeleteMapping("/delete/{id}")
    public void obrisiFormular(@PathVariable int id) {
        formularService.obrisiFormular(id);
    }

}
