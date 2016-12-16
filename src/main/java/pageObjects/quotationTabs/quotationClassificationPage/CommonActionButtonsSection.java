package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-06.
 */
public class CommonActionButtonsSection extends QuotationNavigationBar {

    public CommonActionButtonsSection(WebDriver driver) {
        super(driver);

    }
    @FindBy(css =" #newQuotation button.primaryButton")
    private WebElement createQuotationButton;

    public CommonActionButtonsSection pressCreateQuotationButton (){
        createQuotationButton.click();
        return this;
    }



}
