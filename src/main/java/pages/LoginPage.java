package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import core.PagesClass;

public class LoginPage extends PagesClass{

    private By loginPageButton = By.cssSelector(".btn.btn-sm.btn-link.text-white");
    private By emailField = By.id("user");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login");


    //@Parameters({"email","password"})
    public void login(String email, String password){
        driver.get("https://trello.com/");
        driver.findElement(loginPageButton).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        (new WebDriverWait(driver,6))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".boards-page-board-section-list-item")));
    }

}
