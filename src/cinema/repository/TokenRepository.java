package cinema.repository;

import cinema.model.Place;
import cinema.dtos.ReturnTicketDTO;
import cinema.dtos.TicketDTO;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Repository
public class TokenRepository {



    private final Set<TicketDTO> tickets = new HashSet<>();

    public Set<TicketDTO> getTickets() {
        return tickets;
    }

    public void save(Place seat, UUID uuid) {
        TicketDTO ticket = new TicketDTO(uuid ,seat);
        tickets.add(ticket);
    }

    public TicketDTO getByToken(ReturnTicketDTO uuid){
        for (TicketDTO x : tickets) {
            if(x.getToken().toString().equals(uuid.toString())){
                return x;
            }
        }
        throw new IllegalArgumentException("Wrong Token !");

    }

    public void delete(TicketDTO ticketDTO){
        tickets.remove(ticketDTO);
    }

    public int income(){
        return tickets.stream()
                .map(ticketDTO -> ticketDTO.getTicket()
                .getPrice()).reduce(Integer::sum)
                .orElse(0);
    }


}
