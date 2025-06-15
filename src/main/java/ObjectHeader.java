import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ObjectHeader {
private WebDriver driver;

private By personalAccount = By.xpath("//header/nav/a");
private By buttonConstructor = By.xpath("//header/nav/ul/li[1]/a");


    public ObjectHeader(WebDriver driver){
    this.driver = driver;
}

    public void clickButtonConstructor(){
        driver.findElement(buttonConstructor).click();
    }


public void clickButterPersonalAccount(){
    driver.findElement(personalAccount).click();
}
}
