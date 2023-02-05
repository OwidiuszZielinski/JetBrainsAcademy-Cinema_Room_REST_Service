package cinema;

public class PlaceDTO {

    private int row;
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

    public static boolean checkNumbers(int col, int row){
        return col > Main.COLUMNS || row > Main.ROWS || col < 1 || row < 1;
    }
}
