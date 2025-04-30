import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckboxTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkCheckbox() {
       driver.get("https://the-internet.herokuapp.com/checkboxes");
       boolean isFirstCheckboxSelect = driver.findElement(By.xpath(
               "//*[@id=\"checkboxes\"]/input[1]")).isSelected();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(isFirstCheckboxSelect, false);
        driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]")).click();
        isFirstCheckboxSelect = driver.findElement(By.xpath(
                "//*[@id=\"checkboxes\"]/input[1]")).isSelected();
        softAssert.assertEquals(isFirstCheckboxSelect, true);
        boolean isSecondCheckboxSelect = driver.findElement(By.xpath(
                "//*[@id=\"checkboxes\"]/input[2]")).isSelected();
        softAssert.assertEquals(isSecondCheckboxSelect, true);
        driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]")).click();
        isSecondCheckboxSelect = driver.findElement(By.xpath(
                "//*[@id=\"checkboxes\"]/input[2]")).isSelected();
        softAssert.assertEquals(isSecondCheckboxSelect, false);
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
