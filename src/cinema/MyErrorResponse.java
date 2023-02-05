package cinema;

public class MyErrorResponse {


    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public MyErrorResponse(String error) {
        this.error = error;
    }
}
