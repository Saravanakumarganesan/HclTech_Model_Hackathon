package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    public WebDriver driver;
    public WebDriverWait wait;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    By email = By.id("userEmail");
    By password = By.id("userPassword");
    By loginBtn = By.id("login");

    By errorMsg = By.cssSelector(".toast-error");

    By productCards = By.cssSelector(".card-body");
    By addToCartBtn = By.xpath("//button[contains(text(),'Add To Cart')]");

    By cartBadge = By.cssSelector("[routerlink='/dashboard/cart'] label");

    By cartBtn = By.cssSelector("[routerlink='/dashboard/cart']");

    By cartProducts = By.cssSelector(".cartSection h3");

    By deleteBtn = By.cssSelector(".btn-danger");

    By totalPrice = By.cssSelector(".totalRow span");

    By checkoutBtn = By.xpath("//button[contains(text(),'Checkout')]");

    By placeOrderBtn = By.xpath("//a[contains(text(),'Place Order')]");

    By country = By.xpath("//input[@placeholder='Select Country']");

    By countryOption = By.xpath("//button[contains(@class,'ta-item')]");

    By orderSuccess = By.cssSelector(".hero-primary");

    By ordersBtn = By.cssSelector("[routerlink='/dashboard/myorders']");

    By orderRows = By.cssSelector("tbody tr");

    By orderId = By.cssSelector("tbody tr th");

    public void openApplication() {
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
    }

    public void login(String userEmail, String userPassword) {
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).sendKeys(userPassword);
        driver.findElement(loginBtn).click();
    }

    public String getLoginErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
        return driver.findElement(errorMsg).getText();
    }

    public int getProductCount() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards));
        return driver.findElements(productCards).size();
    }

    public boolean verifyProductNameAndPrice() {
        List<WebElement> products = driver.findElements(productCards);

        for (WebElement product : products) {

            String name = product.findElement(By.cssSelector("b")).getText();

            String price = product.findElement(By.cssSelector(".text-muted")).getText();

            if (name.isEmpty() || price.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void addProductToCart(String productName) {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productCards));

        List<WebElement> products = driver.findElements(productCards);

        for (WebElement product : products) {

            String name = product.findElement(By.cssSelector("b")).getText();

            if (name.equalsIgnoreCase(productName)) {

                product.findElement(By.xpath(".//button[contains(text(),'Add To Cart')]")).click();

                break;
            }
        }
    }

    public String getCartBadgeCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartBadge));
        return driver.findElement(cartBadge).getText();
    }

    public void goToCart() {
        driver.findElement(cartBtn).click();
    }

    public boolean verifyProductInCart(String productName) {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartProducts));

        List<WebElement> products = driver.findElements(cartProducts);

        for (WebElement product : products) {

            if (product.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public void deleteProductFromCart() {

        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
        driver.findElement(deleteBtn).click();
    }

    public String getTotalPrice() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));
        return driver.findElement(totalPrice).getText();
    }

    public void proceedToCheckout() {

        driver.findElement(checkoutBtn).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(country));

        driver.findElement(country).sendKeys("India");

        wait.until(ExpectedConditions.visibilityOfElementLocated(countryOption));

        driver.findElement(countryOption).click();

        driver.findElement(placeOrderBtn).click();
    }

    public String getOrderSuccessMessage() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccess));

        return driver.findElement(orderSuccess).getText();
    }

    public void goToOrders() {
        driver.findElement(ordersBtn).click();
    }

    public boolean verifyOrderHistory() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(orderRows));

        return driver.findElements(orderRows).size() > 0;
    }

    public boolean verifyOrderIdDisplayed() {

        return driver.findElement(orderId).isDisplayed();
    }

    public boolean validateEmptyLogin() {

        driver.findElement(loginBtn).click();

        return driver.findElement(email).getAttribute("validationMessage").length() > 0;
    }
}