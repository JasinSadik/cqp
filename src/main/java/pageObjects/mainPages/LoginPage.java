package pageObjects.mainPages;

import common.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class LoginPage extends CommonMethods {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name='UserName']")
    private WebElement usernameField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(css = "p.submit >input")
    private WebElement loginButton;

    public LoginPage insertUsername(String username) {
        waitOnElementToBeVisible(usernameField);
        usernameField.clear();
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage insertPassword(String password) {
        waitOnElementToBeVisible(usernameField);
        passwordField.clear();
        passwordField.sendKeys(password);
        return this;
    }

    public LoginPage pressSubmitButton() {
        waitOnElementToBeVisible(loginButton);
        loginButton.click();
        return this;
    }

    public UnauthorizedAccessPage invalidLogInToCqp(String username, String password) {

        return new UnauthorizedAccessPage(driver);
    }

    public LsuDashboard logInToCqp(String username, String password) {

        return new LsuDashboard(driver);
    }
}
