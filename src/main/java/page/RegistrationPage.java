package page;

import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By nameField = By.xpath("//label[text()='Имя']/following-sibling::input");
    private By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private By passwordField = By.xpath("//input[@type='password']");
    private By registrationButton = By.xpath("//*[@id=\"root\"]/div/main/div/form/button");
    private By loginButton = By.className("Auth_link__1fOlj");
    private By passwordError = By.className("input__error");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void enterName(String name) {

        driver.findElement(nameField).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @SneakyThrows
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void registration(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegistrationButton();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public WebElement waitPasswordErrorVisible(String password) {
        enterPassword(password);
        driver.findElement(emailField).click();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError));
    }

    public void scrollToLoginButton() {
        WebElement element = driver.findElement(loginButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

}
