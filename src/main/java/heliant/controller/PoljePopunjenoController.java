package heliant.controller;


import heliant.dto.PoljePopunjenoDto;
import heliant.entity.PoljePopunjeno;
import heliant.mapper.PoljePopunjenoMapper;
import heliant.service.PoljePopunjenoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;


@RestController
@RequestMapping("/polje_popunjeno")
@RequiredArgsConstructor
public class PoljePopunjenoController {

    private final PoljePopunjenoMapper poljePopunjenoMapper;
    private final PoljePopunjenoService poljePopunjenoService;

    @PostMapping("/create")
    public PoljePopunjenoDto kreirajPoljePopunjeno(@RequestBody PoljePopunjenoDto poljePopunjenoDto) {
        PoljePopunjeno poljePopunjeno = poljePopunjenoMapper.dtoToEntity(poljePopunjenoDto);
        PoljePopunjeno created = poljePopunjenoService.kreirajPoljePopunjeno(
                poljePopunjenoDto.getIdFormularPopunjen(),
                poljePopunjenoDto.getIdPolje(),
                poljePopunjeno);
        return poljePopunjenoMapper.entityToDto(created);
    }

    @GetMapping("/read/{id}")
    public PoljePopunjenoDto procitajPoljePopunjeno(@PathVariable int id) {
        PoljePopunjeno poljePopunjeno = poljePopunjenoService.procitajPoljePopunjeno(id);
        return poljePopunjenoMapper.entityToDto(poljePopunjeno);
    }

    @GetMapping("/read/all/{idFormularPopunjen}")
    public List<PoljePopunjenoDto> procitajSvaPoljaPopunjenaZaFormularPopunjen(@PathVariable int idFormularPopunjen) {
        Stream<PoljePopunjeno> allPoljePopunjeno = poljePopunjenoService
                .procitajSvaPoljaPopunjenaZaFormularPopunjen(idFormularPopunjen);
        return allPoljePopunjeno.map(poljePopunjenoMapper::entityToDto).toList();
    }

    @PutMapping("/update")
    public PoljePopunjenoDto azurirajPoljePopunjeno(@RequestBody PoljePopunjenoDto poljePopunjenoDto) {
        PoljePopunjeno poljePopunjeno = poljePopunjenoMapper.dtoToEntity(poljePopunjenoDto);
        PoljePopunjeno updatedPoljePopunjeno = poljePopunjenoService
                .azurirajPoljePopunjeno(poljePopunjenoDto.getId(), poljePopunjeno);
        return poljePopunjenoMapper.entityToDto(updatedPoljePopunjeno);
    }

    @DeleteMapping("/delete/{id}")
    public void obrisiPoljePopunjeno(@PathVariable int id) {
        poljePopunjenoService.obrisiPoljePopunjeno(id);
    }

}
