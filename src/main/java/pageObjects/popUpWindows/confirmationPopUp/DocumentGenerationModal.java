package pageObjects.popUpWindows.confirmationPopUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.Modals;
import pageObjects.quotationTabs.fullCostAndFinalizationPage.DocumentGenerationSection;

/**
 * Created by PLJAHAS on 2016-12-30.
 */
public class DocumentGenerationModal extends Modals {
    public DocumentGenerationModal(WebDriver driver) {
        super(driver);
    }

    private By confirmButton = By.xpath("");

    public Modals pressConfirmButton(){
        waitOnButton(confirmButton);
        click(confirmButton);
        return new DocumentGenerationModal(driver);
    }


}
