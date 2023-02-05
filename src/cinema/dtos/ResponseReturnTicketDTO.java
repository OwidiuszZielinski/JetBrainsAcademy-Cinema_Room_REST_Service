package cinema.dtos;

import cinema.model.Place;

import java.util.Objects;

public class ResponseReturnTicketDTO {
    private Place returned_ticket;

    public ResponseReturnTicketDTO(Place returned_ticket) {
        this.returned_ticket = returned_ticket;
    }



    public ResponseReturnTicketDTO() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseReturnTicketDTO that = (ResponseReturnTicketDTO) o;
        return Objects.equals(returned_ticket ,that.returned_ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(returned_ticket);
    }

    @Override
    public String toString() {
        return "ResponseReturnTicketDTO{" +
                "returned_ticket=" + returned_ticket +
                '}';
    }

    public Place getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(Place returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public static ResponseReturnTicketDTO mapper(TicketDTO ticket){
        return new ResponseReturnTicketDTO(ticket.getTicket());
    }
}
