package pageObjects.popUpWindows.confirmationPopUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.Modals;

/**
 * Created by PLLIKOS on 2017-06-08.
 */
public class RemoveRequestConfirmationPopUp extends Modals {

    public RemoveRequestConfirmationPopUp(WebDriver driver) {
        super(driver);
    }

    private By removeButton = By.xpath("//div[@class ='modal-footer']//button[contains(text(),'Remove')]");

    public void pressRemoveRequestConfirmationButton (){
        waitOnButton(removeButton);
        click(removeButton);
    }

}
