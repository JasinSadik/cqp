package pageObjects.quotationTabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.popUpWindows.confirmationPopUp.ChangeStatusConfirmationModal;

/**
 * Created by PLJAHAS on 2016-12-30.
 */
public class CloseQuotationPage extends QuotationNavigationBar {
    public CloseQuotationPage(WebDriver driver) {
        super(driver);
    }

    private int positionLine = 1;

    private void setPositionLine(int desiredLine) {
        selectProductCheckbox = By.xpath("//th[contains(text(), 'Material code')]/../../..//tbody/tr[" + positionLine + "]//td[7]/input");
    }

    private By newStatusDropdownLostExpandButton = By.xpath("//span[text()='New status:']/..//span[text()='select']");
    private By saveStatusButton = By.xpath("//button[text()='Save status']");
    private By editPreOrderDataButton = By.xpath("//div[@id='quotationGeneral']//button[text()='Edit']");
    private By savePreOrderDataSectionButton = By.xpath("//div[@id='quotationGeneral']//button[text()='Save']");
    private By submitOrderButton = By.xpath("//button[text()='Submit Order']/../../div[4]/button");
    private By selectProductCheckbox = By.xpath("//th[contains(text(), 'Material code')]/../../..//tbody/tr[" + positionLine + "]//td[7]/input");
    private By customerOrderNumberInput = By.xpath("//div[@id='quotationGeneral']//div[@class ='editItem'][4]/input");

    private void expandNewStatusDropdownList() {
        waitOnElementToBeClickable(newStatusDropdownLostExpandButton);
        click(newStatusDropdownLostExpandButton);
    }

    private void pressSaveStatusButton() {
        waitOnButton(saveStatusButton);
        click(saveStatusButton);
    }

    public void setWonStatus() {
        expandNewStatusDropdownList();
        selectElementFromDropdownListByHtmlElement("Won");
        pressSaveStatusButton();
        new ChangeStatusConfirmationModal(driver).pressConfirmButton();
    }

    public void insertCustomerOrderNumber(String customerOrderNumber) {
        waitOnPresenceOfElement(customerOrderNumberInput);
        sendKeys(customerOrderNumberInput, customerOrderNumber);

    }

    public void pressEditPreOrderDataButton() {
        waitOnButton(editPreOrderDataButton);
        click(editPreOrderDataButton);
    }

    public void pressSavePreOrderDataButton() {
        waitOnButton(savePreOrderDataSectionButton);
        click(savePreOrderDataSectionButton);
    }

    public void fillInPreOrderData(String customerOrderNumber) {
        pressEditPreOrderDataButton();
        insertCustomerOrderNumber(customerOrderNumber);
        pressSavePreOrderDataButton();

    }

    public void selectProductForOrderingCheckbox(int desiredLine){
        setPositionLine(desiredLine);
        waitOnElementToBeClickable(selectProductCheckbox);
        click(selectProductCheckbox);
    }

    public void setProductOrderingSystem(String orderingSystem) {

    }

    public void selectProductForOrdering(int desiredLine, String orderingSystem){
        selectProductForOrderingCheckbox(desiredLine);
        setProductOrderingSystem(orderingSystem);
    }

    public void pressSubmitOrderButton() {
        waitOnButton(submitOrderButton);
        click(submitOrderButton);
    }
}
