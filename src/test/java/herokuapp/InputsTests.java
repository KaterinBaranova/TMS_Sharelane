package herokuapp;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class InputsTests {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void inputsTest() {
        driver.get("http://the-internet.herokuapp.com/inputs");
        driver.findElement(By.xpath("//input")).sendKeys(Keys.ARROW_UP);
        String upKeyResult = driver.findElement(By.xpath("//input")).getAttribute("value");
        assertEquals(upKeyResult, "1");
        driver.findElement(By.xpath("//input")).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN);
        String downKeyResult = driver.findElement(By.xpath("//input")).getAttribute("value");
        assertEquals(downKeyResult, "-2");
        // проверить буквенные симолы
        driver.navigate().refresh();
        driver.findElement(By.xpath("//input")).sendKeys("qwerty");
        String alphaResult = driver.findElement(By.xpath("//input")).getAttribute("value");
        assertEquals(alphaResult, ""); //есть баг 'e' вводится
        // проверить ручной ввод цифровых симолов
        driver.navigate().refresh();
        driver.findElement(By.xpath("//input")).sendKeys("123");
        String numericResult = driver.findElement(By.xpath("//input")).getAttribute("value");
        assertEquals(numericResult, "123");
    }
}

