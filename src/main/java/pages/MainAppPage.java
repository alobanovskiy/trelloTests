package pages;

import core.PagesClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class MainAppPage extends PagesClass {
    
    private By profileButton = By.cssSelector(".js-open-header-member-menu");
    private By settingsMenuButton = By.cssSelector("a.js-profile");
    private By logoutMenuButton = By.cssSelector("li:nth-child(7) > button");
    private By createBoard = By.cssSelector(".board-tile.mod-add");
    private By boardTitle = By.cssSelector(".subtle-input");
    private By createBoardButton = By.cssSelector(".primary");
    private By moreButton = By.cssSelector(".board-menu-navigation-item-link.js-open-more");
    private By closeBoardButton = By.cssSelector(".js-close-board");
    private By confirmClose = By.cssSelector(".js-confirm.full.negate");
    private By fullClose = By.cssSelector(".quiet.js-delete");
    private By favorite = By.cssSelector(".board-tile-options");
    private By boardClipBoard = By.cssSelector("li.boards-page-board-section-list-item");
    private By boardList = By.cssSelector(".boards-page-board-section.mod-no-sidebar");
    private By boardListHeader = By.cssSelector(".boards-page-board-section-header-name");


    public void logOut(){
        (new WebDriverWait(driver,8))
                .until(ExpectedConditions.presenceOfElementLocated(profileButton));
        driver.findElement(profileButton).click();
        driver.findElement(logoutMenuButton).click();
    };

    public void createBoard(String boardName){
        (new WebDriverWait(driver,8))
                .until(ExpectedConditions.presenceOfElementLocated(profileButton));
        driver.findElement(createBoard).click();
        driver.findElement(boardTitle).sendKeys(boardName);
        driver.findElement(createBoardButton).click();
        String wordInUrl = boardName.replaceAll(" ", "-").toLowerCase();
        (new WebDriverWait(driver,8))
                .until(ExpectedConditions.urlContains(wordInUrl));
    }

    public void deleteBoard(String boardToDelete){
        String expContent = "Доска не найдена.";
        driver.findElement(moreButton).click();
        (new WebDriverWait(driver,4))
                .until(ExpectedConditions.presenceOfElementLocated(closeBoardButton));
        driver.findElement(closeBoardButton).click();
        driver.findElement(confirmClose).click();
        (new WebDriverWait(driver,4))
                .until(ExpectedConditions.presenceOfElementLocated(fullClose));
        driver.findElement(fullClose).click();
        driver.findElement(confirmClose).click();
        (new WebDriverWait(driver,4))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".big-message.quiet")));
        driver.get(boardToDelete);
        String content = driver.findElement(By.cssSelector(".big-message.quiet > h1")).getText();
        Assert.assertEquals(content, expContent);
    }

    private void hover(WebElement element){
        Actions builder = new Actions(driver);
        builder.moveToElement(element).perform();
    };

    public int getSizeOfFavorite(){
        String text = driver.findElements(boardList).get(0).findElement(boardListHeader).getText();
        if (text.equals("Отмеченные доски")){
          return driver.findElements(boardList).get(0).findElements(boardClipBoard).size();
        }
        return 0;
    };

    public void checkFavorite(int number){
        (new WebDriverWait(driver,4))
                .until(ExpectedConditions.presenceOfElementLocated(boardClipBoard));
        WebElement board = driver.findElements(boardClipBoard).get(number);
        hover(board);
        board.findElement(favorite).click();
    }
}
