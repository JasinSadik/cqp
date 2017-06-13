package pageObjects.popUpWindows.confirmationPopUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.Modals;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesPage;

/**
 * Created by PLJAHAS on 2016-12-20.
 */
public class SfdcSyncConfirmationModal extends Modals {
    public SfdcSyncConfirmationModal(WebDriver driver) {
        super(driver);
    }

    private By confirmButton = By.xpath("//div[@id='sharedConfirmationModal']//button[contains(text(),'Confirm')]");
    private By cancelButton = By.xpath("//div[@id='sharedConfirmationModal']//button[contains(text(),'Cancel')]");

    public void pressConfirmButton() {
        waitOnButton(confirmButton);
        click(confirmButton);
    }

    public void pressCancelButton() {
        waitOnButton(cancelButton);
        click(cancelButton);
    }

}
