package pageObjects.quotationTabs.quotationClassificationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.confirmationPopUp.SfdcSyncConfirmationModal;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-06.
 */
public class QuotationClassificationCommonActionButtonsSection extends QuotationNavigationBar {

    public QuotationClassificationCommonActionButtonsSection(WebDriver driver) {
        super(driver);

    }

    private By createQuotationButton = By.id("btnCreateQuotation");

    public SfdcSyncConfirmationModal pressCreateQuotationButton (){
        waitOnButton(createQuotationButton);
        click(createQuotationButton);
        return new SfdcSyncConfirmationModal(driver);
    }



}
