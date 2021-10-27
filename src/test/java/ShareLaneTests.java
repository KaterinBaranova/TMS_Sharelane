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

public class ShareLaneTests {
    private WebDriver driver;

    @BeforeClass (alwaysRun = true)
    public void setUp() {
        System.setProperty("webDriver.chrome.driver", "./src/main/resources/chromedriver");
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.sharelane.com");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void zipCodePositiveTest() throws InterruptedException {

        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();

        Thread.sleep(10000);
        WebElement signUpLink = driver.findElement(By.cssSelector("<a [href='/register.py']"));
        signUpLink.click();

        WebElement zipCodeInput = driver.findElement(By.name("Zip_code"));
        zipCodeInput.sendKeys("12345");

        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        // Assert.assertFalse(zipCodeInput.isDisplayed(),"Zip Code input should not be present");
        Assert.assertTrue(registerButton.isDisplayed(), "Register button should be present");

    }

    @Test
    public void emptyZipCodeTest() throws InterruptedException {

        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();

        Thread.sleep(10000);
        WebElement signUpLink = driver.findElement(By.cssSelector("<a [href='/register.py']"));
        signUpLink.click();

        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(continueButton.isDisplayed(), "Continue button should be present");
    }

    @Test
    public void zipCode4DigitsTest() throws InterruptedException {
        // Open browser
        // Navigate to shareLane (https://www.sharelane.com/cgi-bin/main.py)
        //Click on the Sign-up link
        //Enter 4 digits to Zip code filed
        //Click continue button
        //Verify that user is stays on the same page
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();

        Thread.sleep(10000);
        WebElement signUpLink = driver.findElement(By.cssSelector("<a [href='/register.py']"));
        signUpLink.click();

        WebElement zipCodeInput = driver.findElement(By.name("Zip_code"));
        zipCodeInput.sendKeys("1234");

        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(continueButton.isDisplayed(), "Continue button should be present");
    }

    @Test
    public void zipCode6DigitsTest() throws InterruptedException {
        // Open browser
        // Navigate to shareLane (https://www.sharelane.com/cgi-bin/main.py)
        //Click on the Sign-up link
        //Enter 6 digits to Zip code filed
        //Click continue button
        //Verify that user is stays on the same page
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();

        Thread.sleep(10000);
        WebElement signUpLink = driver.findElement(By.cssSelector("<a [href='/register.py']"));
        signUpLink.click();

        WebElement zipCodeInput = driver.findElement(By.name("Zip_code"));
        zipCodeInput.sendKeys("123456");

        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(continueButton.isDisplayed(), "Continue button should be present");
    }

    @Test
    public void emptySignUp() throws InterruptedException {
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();

        Thread.sleep(10000);
        WebElement signUpLink = driver.findElement(By.cssSelector("<a [href='/register.py']"));
        signUpLink.click();

        WebElement zipCodeInput = driver.findElement(By.name("Zip_code"));
        zipCodeInput.sendKeys("12345");

        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        registerButton.click();
        Assert.assertTrue(registerButton.isDisplayed(), "Register button should be present");
    }

    @Test
    public void validSignUp() throws InterruptedException {
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();

        Thread.sleep(10000);
        WebElement signUpLink = driver.findElement(By.cssSelector("<a [href='/register.py']"));
        signUpLink.click();

        WebElement zipCodeInput = driver.findElement(By.name("Zip_code"));
        zipCodeInput.sendKeys("12345");

        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        WebElement firstNameInput = driver.findElement(By.name("first_name"));
        firstNameInput.sendKeys("Bob");
        WebElement lastNameInput = driver.findElement(By.name("last_name"));
        lastNameInput.sendKeys("Smith");
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("Bob123@mailinator.com");
        WebElement password1Input = driver.findElement(By.name("password1"));
        password1Input.sendKeys("qwerty123");
        WebElement password2Input = driver.findElement(By.name("password2"));
        password2Input.sendKeys("qwerty123");
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        registerButton.click();
        WebElement hereLink = driver.findElement(By.cssSelector("<a [href='./main.py']"));
        Assert.assertTrue(hereLink.isDisplayed(), "Here link should be present");
    }
}