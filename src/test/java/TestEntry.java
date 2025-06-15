import PageObject.PageLogin;
import PageObject.PageMain;
import PageObject.PageRecoveryPassword;
import PageObject.PageRegistration;
import Personal.Client;
import Personal.ClientCheck;
import Personal.RequestClient;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestEntry {
    RequestClient clientRequest = new RequestClient();
    ClientCheck clientCheck = new ClientCheck();
    Client client;
    DriverFactor factory = new DriverFactor();
    private WebDriver driver;
    private String accessToken;
    

    @Before
    public void initDrive() throws Exception {
        client = Client.random();
        ValidatableResponse createResponse = clientRequest.createClient(client);
        accessToken = clientCheck.createdClient(createResponse, client);
        factory.initDriver();
        driver = factory.getDriver();
        factory.getWebOpen();
    }
    @Test
    public void entryMainPage() throws InterruptedException {
        driver = factory.getDriver();
        PageLogin pageLogin = new PageLogin(driver);
        PageMain main = new PageMain(driver);

        main.clickEntryButton();
        Thread.sleep(1_000);

        pageLogin.logIn(client.getEmail(), client.getPassword());
        Thread.sleep(2_000);

        String actualUrl = driver.getCurrentUrl();

        assertTrue( "После регистрации не произошел переход на главную страницу! Текущий URL: " + actualUrl,
                actualUrl.equals(Url.MAIN.getUrl()));

        assertEquals( "Оформить заказ", main.button());

    }
    @Test
    public void entryPersonalAccount() throws InterruptedException {
        driver = factory.getDriver();
        PageLogin pageLogin = new PageLogin(driver);
        PageMain main = new PageMain(driver);
        ObjectHeader objectHeader = new ObjectHeader(driver);

        objectHeader.clickButterPersonalAccount();

        Thread.sleep(1_000);

        pageLogin.logIn(client.getEmail(), client.getPassword());
        Thread.sleep(2_000);

        String actualUrl = driver.getCurrentUrl();

        assertTrue( "После регистрации не произошел переход на главную страницу! Текущий URL: " + actualUrl,
                actualUrl.equals(Url.MAIN.getUrl()));

        assertEquals( "Оформить заказ", main.button());

    }
    @Test
    public void entryThroughRegistration() throws InterruptedException {
        driver = factory.getDriver();
        PageLogin pageLogin = new PageLogin(driver);
        PageMain main = new PageMain(driver);
        ObjectHeader objectHeader = new ObjectHeader(driver);
        PageRegistration pageRegistration = new PageRegistration(driver);

        objectHeader.clickButterPersonalAccount();
        pageLogin.clickRegistration();
        pageRegistration.clickButtonAutoriz();
        Thread.sleep(1_000);


        pageLogin.logIn(client.getEmail(), client.getPassword());
        Thread.sleep(2_000);

        String actualUrl = driver.getCurrentUrl();

        assertTrue( "После регистрации не произошел переход на главную страницу! Текущий URL: " + actualUrl,
                actualUrl.equals(Url.MAIN.getUrl()));

        assertEquals( "Оформить заказ", main.button());

    }
    @Test
    public void entryRecoveryPassword() throws InterruptedException {
        driver = factory.getDriver();
        PageLogin pageLogin = new PageLogin(driver);
        PageMain main = new PageMain(driver);
        ObjectHeader objectHeader = new ObjectHeader(driver);
        PageRecoveryPassword pageRecoveryPassword = new PageRecoveryPassword(driver);

        objectHeader.clickButterPersonalAccount();
        pageLogin.clickRecoveryPassword();
        pageRecoveryPassword.clickButtorLogIn();
        Thread.sleep(1_000);


        pageLogin.logIn(client.getEmail(), client.getPassword());
        Thread.sleep(2_000);

        String actualUrl = driver.getCurrentUrl();

        assertTrue( "После регистрации не произошел переход на главную страницу! Текущий URL: " + actualUrl,
                actualUrl.equals(Url.MAIN.getUrl()));

        assertEquals( "Оформить заказ", main.button());

    }

    @After
    public void setDown(){
        ValidatableResponse delete =  clientRequest.delete(accessToken);
        clientCheck.deleteClient(delete);
        factory.webQuit();
    }
}
