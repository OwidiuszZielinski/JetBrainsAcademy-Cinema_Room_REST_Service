package cinema.repository;

import cinema.model.Place;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository {
    Place save(int row, int column, int price);
    Place delete(int row,int column);
}
