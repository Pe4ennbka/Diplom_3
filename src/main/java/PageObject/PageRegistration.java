package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PageRegistration {
    WebDriver driver;

    private By fieldsName = By.xpath("//fieldset[1]/div[@class = 'input__container']/div/input");
    private By fieldsEmail = By.xpath("//fieldset[2]/div[@class = 'input__container']/div/input");
    private By fieldsPassword = By.xpath("//fieldset[3]/div[@class = 'input__container']/div/input");
    private By errorIncorrectPassword = By.xpath("//fieldset[3]/div[@class = 'input__container']/p");

    public By buttonRegistration = By.xpath("//main/div/form/button");
    public By buttonAutoriz = By.xpath("//main/div/div/p/a");


    public PageRegistration(WebDriver driver){
        this.driver = driver;
    }

    @Step("В регистрации написать имя")
    private void sendFieldsName(String name){
        driver.findElement(fieldsName).sendKeys(name);
    }
    @Step("В регистрации написать почту")
    private void sendFieldsEmail(String email){
        driver.findElement(fieldsEmail).sendKeys(email);
    }
    @Step("В регистрации написать пароль")
    private void sendFieldsPassword(String password){
        driver.findElement(fieldsPassword).sendKeys(password);
    }
    @Step("Кликнуть кнопку регистрацию")
    private void clickRegistration(){
        driver.findElement(buttonRegistration).click();
    }
    @Step("Вытащить текст ошибки пароля")
    public String getTextIncorrectPassword(){
        return driver.findElement(errorIncorrectPassword).getText();
    }
    @Step("Нажать кнопку авторизации")
    public void clickButtonAutoriz(){
        driver.findElement(buttonAutoriz).click();
    }
    @Step("Зарегистрироваться")
    public void registration(String name, String email, String password) {
        sendFieldsName(name);
        sendFieldsEmail(email);
        sendFieldsPassword(password);
        clickRegistration();
    }
}
