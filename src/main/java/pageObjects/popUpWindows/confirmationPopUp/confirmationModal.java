package pageObjects.popUpWindows.confirmationPopUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;

/**
 * Created by PLJAHAS on 2016-12-19.
 */
public class ConfirmationModal extends QuotationNavigationBar {


    public ConfirmationModal(WebDriver driver) {
        super(driver);
    }


    private By yesButton = By.xpath("//div[@id='sharedConfirmationYesNoModal']//button[contains(text(),'Yes')]");
    private By noButton = By.xpath("//div[@id='sharedConfirmationYesNoModal']//button[contains(text(),'No')]");
    private By cancelButton =  By.xpath("//div[@id='sharedConfirmationYesNoModal']//button[contains(text(),'Cancel')]");


    public AdditionalDataSection pressYesButton(){
        waitOnButton(yesButton);
        click(yesButton);
        return new AdditionalDataSection(driver);
    }
    public AdditionalDataSection pressNoButton(){
        waitOnButton(noButton);
        click(noButton);
        return new AdditionalDataSection(driver);
    }
    public AdditionalDataSection pressCancelButton(){
        waitOnButton(cancelButton);
        click(cancelButton);
        return new AdditionalDataSection(driver);
    }

}


