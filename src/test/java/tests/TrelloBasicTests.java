package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BoardPage;
import pages.LoginPage;

import org.testng.annotations.*;
import pages.MainAppPage;

import static org.testng.Assert.*;

public class TrelloBasicTests extends LoginPage {

    String boardToDelete;
    LoginPage loginPage = new LoginPage();
    MainAppPage mainAppPage = new MainAppPage();
    BoardPage boardPage = new BoardPage();

    @BeforeMethod
    @Parameters({"email","password"})
    public void setUp(String email, String password){
        driver.get("https://trello.com/");
        loginPage.login(email, password);
    };

    @Test
    void loginTest(){
       driver.findElement(By.cssSelector(".boards-page-board-section-list-item")).click();
        assertTrue(driver.findElement(By.cssSelector(".all-boards")).isDisplayed());
    }

    @Test
    void logoutTest(){
        String expContent = "Спасибо, что используете Trello.\n" +
                "Вы вышли. Ну, что теперь?";
        MainAppPage mainAppPage = new MainAppPage();
        mainAppPage.logOut();
        String content = driver.findElement(By.cssSelector(".layout-centered.u-center-text")).getText();
        assertEquals(content, expContent);
    }

    @Test
    void favouriteBoard(){
        Integer sizeOfBoard = mainAppPage.getSizeOfFavorite();
        mainAppPage.checkFavorite(7);
        Integer sizeOfBoardAfter = mainAppPage.getSizeOfFavorite();
        assertFalse(sizeOfBoardAfter == 0);
        assertTrue(sizeOfBoard<sizeOfBoardAfter);
    }

    @Test
    void createBoard(){
        mainAppPage.createBoard("Some Long Name for BoArd");
        boardPage.isPrivate(driver.getCurrentUrl());
        boardToDelete = driver.getCurrentUrl();

    }

    @Test
    void checkAccess() {
        Boolean privateBoard = boardPage.isPrivate("https://trello.com/b/Fiz4oeMa/test-board");
        assertTrue(privateBoard);
    };

    @Test(dependsOnMethods = { "createBoard" })
    void deleteBoard()throws InterruptedException{
        (new WebDriverWait(driver,4))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".boards-page-board-section-list-item")));
        driver.get(boardToDelete);
        mainAppPage.deleteBoard(boardToDelete);
    }

}
