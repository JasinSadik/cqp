package pageObjects.mainPages;

import common.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by PLJAHAS on 2016-11-25.
 */
public class UnauthorizedAccessPage extends Page {

    @FindBy(css ="#UnauthorizedPage .h1")
    private WebElement unauthorizedPageErrorMessage;

    public UnauthorizedAccessPage(WebDriver driver) {
        super(driver);
    }

    public UnauthorizedAccessPage checkUnauthorizedAccessMessageShown(){
        assert "Unauthorized access".equals(unauthorizedPageErrorMessage.getText());
        return this;
    }
}
