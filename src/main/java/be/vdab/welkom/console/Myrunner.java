package be.vdab.welkom.console;
import be.vdab.welkom.exceptions.RepositoryException;
import be.vdab.welkom.repositories.LandRepository;
import be.vdab.welkom.repositories.TaalRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
@Component
public class Myrunner implements CommandLineRunner {
    private final LandRepository landRepository;
    private final TaalRepository taalRepository;
    public Myrunner(LandRepository landRepository, @Qualifier("XML") TaalRepository taalRepository) {
        this.landRepository = landRepository;
        this.taalRepository = taalRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        try {
            landRepository.findAll().forEach(land -> System.out.println(land.getCode() + " " + land.getNaam() + " " + land.getOppervlakte()));
            taalRepository.findAll().forEach(taal -> System.out.println(taal.getCode() + " : " + taal.getNaam()));
        } catch (RepositoryException e) {
            e.printStackTrace(System.err);
        }
    }
}
