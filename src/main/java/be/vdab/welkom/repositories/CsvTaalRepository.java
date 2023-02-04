package be.vdab.welkom.repositories;
import be.vdab.welkom.domain.Taal;
import be.vdab.welkom.exceptions.RepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
@Component
@Qualifier("CSV")
public class CsvTaalRepository implements TaalRepository {
    private final String pad;
    public CsvTaalRepository(@Value("${talenCsvPad}") String pad) {
        this.pad = pad;
    }
    @Override
    public List<Taal> findAll() {
        try (var stream = Files.lines(Path.of("/data/talen.csv"))) {
            return stream.map(regel -> regel.split(",")).map(regelOnderdeel -> new Taal(regelOnderdeel[0], regelOnderdeel[1])).toList();
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new RepositoryException(e);
        }
    }
}
