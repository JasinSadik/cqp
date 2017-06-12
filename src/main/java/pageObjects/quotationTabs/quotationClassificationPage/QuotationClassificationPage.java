package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.confirmationPopUp.SfdcSyncConfirmationModal;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-06.
 */
public class QuotationClassificationPage extends QuotationNavigationBar {

    public QuotationClassificationPage(WebDriver driver) {
        super(driver);

    }

    private By createQuotationButton = By.id("btnCreateQuotation");
    private By deleteQuotationButton = By.xpath("//*[@id='newQuotation']//span[4]//button[text()='Delete quotation']");

    public void pressCreateQuotationButton() {
        waitOnButton(createQuotationButton);
        click(createQuotationButton);
    }

    public void pressDeleteQuotationButton() {
        waitOnButton(deleteQuotationButton);
        click(deleteQuotationButton);
    }
}
