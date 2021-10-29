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

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void zipCodePositiveTest() {

        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement signUpLink = driver.findElement(By.cssSelector("<a [href='./register.py']"));
        signUpLink.click();
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        Assert.assertFalse(zipCodeInput.isDisplayed(), "Zip code input should not be displayed");
        Assert.assertTrue(registerButton.isDisplayed(), "Register button should be displayed");
    }

    @Test
    public void emptyZipCodeTest() {

        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement signUpLink = driver.findElement(By.cssSelector("<a [href='/register.py']"));
        signUpLink.click();
        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(continueButton.isDisplayed(), "Continue button should be present");
    }

    @Test
    public void zipCode4DigitsTest() {

        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement signUpLink = driver.findElement(By.cssSelector("<a [href='/register.py']"));
        signUpLink.click();
        WebElement zipCodeInput = driver.findElement(By.name("Zip_code"));
        zipCodeInput.sendKeys("1234");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(continueButton.isDisplayed(), "Continue button should be present");
    }

    @Test
    public void zipCode6DigitsTest() {

        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement signUpLink = driver.findElement(By.cssSelector("<a [href='/register.py']"));
        signUpLink.click();
        WebElement zipCodeInput = driver.findElement(By.name("Zip_code"));
        zipCodeInput.sendKeys("123456");
        WebElement continueButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        continueButton.click();
        Assert.assertTrue(continueButton.isDisplayed(), "Continue button should be present");
    }

    @Test
    public void emptySignUp() {
        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
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
    public void validSignUp() {
        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
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

    @Test
    public void AddToCartForUnauthenticated() {

        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement searchInput = driver.findElement(By.name("keyword"));
        searchInput.sendKeys("White Fang");
        WebElement searchButton = driver.findElement(By.cssSelector("input[value='Search']"));
        searchButton.click();
        WebElement addToCartButton = driver.findElement(By.cssSelector("<a[href='./add_to_cart.py?book_id=2']"));
        addToCartButton.click();
        WebElement errorMessage = driver.findElement(By.id("error_message"));
        Assert.assertEquals(errorMessage.getText(), "Oops, error. You must log in.");
    }

    @Test
    public void AddToCartForAuthenticated() {
        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("rajiv_lin@8988.5.sharelane.com");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("1111");
        WebElement loginButton = driver.findElement(By.cssSelector("input[value='Login']"));
        loginButton.click();
        WebElement searchInput = driver.findElement(By.name("keyword"));
        searchInput.sendKeys("White Fang");
        WebElement searchButton = driver.findElement(By.cssSelector("input[value='Search']"));
        searchButton.click();
        WebElement addToCartButton = driver.findElement(By.cssSelector("<a[href='./add_to_cart.py?book_id=2']"));
        addToCartButton.click();
        WebElement confirmationMessage = driver.findElement(By.id("confirmation_message"));
        Assert.assertEquals(confirmationMessage.getText(), "Book was added to the Shopping Cart.");
    }

    @Test
    public void ProceedToCheckoutPage() {

        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("rajiv_lin@8988.5.sharelane.com");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("1111");
        WebElement loginButton = driver.findElement(By.cssSelector("input[value='Login']"));
        loginButton.click();
        WebElement searchInput = driver.findElement(By.name("keyword"));
        searchInput.sendKeys("White Fang");
        WebElement searchButton = driver.findElement(By.cssSelector("input[value='Search']"));
        searchButton.click();
        WebElement addToCartButton = driver.findElement(By.cssSelector("<a[href='./add_to_cart.py?book_id=2']"));
        addToCartButton.click();
        WebElement shoppingCartLink = driver.findElement(By.cssSelector("<a[href='./shopping_cart.py']"));
        shoppingCartLink.click();
        WebElement proceedToCheckoutButton = driver.findElement(By.cssSelector("input[value='Proceed to Checkout']"));
        proceedToCheckoutButton.click();
        WebElement makePaymentButton = driver.findElement(By.cssSelector("input[value='Make Payment']"));
        Assert.assertTrue(makePaymentButton.isDisplayed(), "Make Payment button should be present");
    }

    @Test
    public void cartUpdatedConfirmationMessage() {

        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("rajiv_lin@8988.5.sharelane.com");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("1111");
        WebElement loginButton = driver.findElement(By.cssSelector("input[value='Login']"));
        loginButton.click();
        WebElement searchInput = driver.findElement(By.name("keyword"));
        searchInput.sendKeys("White Fang");
        WebElement searchButton = driver.findElement(By.cssSelector("input[value='Search']"));
        searchButton.click();
        WebElement addToCartButton = driver.findElement(By.cssSelector("<a[href='./add_to_cart.py?book_id=2']"));
        addToCartButton.click();
        WebElement shoppingCartLink = driver.findElement(By.cssSelector("<a[href='./shopping_cart.py']"));
        shoppingCartLink.click();
        WebElement quantityInput = driver.findElement(By.name("q"));
        quantityInput.sendKeys("2");
        WebElement updateButton = driver.findElement(By.cssSelector("input[value='Update']"));
        updateButton.click();
        WebElement cartUpdatedConfirmationMessage = driver.findElement(By.id("confirmation_message"));
        Assert.assertEquals(cartUpdatedConfirmationMessage.getText(), "Cart Updated");
    }

    @Test
    public void discountCheck() {

        driver.get("https://www.sharelane.com");
        WebElement enterButton = driver.findElement(By.cssSelector("<a[href='../cgi-bin/main.py']"));
        enterButton.click();
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("rajiv_lin@8988.5.sharelane.com");
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("1111");
        WebElement loginButton = driver.findElement(By.cssSelector("input[value='Login']"));
        loginButton.click();
        WebElement searchInput = driver.findElement(By.name("keyword"));
        searchInput.sendKeys("White Fang");
        WebElement searchButton = driver.findElement(By.cssSelector("input[value='Search']"));
        searchButton.click();
        WebElement addToCartButton = driver.findElement(By.cssSelector("<a[href='./add_to_cart.py?book_id=2']"));
        addToCartButton.click();
        WebElement shoppingCartLink = driver.findElement(By.cssSelector("<a[href='./shopping_cart.py']"));
        shoppingCartLink.click();
        WebElement quantityInput = driver.findElement(By.name("q"));
        quantityInput.sendKeys("63");
        WebElement updateButton = driver.findElement(By.cssSelector("input[value='Update']"));
        updateButton.click();
        WebElement discountAmount = driver.findElement(By.xpath("//table[@align=']/tbody/tr/td[5]//a[1]"));
        Assert.assertEquals(discountAmount.getText(), "3");
    }
}