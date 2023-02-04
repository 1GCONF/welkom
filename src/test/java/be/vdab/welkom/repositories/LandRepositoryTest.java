package be.vdab.welkom.repositories;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class LandRepositoryTest {
    private final static String PAD = "/data/landen.csv";
    private LandRepository landRepository;
    @BeforeEach
    void beforeEach() {
        landRepository = new LandRepository(PAD);
    }
    @Test
    void deRepositoryGeeftEvenveelLandenAlsHetAantalRegels() throws IOException {
        try (var stream = Files.lines(Path.of(PAD))) {
            assertThat(landRepository.findAll().size()).isEqualTo(stream.count());
        }
    }
    @Test
    void hetEersteLandBevatDeDataUitDeEersteRegel() throws IOException {
        try (var stream = Files.lines(Path.of(PAD))) {
            stream.findFirst().ifPresent(eersteRegel -> {
                var eersteLand = landRepository.findAll().get(0);
                assertThat(eersteLand.getCode() + "," + eersteLand.getNaam() + "," +
                        eersteLand.getOppervlakte())
                        .isEqualTo(eersteRegel);
            });
        }
    }
}
