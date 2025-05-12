package homework_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class DropdownTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkCheckbox() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        SoftAssert softAssert = new SoftAssert();
        Select select = new Select(driver.findElement(By.id("dropdown")));
        select.selectByValue("1");
        softAssert.assertEquals(driver.findElement(By.xpath(
                "//*[@id=\"dropdown\"]/option[2]")).isSelected(), true);
        select.selectByValue("2");
        softAssert.assertEquals(driver.findElement(By.xpath(
                "//*[@id=\"dropdown\"]/option[3]")).isSelected(), true);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
