package pageObjects.mainPages;

import common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class TopMenu extends CommonMethods {
    public TopMenu(WebDriver driver) {
        super(driver);
    }

    private By logoutHyperlink = By.xpath("//a[text()='Logout']");

    public LoginPage pressLogoutHyperlink(){
        waitOnElementToBeClickable(logoutHyperlink);
        click(logoutHyperlink);
        return new LoginPage(driver);
    }


}
