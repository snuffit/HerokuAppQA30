import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class NotificationTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkNotification() {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/p/a")).click();
        softAssert.assertEquals(driver.findElement(By.xpath(
                "//*[@id=\"flash\"]")).isDisplayed(), true);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
