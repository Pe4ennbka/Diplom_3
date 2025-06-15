package Personal;

import java.util.Random;

public class Client {
    private String email;
    private String password;
    private String name;

    public Client(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static Client random(){
        int suff = new Random().nextInt(1000000);
        return new Client("kolia" + suff + "@yandex.com", "1234567", "Nikolai");
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
