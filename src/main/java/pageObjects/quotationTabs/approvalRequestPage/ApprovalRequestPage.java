package pageObjects.quotationTabs.approvalRequestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-23.
 */
public class ApprovalRequestPage extends QuotationNavigationBar {
    public ApprovalRequestPage(WebDriver driver) {
        super(driver);
    }

    private By nextButton = By.xpath("//button[text() = 'Next']");
    private By submitForApprovalButton = By.xpath("//button[text() = 'Submit for approval']");
    private By approveButton = By.xpath("//button[text() = 'Approve']");
    private By sendButton = By.xpath("//button[text() = 'Send']");
    private By commentForApprovalField = By.xpath("//span[text() = 'Add comment:']/../div/div");

    private ApprovalRequestPage insertComment(String message){
        waitOnPresenceOfElement(commentForApprovalField);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(commentForApprovalField, message);
        return this;
    }


    private ApprovalRequestPage pressSendButton() {
        waitOnButton(sendButton);
        click(sendButton);
        return this;
    }

    private ApprovalRequestPage pressApproveButton() {
        waitOnButton(approveButton);
        click(approveButton);
        return this;
    }

    private ApprovalRequestPage pressNextButton() {
        waitOnButton(nextButton);
        click(nextButton);
        return this;
    }


    private ApprovalRequestPage pressSubmitForApprovalButton() {
        waitOnButton(submitForApprovalButton);
        click(submitForApprovalButton);
        return this;
    }


    public void startApprovalForDefaultUsers(){
        pressNextButton();
        pressSubmitForApprovalButton();
    }

    public ApprovalRequestPage approveQuotation(String message){
        pressApproveButton();
        insertComment(message);
        pressSendButton();
        return this;
    }



}