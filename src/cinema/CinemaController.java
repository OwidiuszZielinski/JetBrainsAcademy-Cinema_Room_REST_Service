package cinema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;
    private final ObjectMapper objectMapper;

    public CinemaController(CinemaService cinemaService ,ObjectMapper objectMapper) {
        this.cinemaService = cinemaService;
        this.objectMapper = objectMapper;
    }


    @GetMapping("/seats")
    public InMemoryPlacesRepository getHall() {
        return cinemaService.getAllPlaces();
    }

    @PostMapping("/return")
    public ResponseEntity returnTicket(@RequestBody ReturnTicketDTO token) throws JsonProcessingException {
        try {
            ResponseReturnTicketDTO ticketByToken = cinemaService.getTicketByToken(token);
            return new ResponseEntity<>(ticketByToken ,HttpStatus.OK);
        } catch (NoSuchElementException e) {
            String responseBAD = objectMapper.writeValueAsString(new Error("Wrong token!"));
            return new ResponseEntity<>(responseBAD ,HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/purchase")
    public ResponseEntity<String> getSeatWithToken(@RequestBody PlaceDTO seatDTO) throws JsonProcessingException {
        String responseOK = "";
        if (PlaceDTO.checkNumbers(seatDTO)) {
            String response = objectMapper.writeValueAsString(new Error("The number of a row or a column is out of bounds!"));
            return new ResponseEntity<>(response ,HttpStatus.BAD_REQUEST);
        }
        try {
            TicketDTO ticketDTO = cinemaService.buyTicketWithToken(seatDTO.getRow() ,seatDTO.getColumn());
            responseOK = objectMapper.writeValueAsString(ticketDTO);
            return new ResponseEntity<>(responseOK ,HttpStatus.OK);
        } catch (NoSuchElementException e) {
            String responseBAD = objectMapper.writeValueAsString(new Error("The ticket has been already purchased!"));
            return new ResponseEntity<>(responseBAD ,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/stats")
    public ResponseEntity getStats(@RequestParam String password){
        try {
            Stats stats = cinemaService.getStats(password);
            return new ResponseEntity<>(stats,HttpStatus.OK);
        } catch (RuntimeException error){
            return new ResponseEntity<>(new Error("The password is wrong!"),HttpStatus.UNAUTHORIZED);
        }
    }

}
