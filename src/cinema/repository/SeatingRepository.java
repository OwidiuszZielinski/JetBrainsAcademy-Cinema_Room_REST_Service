package cinema.repository;

import cinema.Main;
import cinema.model.Place;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SeatingRepository implements MemoryRepository {
    @Value("${ROWS}")
    private int total_rows;
    @Value("${COLUMNS}")
    private int total_columns;
    private Set<Place> available_seats = new HashSet<>();


    public SeatingRepository() {
    }

    public SeatingRepository(int total_rows , int total_columns , Set<Place> available_seats) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = available_seats;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public Set<Place> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Set<Place> available_seats) {
        this.available_seats = available_seats;
    }

    public void initRepository() {
        for (int i = 1; i <= total_rows; i++) {
            for (int j = 1; j <= total_columns; j++) {
                if (i <= 4) {
                    available_seats.add(new Place(i ,j ,10));
                } else
                    available_seats.add(new Place(i ,j ,8));
            }
        }
    }

    @Override
    public Place save(int row ,int column ,int price) {
        Place toSave = new Place(row ,column ,price);
        available_seats.add(toSave);
        return toSave;
    }

    @Override
    public Place delete(int row ,int column) {
        Place ret = available_seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getColumn() == column).findFirst()
                .orElseThrow(() ->new IllegalArgumentException("This seat is not available"));
        available_seats.removeIf(seat -> seat.getRow() == row && seat.getColumn() == column);
        return ret;
    }


}
