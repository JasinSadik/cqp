package pageObjects.mainPages;

import common.CommonMethods;
import common.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by PLJAHAS on 2016-11-25.
 */
public class UnauthorizedAccessPage extends CommonMethods {


    private By unauthorizedPageErrorMessage = By.cssSelector( "#UnauthorizedPage .h1");

    public UnauthorizedAccessPage(WebDriver driver) {
        super(driver);
    }

    public boolean checkUnauthorizedAccessMessageShown(){
        return  "Unauthorized access".equals(getText(unauthorizedPageErrorMessage));

    }
}
