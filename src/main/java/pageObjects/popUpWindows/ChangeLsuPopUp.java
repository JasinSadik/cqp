package pageObjects.popUpWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.mainPages.TopMenu;

/**
 * Created by PLJAHAS on 2017-06-01.
 */
public class ChangeLsuPopUp extends Modals {
    public ChangeLsuPopUp(WebDriver driver) {
        super(driver);
    }

    private By lsuComboBox = By.xpath("//*[@id='headerLinks']/div[1]/div[2]/div/div/span[2]/span/span[2]/span");
    private By confirmButton = By.xpath("//*[@id='headerLinks']/div[1]/div[3]/button[2]");

    public void changeLsu(String LsuName){
        waitOnButton(lsuComboBox);
        selectElementFromDropdownListByHtmlElement(LsuName);
        pressConfirmButton();
    }

    public void pressConfirmButton(){
        waitOnButton(confirmButton);
        click(confirmButton);
    }


    public void getUsersLsus(){ //not completed
        getElementsFromDropdownList("id");
        click(confirmButton);
    }

}
