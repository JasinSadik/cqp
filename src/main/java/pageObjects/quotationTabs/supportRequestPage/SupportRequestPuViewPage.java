package pageObjects.quotationTabs.supportRequestPage;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.thoughtworks.selenium.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-28.
 */
public class SupportRequestPuViewPage extends QuotationNavigationBar {
    public SupportRequestPuViewPage(WebDriver driver) {
        super(driver);
    }

    private By forwardButton = By.id("forwardButton");
    private By replyAndCloseButton = By.id("finalReplyButton");
    private By onHoldButton = By.id("onHoldButton");
    private By requestMoreInfoButton = By.id("getInfoButton");
    private By informButton = By.id("informButton");
    private By delegateButton = By.id("delegateButton");
    private By delegateToGroupButton = By.id("delegateToGroupButton");
    private By commentTextArea = By.xpath("//div[contains(@class, 'commentTextArea')]");
    private By sendRequestButton = By.xpath("//div[@class='commentButtonBar']//button[text()='Send']");
    private By statusLabel = By.xpath("//span[text()='Status: ']/..//span[2]");
    private By goToProductHyperlink = By.xpath("//a[text()='Go to product']");

    private void waitOnLogOfAction(String action, String message) {
        waitOnPresenceOfElement(By.xpath("//span[contains(@class,'requestText')]//div[text()='" + message + "']/../../../../../../..//span[text()='" + action + "']"));
    }

    public String getCurrentStatus() {
        waitForPageLoad(driver);
        waitOnPresenceOfElement(statusLabel);
        return getText(statusLabel);
    }

    public SupportRequestPuViewPage replyAndCloseRequest(String message) {
        pressReplyAndCloseButton();
        insertComment(message);
        pressSendRequestButton();
        waitOnLogOfAction("Final Reply", message);
        return this;
    }

    private SupportRequestPuViewPage insertComment(String message) {
        waitOnPresenceOfElement(commentTextArea);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(commentTextArea, message);
        return this;
    }

    private SupportRequestPuViewPage pressSendRequestButton() {
        waitOnButton(sendRequestButton);
        driver.findElement(sendRequestButton).click();
        return this;
    }

    private SupportRequestPuViewPage pressReplyAndCloseButton() {
        waitOnButton(replyAndCloseButton);
        click(replyAndCloseButton);
        return this;
    }

    private boolean checkButtonAvailability(By by) {
        waitForPageLoad(driver);
        try {
            setTimeout(driver, 60);
            driver.findElement(by);
            setTimeout(driver, 30);
            return true;
        } catch (ElementNotFoundException e) {
            return false;
        }
    }

    public boolean checkForwardButtonAvailability() {
        return checkButtonAvailability(forwardButton);
    }

    public boolean checkReplyAndCloseButtonAvailability() {
        return checkButtonAvailability(replyAndCloseButton);
    }

    public boolean checkOnHoldButtonAvailability() {
        return checkButtonAvailability(onHoldButton);
    }

    public boolean checkRequestMoreInfoButtonAvailability() {
        return checkButtonAvailability(requestMoreInfoButton);
    }

    public boolean checkInformButtonAvailability() {
        return checkButtonAvailability(informButton);
    }

    public boolean checkDelegateButtonAvailability() {
        return checkButtonAvailability(delegateButton);
    }

    public boolean checkDelegateToGroupButtonAvailability() {
        return checkButtonAvailability(delegateToGroupButton);
    }

}
