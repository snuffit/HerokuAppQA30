package homework_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.time.Duration;

public class FileUploadTest {

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
    public void checkFileUpload() {
        driver.get("https://the-internet.herokuapp.com/upload");
        File file = new File("src/test/java/resource/test.md");
        driver.findElement(By.xpath("//input[@id='file-upload']"))
                .sendKeys(file.getAbsolutePath());
        driver.findElement(By.xpath("//*[@class='button']")).click();
        Assert.assertEquals(driver.findElement(By.xpath(
                "//*[@id='uploaded-files']")).getText(), "test.md");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
