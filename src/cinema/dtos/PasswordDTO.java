package cinema.dtos;

public class PasswordDTO {
    private final String password;

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "PasswordDTO{" +
                "password='" + password + '\'' +
                '}';
    }

    public PasswordDTO(String password) {
        this.password = password;
    }
}
