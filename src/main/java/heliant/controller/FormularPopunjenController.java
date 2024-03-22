package heliant.controller;

import heliant.dto.FormularPopunjenDto;
import heliant.dto.PoljePopunjenoDto;
import heliant.entity.FormularPopunjen;
import heliant.entity.Polje;
import heliant.entity.PoljePopunjeno;
import heliant.mapper.FormularPopunjenMapper;
import heliant.mapper.PoljePopunjenoMapper;
import heliant.service.FormularPopunjenService;
import heliant.service.PoljeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/formular_popunjen")
@RequiredArgsConstructor
public class FormularPopunjenController {

    private final FormularPopunjenService formularPopunjenService;
    private final PoljeService poljeService;
    private final FormularPopunjenMapper formularPopunjenMapper;
    private final PoljePopunjenoMapper poljePopunjenoMapper;

    @PostMapping("/create/{idFormular}")
    public FormularPopunjenDto kreirajFormularPopunjen(
            @PathVariable int idFormular,
            @RequestBody List<PoljePopunjenoDto> poljePopunjenoDtoList
    ) {
        List<PoljePopunjeno> poljePopunjenoList = poljePopunjenoDtoList.stream()
                .map(poljePopunjenoDto -> {
                    Polje polje = poljeService.procitajPolje(poljePopunjenoDto.getIdPolje());
                    PoljePopunjeno poljePopunjeno = poljePopunjenoMapper.dtoToEntity(poljePopunjenoDto);
                    poljePopunjeno.setPolje(polje);
                    return poljePopunjeno;
                }).toList();

        FormularPopunjen created = formularPopunjenService.kreirajFormularPopunjen(idFormular, poljePopunjenoList);

        return formularPopunjenMapper.entityToDto(created);
    }

    @GetMapping("/read/{id}")
    public FormularPopunjenDto procitajFormularPopunjen(@PathVariable int id) {
        FormularPopunjen formularPopunjen = formularPopunjenService.procitajFormularPopunjen(id);
        return formularPopunjenMapper.entityToDto(formularPopunjen);
    }

    @GetMapping("/read/by/formular/{idFormular}")
    public FormularPopunjenDto procitajFormularPopunjenZaFormular(@PathVariable int idFormular) {
        FormularPopunjen formularPopunjen = formularPopunjenService.procitajFormularPopunjenZaFormular(idFormular);
        return formularPopunjenMapper.entityToDto(formularPopunjen);
    }

    @GetMapping("/read")
    public List<FormularPopunjenDto> procitajSveFormularePopunjene() {
        Stream<FormularPopunjen> allFormularPopunjen = formularPopunjenService.procitajSveFormularePopunjene();
        return allFormularPopunjen.map(formularPopunjenMapper::entityToDto).toList();
    }

    @DeleteMapping("/delete/{id}")
    public void obrisiFormularPopunjen(@PathVariable int id) {
        formularPopunjenService.obrisiFormularPopunjen(id);
    }

}
