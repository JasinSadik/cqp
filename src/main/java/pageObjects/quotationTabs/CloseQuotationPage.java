package pageObjects.quotationTabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PLJAHAS on 2016-12-30.
 */
public class CloseQuotationPage extends QuotationNavigationBar{
    public CloseQuotationPage(WebDriver driver) {
        super(driver);
    }

    private By submitOrderButton = By.xpath("");
    private By newStatusDropdownLostExpandButton = By.xpath("//span[text()='New status:']/..//span[text()='select']");
    private By saveStatusButton = By.xpath("//button[text()='Save status']");


    private void expandNewStatusDropdownList(){
        waitOnElementToBeClickable(newStatusDropdownLostExpandButton);
        click(newStatusDropdownLostExpandButton);
    }

    private void pressSaveStatusButton(){
        waitOnButton(saveStatusButton);
        click(saveStatusButton);
    }

    public void setWonStatus() {
        expandNewStatusDropdownList();
        selectElementFromDropdownListByHtmlElement("Won");
        pressSaveStatusButton();
        //wait on status
    }

    public void fillInPreOrderData() {
    }

    public void setProductOrderingSystem(int i, String oms) {
    }

    public void submitOrder() {
    }
}
