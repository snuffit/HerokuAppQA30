package homework_1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkNumInputs() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.tagName("input")).sendKeys("10");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "11");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "9");
        softAssert.assertAll();
    }

    @Test
    public void checkNotNumInputs() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.tagName("input")).sendKeys("qwerty");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "1");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "0");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
