import PageObject.PageLogin;
import PageObject.PageMain;
import PageObject.PageRegistration;
import Personal.Client;
import Personal.ClientCheck;
import Personal.LoginClient;
import Personal.RequestClient;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestRegistration {
    DriverFactor factory = new DriverFactor();
    private WebDriver driver;
    RequestClient clientRequest = new RequestClient();
    ClientCheck clientCheck = new ClientCheck();
    Client client;

    private final String name;
    private final String email;
    private final String goodPassword;
    private final String badPassword;

    public TestRegistration(String name, String email, String goodPassword, String badPassword){
        this.name = name;
        this.email = email;
        this.goodPassword = goodPassword;
        this.badPassword = badPassword;
        this.client = new Client(email, goodPassword, name);
    }

    @Parameterized.Parameters
    public static Object[][] getPersonalData(){
        int suff = new Random().nextInt(1000000);
        return new Object[][]{
                {"Kolia","nikolia" + suff + "@yandex.com", "123456", "1234"},
        };
        }

    @Before
    public void initDrive() throws Exception {
        factory.initDriver();
        driver = factory.getDriver();
        factory.getWebOpen();

    }
    @Test
    public void correctRegirtration() throws Exception {
        driver = factory.getDriver();

        PageMain main = new PageMain(driver);
        ObjectHeader objectHeader = new ObjectHeader(driver);
        PageRegistration pageRegistration = new PageRegistration(driver);
        PageLogin pageLogin = new PageLogin(driver);

        objectHeader.clickButterPersonalAccount();

        pageLogin.clickRegistration();

        pageRegistration.registration(name, email, goodPassword);

        Thread.sleep(2_000);

        pageLogin.logIn(email, goodPassword);

        Thread.sleep(2_000);
        String actualUrl = driver.getCurrentUrl();

        assertTrue( "После регистрации не произошел переход на главную страницу! Текущий URL: " + actualUrl,
                actualUrl.equals(Url.MAIN.getUrl()));

        assertEquals( "Оформить заказ", main.button());

        var login = LoginClient.fromClient(client);
        ValidatableResponse loginResponse = clientRequest.loginClient(login);
        String accessToken = clientCheck.loginCourier(loginResponse);
        ValidatableResponse delete =  clientRequest.delete(accessToken);
        clientCheck.deleteClient(delete);
    }
    @Test
    public void regirtrationBadPassword() throws Exception {
        driver = factory.getDriver();

        ObjectHeader objectHeader = new ObjectHeader(driver);
        PageRegistration pageRegistration = new PageRegistration(driver);
        PageLogin pageLogin = new PageLogin(driver);

        objectHeader.clickButterPersonalAccount();

        pageLogin.clickRegistration();

        pageRegistration.registration(name, email, badPassword);

        Thread.sleep(2_000);

        assertEquals( "Некорректный пароль", pageRegistration.getTextIncorrectPassword());
    }
    @After
    public void setDown(){
        factory.webQuit();
    }
}
