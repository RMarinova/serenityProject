package WebPages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {


    WebDriver webDriver;

    public RegisterPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
    }

    @FindBy(xpath = "//input[@name=\"first_name\"]")
    WebElement firstNameField;

    @FindBy(xpath = "//input[@value=\"Mr.\"]")
    WebElement titleMrRadioBtn;

    @FindBy(xpath = "//input[@value=\"Mrs.\"]")
    WebElement titleMrsRadioBtn;

    @FindBy(xpath = "//input[@name=\"sir_name\"]")
    WebElement sirNameField;

    @FindBy(xpath = "//input[@name=\"email\"]")
    WebElement emailField;

    @FindBy(xpath = "//input[@name=\"pass\"]")
    WebElement passwordField;

    @FindBy(xpath = "//input[@name=\"country\"]")
    WebElement countryField;

    @FindBy(xpath = "//input[@name=\"city\"]")
    WebElement cityField;

    @FindBy(xpath = "//input[@id=\"TOS\"]")
    WebElement termsCheckbox;

    @FindBy(name = "signup")
    WebElement registerBtn;

    /////////////////////////////////////////////////////////////////////////////////

    private void clickRadioButton(String text) {

        if (text.equalsIgnoreCase("Mr.")) {

            titleMrRadioBtn.click();
        } else titleMrsRadioBtn.click();
    }

    private void fillInFirstName(String text) {

        firstNameField.clear();
        firstNameField.sendKeys(text);
    }

    private void fillInSirName(String text) {

        sirNameField.clear();
        sirNameField.sendKeys(text);
    }

    private void fillInEmail(String text) {

        emailField.clear();
        emailField.sendKeys(text);
    }

    private void fillInPassword(String text) {

        passwordField.clear();
        passwordField.sendKeys(text);
    }

    private void fillInCountry(String text) {

        countryField.clear();
        countryField.sendKeys(text);
    }

    private void fillInCity(String text) {

        cityField.clear();
        cityField.sendKeys(text);
    }

    public void fillInRegistrationDetails(String title, String firstName, String sirName, String email, String password, String country, String city) {

        clickRadioButton(title);
        fillInFirstName(firstName);
        fillInSirName(sirName);
        fillInEmail(email);
        fillInPassword(password);
        fillInCountry(country);
        fillInCity(city);
    }

    public void assertRegisterButtonIsPresent() throws InterruptedException {

        Thread.sleep(2000);
        Assert.assertTrue(registerBtn.isDisplayed());
    }

    public void assertPopUpErrorMessage() throws InterruptedException {

        Thread.sleep(1000);
        String PopUpMessage = webDriver.switchTo().alert().getText();
        webDriver.switchTo().alert().accept();
        String ExpectedMessage = "You must agree with terms of service";

        Assert.assertEquals(PopUpMessage, ExpectedMessage);
    }

    public void clickTermsCheckbox(){
        termsCheckbox.click();
    }

    public void clickRegisterBtn(){
        registerBtn.click();
    }

//    public void addUser(UserModel user) {
//
//        users.add(user);
//    }
}
