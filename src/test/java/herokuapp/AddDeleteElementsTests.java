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

public class AddDeleteElementsTests {
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
    public void addElementTest() {

        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addButton.click();
        WebElement deleteButton = driver.findElement(By.xpath("//button[text()='deleteElement']"));
        Assert.assertTrue(deleteButton.isDisplayed(), "Delete button should be present on the page");
    }

    @Test
    public void deleteElementTest(){
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addButton.click();
        WebElement deleteButton = driver.findElement(By.xpath("//button[text()='deleteElement']"));
        deleteButton.click();
        Assert.assertFalse(deleteButton.isDisplayed(), "Delete button should not be present on the page");
    }
    @Test
    public void countNumberOfElements(){
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addButton.click();
        addButton.click();
        List<WebElement> deleteButtons = (List<WebElement>) driver.findElement(By.xpath("//button[text()='deleteElement']"));
        deleteButtons.get(0).click();
        deleteButtons = (List<WebElement>) driver.findElement(By.xpath("//button[text()='deleteElement']"));
        Assert.assertEquals(deleteButtons.size(), 1);
    }
}
