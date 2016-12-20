package pageObjects.popUpWindows.confirmationPopUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesCommonActionButtons;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;

/**
 * Created by PLJAHAS on 2016-12-20.
 */
public class SfdcSyncConfirmationModal extends QuotationNavigationBar {
    public SfdcSyncConfirmationModal(WebDriver driver) {
        super(driver);
    }
    private By confirmButton = By.xpath("//div[@id='sharedConfirmationModal']//button[contains(text(),'Confirm')]");
    private By cancelButton =  By.xpath("//div[@id='sharedConfirmationModal']//button[contains(text(),'Cancel')]");

    public ProductsAndPricesCommonActionButtons pressConfirmButton(){
        waitOnButton(confirmButton);
        click(confirmButton);
        return new ProductsAndPricesCommonActionButtons(driver);
    }
    public ProductsAndPricesCommonActionButtons pressCancelButton(){
        waitOnButton(cancelButton);
        click(cancelButton);
        return new ProductsAndPricesCommonActionButtons(driver);
    }

}
