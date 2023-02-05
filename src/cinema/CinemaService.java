package cinema;

import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CinemaService {


    private final InMemoryPlacesRepository inMemoryPlacesRepository;
    private final InMemoryTokenRepository inMemoryTokenRepository;


    public CinemaService(InMemoryPlacesRepository inMemoryPlacesRepository, InMemoryTokenRepository inMemoryTokenRepository) {
        this.inMemoryPlacesRepository = inMemoryPlacesRepository;
        this.inMemoryTokenRepository = inMemoryTokenRepository;
    }


    public InMemoryPlacesRepository getAllPlaces() {
        return inMemoryPlacesRepository;
    }

    //Exception handler


    public TicketDTO buyTicketWithToken(int row, int col) {
        if (PlaceDTO.checkNumbers(col,row)) {
            throw new IllegalArgumentException("The number of a row or a column is out of bounds!");
        }
        UUID uuid = (TicketDTO.generateRandomUUID());
        Place buy = inMemoryPlacesRepository.delete(row, col);
        inMemoryTokenRepository.save(buy, uuid);
        return new TicketDTO(uuid, buy);
    }

    public ResponseReturnTicketDTO getTicketByToken(ReturnTicketDTO uuid) {
        TicketDTO byToken = inMemoryTokenRepository.getByToken(uuid);
        inMemoryTokenRepository.delete(byToken);
        inMemoryPlacesRepository.save(
                byToken.getTicket().getRow(),
                byToken.getTicket().getColumn(),
                byToken.getTicket().getPrice()
        );
        return ResponseReturnTicketDTO.mapper(byToken);
    }

    public Stats getStats(String password) {
        if (password.equals(Stats.getPassword())) {
            return stats();
        }
        throw new IllegalArgumentException("Bad password");
    }

    private Stats stats() {
        int income = inMemoryTokenRepository.income();
        int availableSeats = inMemoryPlacesRepository.getAvailable_seats().size();
        int purchasedTickets = inMemoryTokenRepository.getTickets().size();
        return new Stats(income, availableSeats, purchasedTickets);

    }


}
