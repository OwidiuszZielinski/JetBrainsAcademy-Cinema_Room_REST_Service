package cinema.dtos;

import cinema.Main;
import cinema.annotations.CinemaHallSizeValidation;

public class PlaceDTO {

    @CinemaHallSizeValidation
    private int row;
    @CinemaHallSizeValidation
    private int column;


    public PlaceDTO(int row ,int column) {
        this.row = row;
        this.column = column;
    }

    public PlaceDTO() {
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }


}
