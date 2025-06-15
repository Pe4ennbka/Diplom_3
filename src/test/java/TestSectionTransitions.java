import PageObject.PageLogin;
import PageObject.PageMain;
import PageObject.PagePersonalAccount;
import Personal.Client;
import Personal.ClientCheck;
import Personal.RequestClient;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class TestSectionTransitions {
    RequestClient clientRequest = new RequestClient();
    ClientCheck clientCheck = new ClientCheck();
    Client client;
    DriverFactor factory = new DriverFactor();
    private WebDriver driver;
    private String accessToken;
    private String actualUrl;

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
    public void transitionPersonalAccountAuto() throws InterruptedException {
        driver = factory.getDriver();
        PageLogin pageLogin = new PageLogin(driver);
        PageMain main = new PageMain(driver);
        ObjectHeader objectHeader = new ObjectHeader(driver);
        PagePersonalAccount pagePersonalAccount = new PagePersonalAccount(driver);

        main.clickEntryButton();
        Thread.sleep(1_000);

        pageLogin.logIn(client.getEmail(), client.getPassword());
        Thread.sleep(1_000);

        objectHeader.clickButterPersonalAccount();

        actualUrl = driver.getCurrentUrl();
        assertTrue( "После регистрации не произошел переход на главную страницу! Текущий URL: " + actualUrl,
                actualUrl.equals(Url.PersonalAccountAuto.getUrl()));

        assertEquals( "Профиль", pagePersonalAccount.buttonProfile());
    }
    @Test
    public void transitionPersonalAccountNoAuto() throws InterruptedException{
        driver = factory.getDriver();
        PageLogin pageLogin = new PageLogin(driver);
        ObjectHeader objectHeader = new ObjectHeader(driver);

        objectHeader.clickButterPersonalAccount();

        actualUrl = driver.getCurrentUrl();
        assertTrue( "После регистрации не произошел переход на главную страницу! Текущий URL: " + actualUrl,
                actualUrl.equals(Url.LogIn.getUrl()));

        assertEquals( "Вход", pageLogin.textEntry());
    }
    @Test
    public void personalAccountAutoTransitionConstructor() throws InterruptedException{
        driver = factory.getDriver();
        PageLogin pageLogin = new PageLogin(driver);
        PageMain main = new PageMain(driver);
        ObjectHeader objectHeader = new ObjectHeader(driver);

        main.clickEntryButton();
        Thread.sleep(1_000);

        pageLogin.logIn(client.getEmail(), client.getPassword());
        Thread.sleep(1_000);

        objectHeader.clickButterPersonalAccount();
        Thread.sleep(1_000);
        objectHeader.clickButtonConstructor();
        Thread.sleep(1_000);

        actualUrl = driver.getCurrentUrl();
        assertTrue( "После регистрации не произошел переход на главную страницу! Текущий URL: " + actualUrl,
                actualUrl.equals(Url.MAIN.getUrl()));

        assertEquals( "Оформить заказ", main.button());
    }
    @Test
    public void personalAccountNoAutoTransitionConstructor() throws InterruptedException{
        driver = factory.getDriver();
        PageMain main = new PageMain(driver);
        ObjectHeader objectHeader = new ObjectHeader(driver);

        objectHeader.clickButterPersonalAccount();
        Thread.sleep(1_000);
        objectHeader.clickButtonConstructor();
        Thread.sleep(1_000);

        actualUrl = driver.getCurrentUrl();
        assertTrue( "После регистрации не произошел переход на главную страницу! Текущий URL: " + actualUrl,
                actualUrl.equals(Url.MAIN.getUrl()));

        assertEquals( "Войти в аккаунт", main.button());
    }
    @Test
    public void usefulExit() throws InterruptedException{
        driver = factory.getDriver();
        PageLogin pageLogin = new PageLogin(driver);
        PageMain main = new PageMain(driver);
        ObjectHeader objectHeader = new ObjectHeader(driver);
        PagePersonalAccount pagePersonalAccount = new PagePersonalAccount(driver);

        main.clickEntryButton();
        Thread.sleep(1_000);

        pageLogin.logIn(client.getEmail(), client.getPassword());
        Thread.sleep(1_000);

        objectHeader.clickButterPersonalAccount();
        Thread.sleep(1_000);
        pagePersonalAccount.clickButtonExit();
        Thread.sleep(1_000);
        actualUrl = driver.getCurrentUrl();
        assertTrue( "После регистрации не произошел переход на главную страницу! Текущий URL: " + actualUrl,
                actualUrl.equals(Url.LogIn.getUrl()));

        assertEquals( "Вход", pageLogin.textEntry());
    }
    @Test
    public void сonstructorTransitions() throws InterruptedException{
        driver = factory.getDriver();
        PageMain main = new PageMain(driver);

        assertTrue("Заголовок булки не отображается", main.titleBun().isDisplayed());

        int initialYBun = main.titleBun().getLocation().getY();

        main.clickСonstructorSouse();
        Thread.sleep(1_000);
        int initialYBunNew = main.titleBun().getLocation().getY();
        int initialYSouse = main.titleSouse().getLocation().getY();
        assertTrue("Заголовок соуса не отображается", main.titleSouse().isDisplayed());
        assertTrue("Булка не поднялась после скролла", initialYBunNew < initialYBun);

        main.clickСonstructorIngredient();
        Thread.sleep(1_000);
        int initialYSouseNew = main.titleSouse().getLocation().getY();
        int initialYIngredient = main.titleIngredient().getLocation().getY();

        assertTrue("Заголовок ингредиентов не отображается", main.titleIngredient().isDisplayed());
        assertTrue("Соус не поднялся после скролла", initialYSouseNew < initialYSouse);

        main.clickСonstructorBun();
        int initialYIngredientNew = main.titleIngredient().getLocation().getY();
        Thread.sleep(1_000);
        assertTrue("Заголовок булки не отображается", main.titleBun().isDisplayed());
        assertTrue("Ингредиенты не поднялся после скролла", initialYIngredient < initialYIngredientNew);


    }
    @After
    public void setDown(){
        ValidatableResponse delete =  clientRequest.delete(accessToken);
        clientCheck.deleteClient(delete);
        factory.webQuit();
    }
}
