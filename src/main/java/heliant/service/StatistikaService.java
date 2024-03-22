package heliant.service;

import heliant.entity.Statistika;
import heliant.repository.FormularPopunjenRepository;
import heliant.repository.StatistikaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StatistikaService {

    private final FormularPopunjenRepository formularPopunjenRepository;
    private final StatistikaRepository statistikaRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void createStatistika() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        int brojPopunjenihFormulara = formularPopunjenRepository.countFormularPopunjenByDate(yesterday);

        Statistika statistika = new Statistika(
                yesterday,
                brojPopunjenihFormulara
        );

        statistikaRepository.save(statistika);
    }

}
