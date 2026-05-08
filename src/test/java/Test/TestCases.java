package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;

public class TestCases {
    public WebDriver driver;
    public RegisterPage users;

    @BeforeMethod
    public void PreProcess(){
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
        users = new RegisterPage(driver);
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void Register (){
        users.CheckRegister();
    }

    @Test(priority = 2)
    public void Login() {
        users.CheckLogin();
    }

    @Test(priority = 3)
    public void Error(){
        users.ErrorMessage();
    }

    @Test(priority = 4)
    public void Logout(){
        users.Logout();
    }

    @Test(priority = 5)
    public void Empty(){
        users.EmptyInfo();
    }

    @Test(priority = 6)
    public void Dash(){
        users.DashBoard();
    }

    @Test(priority = 7)
    public void AddCart(){
        users.AddToCart();
    }

    @AfterMethod
    public void PostProcess() {
        driver.quit();
    }
}