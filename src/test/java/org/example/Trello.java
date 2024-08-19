package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.example.CreateboardElements;

import java.time.Duration;

import static org.openqa.selenium.By.cssSelector;

public class Trello {
    static WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public static void loginToTrello() throws InterruptedException {

        driver.get("https://trello.com/");
        driver.findElement(cssSelector("div.Buttonsstyles__ButtonGroup-sc-1jwidxo-3.jnMZCI > a:nth-child(1)")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        element.click();

        driver.findElement(By.id("username")).sendKeys("94sadeceuygulamalar19@gmail.com");
        driver.findElement(By.id("login-submit")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        driver.findElement(By.id("password")).sendKeys("Taskicin24");
        driver.findElement(By.id("login-submit")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("mfa-promote-dismiss")));
        //      driver.findElement(By.id("mfa-promote-dismiss")).click();
    }

    @Test(dependsOnMethods = "loginToTrello")
    public static void createBoard() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //CreateboardElements.HeaderCreate.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cssSelector("button[data-testid='header-create-menu-button']")));
        driver.findElement(cssSelector("button[data-testid='header-create-menu-button']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cssSelector("button[data-testid='header-create-board-button']")));
        driver.findElement(cssSelector("button[data-testid='header-create-board-button']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(cssSelector("input[data-testid='create-board-title-input']")));
        WebElement boardTitle = driver.findElement(cssSelector("input[data-testid='create-board-title-input']"));
        boardTitle.sendKeys("VaultN");
        driver.findElement(cssSelector("button[data-testid='create-board-submit-button']")).click();
    }

    @Test(dependsOnMethods = "createBoard")
    public void createToDoList() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(cssSelector("textarea[data-testid='list-card-composer-textarea']")));
        //add to do
        WebElement sing = driver.findElement(cssSelector("textarea[data-testid='list-card-composer-textarea']"));
        sing.sendKeys("Sign up for Trello");
        //add card
        WebElement addCard = driver.findElement(cssSelector("button[data-testid='list-card-composer-add-card-button']"));
        //data-testid="list-card-composer-add-card-button"
        addCard.click();
        driver.findElement(cssSelector("button[data-testid='list-composer-button']")).click();
        WebElement addCardIcon = driver.findElement(cssSelector("button[data-testid='list-add-card-button']"));
        //data-testid="list-card-composer-add-card-button"
        addCardIcon.click();
        WebElement writeCard = driver.findElement(cssSelector("textarea[data-testid='list-card-composer-textarea']"));
        writeCard.sendKeys("Get key and token");
        driver.findElement(By.cssSelector("button[data-testid='list-card-composer-add-card-button']")).click();
        driver.findElement(cssSelector("textarea[data-testid='list-card-composer-textarea']")).sendKeys("Build a collection");
        driver.findElement(By.cssSelector("button[data-testid='list-card-composer-add-card-button']")).click();
        driver.findElement(cssSelector("textarea[data-testid='list-card-composer-textarea']")).sendKeys("Working on Task");
        driver.findElement(By.cssSelector("button[data-testid='list-card-composer-add-card-button']")).click();

    }

    @Test(dependsOnMethods = "createToDoList")
    public void Backlog() {
        driver.findElement(By.cssSelector("div.WC6fBZ3Z4IYlvP>button[data-testid='list-composer-button']")).click();
        driver.findElement(By.cssSelector("form.vVqwaYKVgTygrk>[data-testid='list-name-textarea']")).sendKeys("Backlog");
        driver.findElement(By.cssSelector("button[data-testid='list-composer-add-list-button']")).click();

    }

    @Test(dependsOnMethods = "Backlog")
    public void BacklogCardAdding() {
        //add card UI Automation
        driver.findElement(By.cssSelector("#board>li:nth-child(4)>div>div.IapUGb_Cq2VzSr>button.O9vivwyDxMqo3q.bxgKMAm3lq5BpA.iUcMblFAuq9LKn.PnEv2xIWy3eSui.SEj5vUdI3VvxDc")).click();
        WebElement addAutomation = driver.findElement(By.cssSelector("#board>li:nth-child(4)>div>ol>li>form>textarea"));
        addAutomation.sendKeys("UI Automation");
        driver.findElement(By.cssSelector("li:nth-child(4)>div>ol>li>form>div>button.bxgKMAm3lq5BpA.SdamsUKjxSBwGb.SEj5vUdI3VvxDc")).click();
        //ADD Writing Test Scenarios
        driver.findElement(By.cssSelector("li:nth-child(4)>div>ol>li.H136XFPzM9syCb>form>textarea")).sendKeys("Writing Test Scenarios");
        driver.findElement(By.cssSelector("li:nth-child(4)>div>ol>li.H136XFPzM9syCb>form>div>button.bxgKMAm3lq5BpA.SdamsUKjxSBwGb.SEj5vUdI3VvxDc")).click();
    }

    @Test(dependsOnMethods = "BacklogCardAdding")
    public void Testing() {
        driver.findElement(By.cssSelector("#board>div>button")).click();
        WebElement addTesting = driver.findElement(By.cssSelector("textarea.oe8RymzptORQ7h"));
        addTesting.sendKeys("Testing");
        driver.findElement(By.cssSelector("button[data-testid='list-composer-add-list-button']")).click();

    }
/*
    @Test(dependsOnMethods = "createBoard")
    public void MoveCards() {

        moveCard("Sign up for Trello", "Done");
        moveCard("Get key and token", "Testing");
        moveCard("Build a collection", "Doing");
        moveCard("Working on Task", "Doing");
    }

    private void moveCard(String cardName, String targetListName) {
        WebElement card = driver.findElement(By.xpath("//a[contains(text(),'" + cardName + "')]"));
        WebElement targetList = driver.findElement(By.xpath("//textarea[contains(text(),'" + targetListName + "')]"));
        card.click();
        driver.findElement(cssSelector("button[data-test-id='move-card-button']")).click();
        WebElement moveToListDropdown = driver.findElement(cssSelector("select[aria-label='Move to list']"));
        moveToListDropdown.sendKeys(targetList.getText());
        driver.findElement(cssSelector("input[value='Move']")).click();
    }
*/
    @Test(dependsOnMethods = "Testing")
    public void closeAndDeleteBoard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(cssSelector("button.frrHNIWnTojsww.GDunJzzgFqQY_3.bxgKMAm3lq5BpA.HAVwIqCeMHpVKh.SEj5vUdI3VvxDc")).click();
        driver.findElement(cssSelector("a.board-menu-navigation-item-link.board-menu-navigation-item-link-v2.js-close-board")).click();
        driver.findElement(cssSelector("input[type='submit']")).click();
     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[data-testid='close-board-delete-board-button']")));
        driver.findElement(cssSelector("button[data-testid='close-board-delete-board-button']")).click();
        //data-testid="close-board-delete-board-button"
        driver.findElement(cssSelector("button[data-testid='close-board-delete-board-confirm-button']")).click();
    }

   /* @AfterClass
    public void tearDown() {
        driver.quit();
   }
*/
}
