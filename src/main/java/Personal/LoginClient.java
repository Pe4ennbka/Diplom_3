package Personal;

public class LoginClient {
    private final String email;
    private final String password;

    public LoginClient(String email, String password){
        this.email = email;
        this.password = password;

    }

    public static LoginClient fromClient(Client client){
        return new LoginClient(client.getEmail(), client.getPassword());
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
