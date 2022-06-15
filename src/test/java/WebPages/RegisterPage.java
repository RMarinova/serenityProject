package POM;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RegisterPage {
    private WebDriver webDriver;

    private List<User>users=new ArrayList<>();

    public void addUser(User user) {

        users.add(user);
    }

    public RegisterPage(WebDriver webDriver) {

        this.webDriver = webDriver;
    }

    public WebElement firstNameField() {

        return webDriver.findElement(By.xpath("//input[@name=\"first_name\"]"));
    }

    private WebElement titleMrRadioButton() {

        return webDriver.findElement(By.xpath("//input[@value=\"Mr.\"]"));
    }

    private WebElement titleMrsRadioButton() {

        return webDriver.findElement(By.xpath("//input[@value=\"Mrs.\"]"));
    }

    public WebElement sirNameField() {

        return webDriver.findElement(By.xpath("//input[@name=\"sir_name\"]"));
    }

    public WebElement emailField() {

        return webDriver.findElement(By.xpath("//input[@name=\"email\"]"));
    }

    public WebElement passwordField() {

        return webDriver.findElement(By.xpath("//input[@name=\"pass\"]"));
    }

    public WebElement countryField() {

        return webDriver.findElement(By.xpath("//input[@name=\"country\"]"));
    }

    public WebElement cityField() {

        return webDriver.findElement(By.xpath("//input[@name=\"city\"]"));
    }

    public WebElement termsCheckbox() {

        return webDriver.findElement(By.xpath("//input[@id=\"TOS\"]"));
    }

    public WebElement registerButton() {

        return webDriver.findElement(By.name("signup"));
    }

    public void clickRadioButton(String text) {

        if (text.equalsIgnoreCase("Mr.")) {

            titleMrRadioButton().click();
        } else titleMrsRadioButton().click();
    }

    public void fillInFirstName(String text) {

        firstNameField().clear();
        firstNameField().sendKeys(text);
    }

    public void fillInSirName(String text) {

        sirNameField().clear();
        sirNameField().sendKeys(text);
    }

    public void fillInEmail(String text) {

        emailField().clear();
        emailField().sendKeys(text);
    }

    public void fillInPassword(String text) {

        passwordField().clear();
        passwordField().sendKeys(text);
    }

    public void fillInCountry(String text) {

        countryField().clear();
        countryField().sendKeys(text);
    }

    public void fillInCity(String text) {

        cityField().clear();
        cityField().sendKeys(text);
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

        Thread.sleep(3000);
        Assert.assertTrue(registerButton().isDisplayed());
    }

    public void assertPopUpErrorMessage() throws InterruptedException {

        Thread.sleep(1000);
        String PopUpMessage = webDriver.switchTo().alert().getText();
        webDriver.switchTo().alert().accept();
        String ExpectedMessage = "You must agree with terms of service";

        Assert.assertEquals(PopUpMessage, ExpectedMessage);
    }
}
