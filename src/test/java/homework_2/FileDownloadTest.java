package homework_2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FileDownloadTest {

    private WebDriver driver;
    private String downloadPath;

    @BeforeTest
    public void setUp() {
        downloadPath = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.directory_upgrade", true);
        prefs.put("safebrowsing.enabled", true);
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testFileDownload() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/download");
        String fileName = driver.findElement(By.xpath("//div[@class='example']/a")).getText();
        driver.findElement(By.xpath("//div[@class='example']/a")).click();
        Thread.sleep(3000);
        Path filePath = Paths.get(downloadPath + fileName);
        assertTrue(Files.exists(filePath), "Файл не был скачан");
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertFalse(Files.exists(filePath), "Файл не был удален");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
