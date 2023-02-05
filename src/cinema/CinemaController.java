package cinema;

import cinema.dtos.*;
import cinema.model.Stats;
import cinema.repository.SeatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@RestController
public class CinemaController {

    @ExceptionHandler({ IllegalArgumentException.class, NoSuchElementException.class })
    public ResponseEntity<MyErrorResponse> handleException(IllegalArgumentException ex) {
        return new ResponseEntity<>(new MyErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;

    }


    @GetMapping("/seats")
    SeatingRepository getHall() {
        return cinemaService.getAllPlaces();
    }

    @PostMapping("/return")
    ResponseReturnTicketDTO returnTicket(@RequestBody ReturnTicketDTO token) {
        return cinemaService.getTicketByToken(token);
    }

    @PostMapping("/purchase")
    TicketDTO getSeatWithToken(@Valid @RequestBody PlaceDTO seatDTO) {
        return cinemaService.buyTicketWithToken(seatDTO.getRow(), seatDTO.getColumn());
    }

    @PostMapping("/stats")
    ResponseEntity getStats(@RequestParam String password) {
        try {
            Stats stats = cinemaService.getStats(password);
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (RuntimeException error) {
            return new ResponseEntity<>(new MyErrorResponse("The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
    }

}
