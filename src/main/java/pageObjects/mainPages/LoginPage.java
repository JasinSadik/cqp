package pageObjects.mainPages;

import common.CommonMethods;
import org.apache.commons.logging.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class LoginPage extends CommonMethods {

    public LoginPage(WebDriver driver) {
        super(driver);
        waitForPageLoad(driver);
    }

    private By usernameField = By.name("UserName");
    private By passwordField = By.name("Password");
    private By loginButton = By.cssSelector("p.submit >input");
    private By createNewQuoteButton = By.xpath("//*[@id='buttonDiv'']/div[1]/a");


    public void pressNewQuoteButton() {
        click(createNewQuoteButton);
    }

    public void insertUsername(String username) {
        waitOnPresenceOfElement(usernameField);
        clear(usernameField);
        sendKeys(usernameField, username);
    }

    public void insertPassword(String password) {
        waitOnPresenceOfElement(passwordField);
        clear(passwordField);
        sendKeys(passwordField, password);
    }

    public void pressSubmitButton() {
        click(loginButton);
    }

    public void invalidLogInToCqp(String username, String password) {
        insertUsername(username);
        insertPassword(password);
        pressSubmitButton();
    }

    public void logInToCqp(String username, String password) {
        insertUsername(username);
        insertPassword(password);
        pressSubmitButton();
    }


}
