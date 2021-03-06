package WebPages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {


    @FindBy(xpath = "//a[@href=\"logout.php?logout\"]")
    WebElement logoutButton;

    @FindBy(how = How.XPATH, using = "//a[@data-toggle=\"dropdown\"]")
    WebElement loginBar;

    @FindBy(xpath = "//*[text()=\"Users\"]")
    WebElement usersButton;

    public HomePage(WebDriver webDriver) {

        PageFactory.initElements(webDriver, this);

    }


    ////////////////////////////////////////////////////////////////////////////////

    public WebElement getLoginBarElement(){
        return loginBar;
    }

    public void logoutFromAccount() {

        loginBar.click();
        logoutButton.click();
    }

    public void ClickUsersButton() {

        usersButton.click();
    }

    public void assertUserIsLoggedIn(String username){

        Assert.assertTrue(loginBar.getText().toLowerCase().contains(username));
    }


}
