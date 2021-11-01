package herokuapp;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class CheckboxesTests {
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
    public void checkboxTest() {

        driver.get("http://the-internet.herokuapp.com/checkboxes");
        WebElement firstCheckbox = driver.findElement(By.cssSelector("[type=checkbox 1]"));
        Assert.assertNull(firstCheckbox.getAttribute("unchecked"));
        firstCheckbox.click();
        Assert.assertNotNull(firstCheckbox.getAttribute("checked"));
        WebElement secondCheckbox = driver.findElement(By.cssSelector("[type=checkbox 2]"));
        Assert.assertNotNull(secondCheckbox.getAttribute("checked"));
        secondCheckbox.click();
        Assert.assertNull(secondCheckbox.getAttribute("unchecked"));
    }
}
