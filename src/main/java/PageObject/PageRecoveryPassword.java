package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageRecoveryPassword {
    private WebDriver driver;

    private By buttorLogIn = By.xpath("//main/div/div/p/a");
    public  PageRecoveryPassword(WebDriver driver){
        this.driver = driver;
    }

    @Step("Кликнуть кнопку вход")
    public void clickButtorLogIn(){
        driver.findElement(buttorLogIn).click();
    }
}
