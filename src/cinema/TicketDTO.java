package cinema;


import java.util.Objects;
import java.util.UUID;

public class TicketDTO {
    private UUID token;
    private Place ticket;

    public TicketDTO() {
    }

    public TicketDTO(UUID token ,Place ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Place getTicket() {
        return ticket;
    }

    public void setTicket(Place ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketDTO that = (TicketDTO) o;
        return Objects.equals(token ,that.token) && Objects.equals(ticket ,that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token ,ticket);
    }

    @Override
    public String toString() {
        return "ResponseBuyDTO{" +
                "token='" + token + '\'' +
                ", ticket=" + ticket +
                '}';
    }


    public static UUID generateRandomUUID(){
        return UUID.randomUUID();
    }
}
