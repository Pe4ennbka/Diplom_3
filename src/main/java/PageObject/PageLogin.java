package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PageLogin {
    private WebDriver driver;

    private By butterRegistration = By.xpath("//main/div/div/p[1]/a");

    public By fieldsEmail = By.xpath("//fieldset[1]/div[@class = 'input__container']/div/input");
    private By fieldsPassword = By.xpath("//fieldset[2]/div[@class = 'input__container']/div/input");
    private By loginButter = By.xpath("//main/div/form/button");
    private By buttorRecoveryPassword = By.xpath("//main/div/div/p/a");

    private By textEntry = By.xpath("//main/div/h2");

    public  PageLogin(WebDriver driver){
        this.driver = driver;
    }
    @Step("Нажатия кнопки регистрация")
    public void clickRegistration(){
        driver.findElement(butterRegistration).click();
    }
    @Step("Введения почты")
    public void sendEmail(String email){
        driver.findElement(fieldsEmail).sendKeys(email);
    }
    @Step("Введения пароля")
    public void sendPassword(String password){
        driver.findElement(fieldsPassword).sendKeys(password);
    }
    @Step("Нажатия кнопки авторизация")
    public void clickLogIn(){
        driver.findElement(loginButter).click();
    }
    @Step("Нажатия кнопки востановить пароль")
    public void clickRecoveryPassword(){
        driver.findElement(buttorRecoveryPassword).click();
    }
    @Step("Авторизация")
    public void logIn(String email, String password){
        sendEmail(email);
        sendPassword(password);
        clickLogIn();
    }

    @Step("Вытащить текст кнопки Выход")
    public String textEntry() {
        return driver.findElement(textEntry).getText();
    }
}
