package pageObjects.quotationTabs.supportRequestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-27.
 */
public class SupportRequestStartPage extends QuotationNavigationBar {
    public SupportRequestStartPage(WebDriver driver) {
        super(driver);
    }

    private By requestMessageField = By.xpath("//span[text()='Request (*):']/../div");
    private By reasonForPriceSupportDropdownListExpandButton = By.xpath("//span[text()='Reason for price support (*)']/..//span[text()='select']");
    private By sendRequestButton = By.xpath("//button[text()='Send Request']");
    private By selectUserDropdownListExpandButton = By.xpath("//span[text()='Select user (*):']/..//span[text()='select']");
    private By selectSpecifyUserCheckbox = By.xpath("//span[text()='Select specific user:']/..//input");

    public SupportRequestStartPage insertRequestMessage(String message) {
        waitOnPresenceOfElement(requestMessageField);
        sendKeys(requestMessageField, message);
        return this;
    }

    public SupportRequestStartPage checkSelectSpecificUserCheckbox(){
        waitOnElementToBeClickable(selectSpecifyUserCheckbox);
        click(selectSpecifyUserCheckbox);
        return this;

    }

    public SupportRequestStartPage setSpecificUser(String username) {
        waitOnElementToBeClickable(selectUserDropdownListExpandButton);
        click(selectUserDropdownListExpandButton);
        selectElementFromDropdownListByHtmlElement(username);
        return this;
    }

    public SupportRequestStartPage setReasonForPriceSupport(String reason) {
        waitOnElementToBeClickable(reasonForPriceSupportDropdownListExpandButton);
        click(reasonForPriceSupportDropdownListExpandButton);
        selectElementFromDropdownListByHtmlElement(reason);
        return this;
    }

    public SupportRequestStartPage pressSendSupportRequestButton() {
        waitOnButton(sendRequestButton);
        click(sendRequestButton);
        return this;
    }

    public SupportRequestStartPage createSupportRequestForSpecificUser(String username, String message, String reason) {
        checkSelectSpecificUserCheckbox();
        setSpecificUser(username);
        insertRequestMessage(message);
        setReasonForPriceSupport(reason);
        pressSendSupportRequestButton();
        return this;
    }

}
