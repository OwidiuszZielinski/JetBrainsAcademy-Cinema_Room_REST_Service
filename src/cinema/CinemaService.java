package cinema;

import cinema.dtos.PlaceDTO;
import cinema.dtos.ResponseReturnTicketDTO;
import cinema.dtos.ReturnTicketDTO;
import cinema.dtos.TicketDTO;
import cinema.model.Place;
import cinema.model.Stats;
import cinema.repository.SeatingRepository;
import cinema.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CinemaService {


    private final SeatingRepository seatingRepository;
    private final TokenRepository tokenRepository;


    public CinemaService(SeatingRepository seatingRepository, TokenRepository tokenRepository) {
        this.seatingRepository = seatingRepository;
        this.tokenRepository = tokenRepository;
    }


    public SeatingRepository getAllPlaces() {
        return seatingRepository;
    }

    //Exception handler


    public TicketDTO buyTicketWithToken(int row, int col) {
        UUID uuid = (TicketDTO.generateRandomUUID());
        Place buy = seatingRepository.delete(row, col);
        tokenRepository.save(buy, uuid);
        return new TicketDTO(uuid, buy);
    }

    public ResponseReturnTicketDTO getTicketByToken(ReturnTicketDTO uuid) {
        TicketDTO byToken = tokenRepository.getByToken(uuid);
        tokenRepository.delete(byToken);
        seatingRepository.save(
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
        int income = tokenRepository.income();
        int availableSeats = seatingRepository.getAvailable_seats().size();
        int purchasedTickets = tokenRepository.getTickets().size();
        return new Stats(income, availableSeats, purchasedTickets);

    }


}
