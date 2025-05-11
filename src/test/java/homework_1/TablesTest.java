package homework_1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class TablesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkTypos() {
        driver.get("https://the-internet.herokuapp.com/tables#delete");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.findElement(By.xpath(
                "//*[@id=\"table1\"]/tbody/tr[1]/td[1]")).getText(), "Smith");
        softAssert.assertEquals(driver.findElement(By.xpath(
                "//*[@id=\"table1\"]/tbody/tr[1]/td[5]")).getText(), "http://www.jsmith.com");
        softAssert.assertEquals(driver.findElement(By.xpath(
                "//*[@id=\"table1\"]/tbody/tr[4]/td[1]")).getText(), "Conway");
        softAssert.assertEquals(driver.findElement(By.xpath(
                "//*[@id=\"table2\"]/tbody/tr[2]/td[1]")).getText(), "Bach");
        softAssert.assertEquals(driver.findElement(By.xpath(
                "//*[@id=\"table2\"]/tbody/tr[3]/td[2]")).getText(), "Jason");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
