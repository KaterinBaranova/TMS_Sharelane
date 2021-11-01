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


import java.util.List;
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
        List<WebElement> checkBoxes = driver.findElements(By.tagName("input"));
        Assert.assertFalse(checkBoxes.get(0).isSelected(), "the 1st checkbox is unchecked");
        checkBoxes.get(0).click();
        Assert.assertTrue(checkBoxes.get(0).isSelected(), "the 1st checkbox is checked");
        Assert.assertTrue(checkBoxes.get(1).isSelected(), "the 2nd checkbox is checked");
        checkBoxes.get(1).click();
        Assert.assertFalse(checkBoxes.get(1).isSelected(), "the 2nd checkbox is unchecked");
    }
}
