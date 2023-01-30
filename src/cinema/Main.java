package cinema;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

     static int ROWS = 9;
     static int COLUMNS = 9;

    public Main(InMemoryPlacesRepository inMemoryPlacesRepository) {
        this.inMemoryPlacesRepository = inMemoryPlacesRepository;
    }

    private final InMemoryPlacesRepository inMemoryPlacesRepository;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        inMemoryPlacesRepository.initRepository(ROWS,COLUMNS);
    }
}
