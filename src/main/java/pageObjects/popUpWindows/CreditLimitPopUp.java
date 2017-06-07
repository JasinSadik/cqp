package pageObjects.popUpWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PLLIKOS on 2017-06-06.
 */
public class CreditLimitPopUp extends Modals {

    public CreditLimitPopUp(WebDriver driver) {
        super(driver);
    }

    private By okButton = By.xpath("//*[@id='infoPopup']/div[3]/button");

    public void pressOkButton (){
        waitOnButton(okButton);
        click(okButton);
    }
}
