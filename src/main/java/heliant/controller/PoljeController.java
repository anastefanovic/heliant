package heliant.controller;

import heliant.dto.PoljeDto;
import heliant.entity.Polje;
import heliant.mapper.PoljeMapper;
import heliant.service.PoljeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/polje")
@RequiredArgsConstructor
public class PoljeController {

    private final PoljeMapper poljeMapper;
    private final PoljeService poljeService;

    @PostMapping("/create")
    public PoljeDto kreirajPolje(@RequestBody PoljeDto poljeDto) {
        Polje polje = poljeMapper.dtoToEntity(poljeDto);
        Polje created = poljeService.kreirajPolje(poljeDto.getIdFormular(), polje);
        return poljeMapper.entityToDto(created);
    }

    @GetMapping("/read/{id}")
    public PoljeDto procitajPolje(@PathVariable int id) {
        Polje polje = poljeService.procitajPolje(id);
        return poljeMapper.entityToDto(polje);
    }

    @GetMapping("/read/all/{idFormular}")
    public List<PoljeDto> procitajSvaPoljaZaFormular(@PathVariable int idFormular) {
        Stream<Polje> allPolje = poljeService.procitajSvaPoljaZaFormular(idFormular);
        return allPolje.map(poljeMapper::entityToDto).toList();
    }

    @PutMapping("/update")
    public PoljeDto azurirajPolje(@RequestBody PoljeDto poljeDto) {
        Polje polje = poljeMapper.dtoToEntity(poljeDto);
        Polje updatedPolje = poljeService.azurirajPolje(poljeDto.getId(), polje);
        return poljeMapper.entityToDto(updatedPolje);
    }

    @DeleteMapping("/delete/{id}")
    public void obrisiPolje(@PathVariable int id) {
        poljeService.obrisiPolje(id);
    }
}
