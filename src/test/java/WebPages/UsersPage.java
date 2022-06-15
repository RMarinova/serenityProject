package POM;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UsersPage {
    WebDriver webDriver;

    public UsersPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<WebElement> emailFields() {

        return webDriver.findElements(By.xpath("//tr/td[6]"));

    }

    public List<WebElement> deleteFields() {

        return webDriver.findElements(By.xpath("//tr/td[7]"));

    }

    public void deleteUser(String userName) {

        for (int i = 0; i <= emailFields().size() - 1; i++) {
            if (emailFields().get(i).getText().contains(userName)) {
                deleteFields().get(i).click();
                break;
            }
        }

        webDriver.switchTo().alert().accept();
    }

    public void assertUserIsDeleted(String userName) {
        boolean present = false;
        for (int i = 0; i <= emailFields().size() - 1; i++) {
            if (emailFields().get(i).getText().contains(userName)) {
                present = true;
                break;
            }
        }
        Assert.assertFalse(present);
    }
}

