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

    private By confirmButton = By.xpath("//*[@id='headerLinks']/div[1]/div[3]/button[2]");
    private By expandChangeUnitDropdownList = By.xpath("//span[text()='Choose one...']/..//span[text()='select']");
    public void changeLsu(String LsuName){
        waitOnButton(expandChangeUnitDropdownList);
        expandChangeUnitDropdownList();
        selectElementFromDropdownListByHtmlElement(LsuName);
        pressConfirmButton();
    }

    public void pressConfirmButton(){
        waitOnButton(confirmButton);
        click(confirmButton);
    }

    public void expandChangeUnitDropdownList(){
        waitOnElementToBeClickable(expandChangeUnitDropdownList);
        expandDrowdownList(expandChangeUnitDropdownList);
    }


    public void getUsersLsus(){ //not completed
        //getElementsFromDropdownList("id");
        click(confirmButton);
    }

}
