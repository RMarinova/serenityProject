package WebPages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver webDriver;


    @FindBy(xpath = "//input[@name=\"email\"]")
    WebElement userNameField;

    @FindBy(xpath = "//input[@name=\"pass\"]")
    WebElement passwordField;

    @FindBy(xpath = "//button[@name=\"btn-login\"]")
    WebElement loginBtn;

    @FindBy(xpath = "//a[@href=\"register.php\"]")
    WebElement registerBtn;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    public void fillInUsername(String username) {
        userNameField.clear();
        userNameField.sendKeys(username);
    }

    public void filInPassword(String password) {

        passwordField.clear();
        passwordField.sendKeys(password);
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

        retryClick(loginBtn);
    }


    public void clickRegisterButton() {

        retryClick(registerBtn);
    }

    public void assertLoginButtonIsPresent() throws InterruptedException {

        Thread.sleep(2000);
        Assert.assertTrue(loginBtn.isDisplayed());
    }
}
