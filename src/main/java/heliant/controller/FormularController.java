package heliant.controller;

import heliant.dto.FormularDto;
import heliant.dto.PageableFormularDto;
import heliant.entity.Formular;
import heliant.mapper.FormularMapper;
import heliant.service.FormularService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public PageableFormularDto procitajSveFormulare(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize
    ) {
        Page<Formular> formularPage = formularService.procitajSveFormulare(pageNumber, pageSize);
        List<FormularDto> formularList = formularPage.getContent()
                .stream().map(formularMapper::entityToDto).toList();

        return new PageableFormularDto(
                formularList,
                formularPage.getNumber(),
                formularPage.getSize(),
                formularPage.getTotalElements(),
                formularPage.getTotalPages()
        );
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
