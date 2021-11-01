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

public class DropdownTests {
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
    public void dropdownTest() {
        driver.get("http://the-internet.herokuapp.com/dropdown");
        WebElement option1 = driver.findElement(By.xpath("//select[@id='dropdown']/option[@value='1']"));
        option1.click();
        Assert.assertTrue(option1.isSelected(), "Option 1 is selected");
        WebElement option2 = driver.findElement(By.xpath("//select[@id='dropdown']/option[@value='2']"));
        option2.click();
        Assert.assertTrue(option2.isSelected(), "Option 2 is selected");
    }

}


