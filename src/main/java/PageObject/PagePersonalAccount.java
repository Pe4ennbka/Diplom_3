package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PagePersonalAccount {
    private WebDriver driver;

    private By buttonProfile = By.xpath("//main/div/nav/ul/li[1]/a");
    private By buttonExit = By.xpath("//main/div/nav/ul/li[3]/button");


    public  PagePersonalAccount(WebDriver driver){
        this.driver = driver;
    }

    public String buttonProfile() {
        return driver.findElement(buttonProfile).getText();
    }
    public void clickButtonExit() {
        driver.findElement(buttonExit).click();
    }
}
