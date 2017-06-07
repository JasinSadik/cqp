package pageObjects.popUpWindows.confirmationPopUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.Modals;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;

/**
 * Created by PLJAHAS on 2016-12-19.
 */
public class ConfirmationModal extends Modals {


    public ConfirmationModal(WebDriver driver) {
        super(driver);
    }


    private By yesButton = By.xpath("//div[@id='sharedConfirmationYesNoModal']//button[contains(text(),'Yes')]");
    private By noButton = By.xpath("//div[@id='sharedConfirmationYesNoModal']//button[contains(text(),'No')]");
    private By cancelButton =  By.xpath("//div[@id='sharedConfirmationYesNoModal']//button[contains(text(),'Cancel')]");


    public void pressYesButton(){
        waitOnButton(yesButton);
        click(yesButton);
    }
    public void pressNoButton(){
        waitOnButton(noButton);
        click(noButton);
    }
    public void pressCancelButton(){
        waitOnButton(cancelButton);
        click(cancelButton);
    }

}


