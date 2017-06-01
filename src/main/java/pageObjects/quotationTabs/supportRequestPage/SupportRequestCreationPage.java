package pageObjects.quotationTabs.supportRequestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PLJAHAS on 2016-12-27.
 */
public class SupportRequestCreationPage extends SupportRequestPage {
    public SupportRequestCreationPage(WebDriver driver) {
        super(driver);
    }

    private By requestMessageField = By.xpath("//span[text()='Request (*):']/../div");
    private By reasonForPriceSupportDropdownListExpandButton = By.xpath("//span[text()='Reason for price support (*)']/..//span[text()='select']");
    private By sendRequestButton = By.xpath("//button[text()='Send Request']");
    private By selectUserDropdownListExpandButton = By.xpath("//span[text()='Select user (*):']/..//span[text()='select']");
    private By selectSpecifyUserCheckbox = By.xpath("//span[text()='Select specific user:']/..//input");

    public SupportRequestCreationPage insertRequestMessage(String message) {
        waitOnPresenceOfElement(requestMessageField);
        sendKeys(requestMessageField, message);
        return this;
    }

    public SupportRequestCreationPage checkSelectSpecificUserCheckbox(){
        waitOnElementToBeClickable(selectSpecifyUserCheckbox);
        click(selectSpecifyUserCheckbox);
        return this;

    }

    public SupportRequestCreationPage setSpecificUser(String username) {
        waitOnElementToBeClickable(selectUserDropdownListExpandButton);
        click(selectUserDropdownListExpandButton);
        selectElementFromDropdownListByHtmlElement(username);
        return this;
    }

    public SupportRequestCreationPage setReasonForPriceSupport(String reason) {
        waitOnElementToBeClickable(reasonForPriceSupportDropdownListExpandButton);
        click(reasonForPriceSupportDropdownListExpandButton);
        selectElementFromDropdownListByHtmlElement(reason);
        return this;
    }

    public SupportRequestCreationPage pressSendSupportRequestButton() {
        waitOnButton(sendRequestButton);
        click(sendRequestButton);
        return this;
    }

    public SupportRequestCreationPage createSupportRequestForSpecificUser(String username, String message, String reason) {
        checkSelectSpecificUserCheckbox();
        setSpecificUser(username);
        insertRequestMessage(message);
        setReasonForPriceSupport(reason);
        pressSendSupportRequestButton();
        return this;
    }

}
