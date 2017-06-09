package pageObjects.popUpWindows.confirmationPopUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.Modals;

/**
 * Created by PLJAHAS on 2017-06-08.
 */
public class ChangeStatusConfirmationModal extends Modals {
    public ChangeStatusConfirmationModal(WebDriver driver) {
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
