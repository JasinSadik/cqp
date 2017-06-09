package pageObjects.popUpWindows.confirmationPopUp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.Modals;

/**
 * Created by PLLIKOS on 2017-06-08.
 */
public class DeleteQuotation extends Modals{
    public DeleteQuotation(WebDriver driver) {
        super(driver);
    }
    private By confirmButton = By.xpath("//*[@id='newQuotation']//button[contains(text(),'Confirm')]");

    public void pressConfirmButton (){
        waitOnButton(confirmButton);
        click(confirmButton);
    }
}

