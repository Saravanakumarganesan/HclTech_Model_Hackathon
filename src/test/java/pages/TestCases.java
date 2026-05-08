package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.RegisterPage;

public class TestCases {

    WebDriver driver;

    RegisterPage page;

    String email = "testuser@gmail.com";

    String password = "Test@123";

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();

        page = new RegisterPage(driver);

        page.openApplication();
    }

    @Test(priority = 1)
    public void verifySuccessfulLogin() {

        page.login(email, password);

        Assert.assertTrue(page.getProductCount() > 0);
    }

    @Test(priority = 2)
    public void verifyLoginFailure() {

        page.login(email, "WrongPassword");

        String error = page.getLoginErrorMessage();

        Assert.assertTrue(error.contains("Incorrect"));
    }

    @Test(priority = 3)
    public void verifyEmptyLoginValidation() {

        Assert.assertTrue(page.validateEmptyLogin());
    }

    @Test(priority = 4)
    public void verifyProductDashboardLoads() {

        page.login(email, password);

        Assert.assertTrue(page.getProductCount() > 0);
    }

    @Test(priority = 5)
    public void verifyProductCardDetails() {

        page.login(email, password);

        Assert.assertTrue(page.verifyProductNameAndPrice());
    }

    @Test(priority = 6)
    public void verifyAddProductToCart() {

        page.login(email, password);

        page.addProductToCart("ZARA COAT 3");

        Assert.assertEquals(page.getCartBadgeCount(), "1");
    }

    @Test(priority = 7)
    public void verifyMultipleProductsAddToCart() {

        page.login(email, password);

        page.addProductToCart("ZARA COAT 3");

        page.addProductToCart("ADIDAS ORIGINAL");

        Assert.assertEquals(page.getCartBadgeCount(), "2");
    }

    @Test(priority = 8)
    public void verifyProductsInCart() {

        page.login(email, password);

        page.addProductToCart("ZARA COAT 3");

        page.goToCart();

        Assert.assertTrue(page.verifyProductInCart("ZARA COAT 3"));
    }

    @Test(priority = 9)
    public void verifyDeleteProductFromCart() {

        page.login(email, password);

        page.addProductToCart("ZARA COAT 3");

        page.goToCart();

        page.deleteProductFromCart();

        Assert.assertFalse(page.verifyProductInCart("ZARA COAT 3"));
    }

    @Test(priority = 10)
    public void verifyCartTotalPrice() {

        page.login(email, password);

        page.addProductToCart("ZARA COAT 3");

        page.goToCart();

        String total = page.getTotalPrice();

        Assert.assertTrue(total.contains("$"));
    }

    @Test(priority = 11)
    public void verifyCheckoutAndOrderPlacement() {

        page.login(email, password);

        page.addProductToCart("ZARA COAT 3");

        page.goToCart();

        page.proceedToCheckout();

        String message = page.getOrderSuccessMessage();

        Assert.assertTrue(message.contains("THANKYOU"));
    }

    @Test(priority = 12)
    public void verifyOrderHistory() {

        page.login(email, password);

        page.goToOrders();

        Assert.assertTrue(page.verifyOrderHistory());
    }

    @Test(priority = 13)
    public void verifyOrderIdDisplayed() {

        page.login(email, password);

        page.goToOrders();

        Assert.assertTrue(page.verifyOrderIdDisplayed());
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}