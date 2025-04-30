import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class HoversTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkHovers() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        SoftAssert softAssert = new SoftAssert();
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath(
                "//*[@id=\"content\"]/div/div[1]/img"))).perform();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a")).click();
        softAssert.assertEquals(driver.findElement(By.tagName(
                "h1")).getText(), "Not Found");
        driver.navigate().back();
        actions.moveToElement(driver.findElement(By.xpath(
                "//*[@id=\"content\"]/div/div[2]/img"))).perform();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/a")).click();
        softAssert.assertEquals(driver.findElement(By.tagName(
                "h1")).getText(), "Not Found");
        driver.navigate().back();
        actions.moveToElement(driver.findElement(By.xpath(
                "//*[@id=\"content\"]/div/div[3]/img"))).perform();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[3]/div/a")).click();
        softAssert.assertEquals(driver.findElement(By.tagName(
                "h1")).getText(), "Not Found");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
