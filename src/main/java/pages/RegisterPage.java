package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class RegisterPage {
    public WebDriver driver;
    public WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By registerLink = By.className("btn1");
    By firstName = By.id("firstName");
    By lastName = By.id("lastName");
    By userEmail = By.id("userEmail");
    By userMobile = By.id("userMobile");
    By occupation = By.className("custom-select");
    By gender = By.xpath("//input[@value='Male']");
    By password = By.id("userPassword");
    By confirmPassword = By.id("confirmPassword");
    By ageCheckbox = By.xpath("//input[@type='checkbox']");
    By registerButton = By.id("login");
    By loginEmail = By.id("userEmail");
    By loginPassword = By.id("userPassword");
    By loginButton = By.id("login");
    By errorMessage = By.cssSelector(".toast-error");

    public void CheckRegister() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        driver.findElement(firstName).sendKeys("Saro");
        driver.findElement(lastName).sendKeys("Kumar");
        driver.findElement(userEmail).sendKeys("saro@gmail.com");
        driver.findElement(userMobile).sendKeys("9876538639");
        WebElement dropdownElement = driver.findElement(occupation);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText("Engineer");
        driver.findElement(gender).click();
        driver.findElement(password).sendKeys("Saro@1234");
        driver.findElement(confirmPassword).sendKeys("Saro@1234");
        driver.findElement(ageCheckbox).click();
        driver.findElement(registerButton).click();
    }

    public void CheckLogin() {
        driver.findElement(loginEmail).sendKeys("saro5@gmail.com");
        driver.findElement(loginPassword).sendKeys("Saro@1234");
        driver.findElement(loginButton).click();
    }
    public void ErrorMessage() {
        driver.findElement(loginEmail).sendKeys("saro5@gmail.com");
        driver.findElement(loginPassword).sendKeys("WrongPassword");
        driver.findElement(loginButton).click();
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        System.out.println("Error Message: " + toast.getText());
    }

    public void Logout(){
        driver.findElement(By.className("btn")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerLink));
    }

    By Email = By.id("userEmail");
    By Password = By.id("userPassword");
    By LoginButton = By.id("login");

    public void EmptyInfo() {

        driver.findElement(LoginButton).click();
        String userValidation = driver.findElement(Email).getAttribute("validationMessage");
        String passValidation = driver.findElement(Password).getAttribute("validationMessage");
        System.out.println(userValidation);
        System.out.println(passValidation);
    }

    public void DashBoard(){
        driver.findElement(loginEmail).sendKeys("saro5@gmail.com");
        driver.findElement(loginPassword).sendKeys("Saro@1234");
        driver.findElement(loginButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card-img-top")));
    }

    public void AddToCart() {
        driver.findElement(loginEmail).sendKeys("saro5@gmail.com");
        driver.findElement(loginPassword).sendKeys("Saro@1234");
        driver.findElement(loginButton).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("w-10"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className("btn"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("btn-primary")));
    }
}
