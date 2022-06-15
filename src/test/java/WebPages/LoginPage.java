package POM;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver webDriver;


    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    public WebElement userNameField() {

        return webDriver.findElement(By.xpath("//input[@name=\"email\"]"));
    }

    public WebElement passwordField() {

        return webDriver.findElement(By.xpath("//input[@name=\"pass\"]"));
    }

    public WebElement logInButton() {

        return webDriver.findElement(By.xpath("//button[@name=\"btn-login\"]"));
    }

    public WebElement registerButton() {

        return webDriver.findElement(By.xpath("//a[@href=\"register.php\"]"));
    }

    public void fillInUsername(String username) {

        userNameField().clear();
        userNameField().sendKeys(username);
    }

    public void filInPassword(String password) {

        passwordField().clear();
        passwordField().sendKeys(password);
    }

    public void retryClick(WebElement element) {

        int maxTries = 10;
        for (int i = 0; i <= maxTries; i++) {
            try {
                element.click();
                break;
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == maxTries) {
                    throw ex;
                }
            }
        }
    }

    public void clickLoginButton() {

        retryClick(logInButton());
    }


    public void clickRegisterButton() {

        retryClick(registerButton());
    }

    public void assertLoginButtonIsPresent() throws InterruptedException {

        Thread.sleep(3000);

        Assert.assertTrue(logInButton().isDisplayed());
    }
}
