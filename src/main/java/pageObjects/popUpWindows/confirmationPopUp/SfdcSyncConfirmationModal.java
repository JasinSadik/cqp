package pageObjects.popUpWindows.confirmationPopUp;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        try{
            new WebDriverWait(driver,2).until(ExpectedConditions.elementToBeClickable(confirmButton));
            click(confirmButton);
        }catch (TimeoutException e){

        }
    }

    public void pressCancelButton() {
        waitOnButton(cancelButton);
        click(cancelButton);
    }

}
