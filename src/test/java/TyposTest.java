import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TyposTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkTypos() {
        driver.get("https://the-internet.herokuapp.com/typos");
        int countOfCorrectText = 0;
        for (int i = 0; i < 10; i++) {
            if (driver.findElement(By.xpath(
                    "//*[@id=\"content\"]/div/p[2]")).getText().contains("won't")) {
                countOfCorrectText++;
                ;
            }
            driver.navigate().refresh();
        }
        Assert.assertEquals(countOfCorrectText, 10,
                countOfCorrectText + " из 10 был обнаружен корректный текст.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
