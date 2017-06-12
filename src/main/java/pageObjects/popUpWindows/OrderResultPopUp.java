package pageObjects.popUpWindows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PLJAHAS on 2017-06-08.
 */
public class OrderResultPopUp extends Modals {
    public OrderResultPopUp(WebDriver driver) {
        super(driver);
    }

    private By omsOrderStatus = By.xpath("div[@id='OMSHistoryModal']//span");




}
