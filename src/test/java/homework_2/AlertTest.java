package homework_2;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class AlertTest {

    WebDriver driver;
    SoftAssert softAssert;
    Actions actions;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        softAssert = new SoftAssert();
        actions = new Actions(driver);
    }

    @Test
    public void checkAlert() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        actions.contextClick(driver.findElement(By.xpath("//*[@id='hot-spot']"))).perform();
        Alert alert = driver.switchTo().alert();
        softAssert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
