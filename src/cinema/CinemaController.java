package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public InMemoryPlacesRepository getHall() {
        return cinemaService.getAllPlaces();
    }

    @PostMapping("/return")
    ResponseReturnTicketDTO returnTicket(@RequestBody ReturnTicketDTO token) {
        return cinemaService.getTicketByToken(token);
    }

    @PostMapping("/purchase")
    public TicketDTO getSeatWithToken(@RequestBody PlaceDTO seatDTO) {
        return cinemaService.buyTicketWithToken(seatDTO.getRow(), seatDTO.getColumn());
    }

    @PostMapping("/stats")
    public ResponseEntity getStats(@RequestParam String password) {
        try {
            Stats stats = cinemaService.getStats(password);
            return new ResponseEntity<>(stats, HttpStatus.OK);
        } catch (RuntimeException error) {
            return new ResponseEntity<>(new MyErrorResponse("The password is wrong!"), HttpStatus.UNAUTHORIZED);
        }
    }

}
