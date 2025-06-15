package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageMain {

    private WebDriver driver;

    public By buttonDesingOrder = By.xpath("//main/section[2]/div/button");

    private By constructorСhooseBun = By.xpath("//main/section[1]/div[1]/div[1]");
    private By constructorСhooseSouse = By.xpath("//main/section[1]/div[1]/div[2]");
    private By constructorСhooseIngredient = By.xpath("//main/section[1]/div[1]/div[3]");

    private By constructorTitleBun = By.xpath("//main/section[1]/div[2]/h2[1]");
    private By constructorTitleSouse = By.xpath("//main/section[1]/div[2]/h2[2]");
    private By constructorTitleIngredient = By.xpath("//main/section[1]/div[2]/h2[3]");

    public PageMain(WebDriver driver){
        this.driver = driver;
    }

    @Step("Найти элемент булочка")
    public WebElement titleBun() {
        return driver.findElement(constructorTitleBun);
    }
    @Step("Найти элемент соус")
    public WebElement titleSouse() {
        return driver.findElement(constructorTitleSouse);
    }
    @Step("Найти элемент ингредиент")
    public WebElement titleIngredient() {
        return driver.findElement(constructorTitleIngredient);
    }
    @Step("Вытащить текст кнопки регистрации")
    public String button() {
        return driver.findElement(By.xpath("//main/section[2]/div/button")).getText();
    }

    public void clickEntryButton(){
        driver.findElement(buttonDesingOrder).click();
    }
    public void clickСonstructorBun(){
        driver.findElement(constructorСhooseBun ).click();
    }
    public void clickСonstructorSouse(){
        driver.findElement(constructorСhooseSouse).click();
    }
    public void clickСonstructorIngredient(){
        driver.findElement(constructorСhooseIngredient).click();
    }

}
