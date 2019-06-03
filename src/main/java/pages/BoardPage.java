package pages;

import core.PagesClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BoardPage extends PagesClass {
    By accessSelector = By.cssSelector(".board-header-btn.perms-btn.js-change-vis");

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
}
