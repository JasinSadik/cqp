package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.By;
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

    private By createQuotationButton = By.id("btnCreateQuotation");

    public CommonActionButtonsSection pressCreateQuotationButton (){
        waitOnButton(createQuotationButton);
        click(createQuotationButton);
        return this;
    }



}
