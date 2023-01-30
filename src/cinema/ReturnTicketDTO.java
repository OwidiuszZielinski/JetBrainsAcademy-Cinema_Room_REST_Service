package cinema;

import java.util.Objects;
import java.util.UUID;

public class ReturnTicketDTO {
    private UUID token;

    public ReturnTicketDTO(UUID token) {
        this.token = token;
    }

    public ReturnTicketDTO() {
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnTicketDTO that = (ReturnTicketDTO) o;
        return Objects.equals(token ,that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    @Override
    public String toString() {
        return token.toString();
    }
}
