package pages;

import core.PagesClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardPage extends PagesClass {
    By accessSelector = By.cssSelector(".board-header-btn.perms-btn.js-change-vis");
    By headerButton = By.cssSelector(".board-header-btn-text");
    By select = By.cssSelector(".js-select");

    public boolean isPrivate(String boardURL){
        driver.get(boardURL);
        (new WebDriverWait(driver,8))
                .until(ExpectedConditions.presenceOfElementLocated(accessSelector));
        String access = driver.findElement(accessSelector).getText();
        if (access.equals("Приватная"))
            return true;
        else
            return false;
    };

    public boolean isPublic(String boardURL){
        driver.get(boardURL);
        (new WebDriverWait(driver,8))
                .until(ExpectedConditions.presenceOfElementLocated(accessSelector));
        String access = driver.findElement(accessSelector).getText();
        if (access.equals("Публичная"))
            return true;
        else
            return false;
    };

    public void makePrivate(String boardURL){
        driver.get(boardURL);
        (new WebDriverWait(driver,8))
                .until(ExpectedConditions.presenceOfElementLocated(accessSelector));
        driver.findElements(headerButton).get(2).click();
        driver.findElements(select).get(0).click();
    }

    public void makePublic(String boardURL){
        driver.get(boardURL);
        (new WebDriverWait(driver,8))
                .until(ExpectedConditions.presenceOfElementLocated(accessSelector));
        driver.findElements(headerButton).get(2).click();
        driver.findElements(select).get(3).click();
        driver.findElement(By.cssSelector(".js-submit.wide.primary.full.make-public-confirmation-button")).click();
    }
}
