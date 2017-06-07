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

    private void insertComment(String message){
        waitOnPresenceOfElement(commentForApprovalField);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(commentForApprovalField, message);
    }


    private void pressSendButton() {
        waitOnButton(sendButton);
        click(sendButton);
    }

    private void pressApproveButton() {
        waitOnButton(approveButton);
        click(approveButton);
    }

    private void pressNextButton() {
        waitOnButton(nextButton);
        click(nextButton);
    }


    private void pressSubmitForApprovalButton() {
        waitOnButton(submitForApprovalButton);
        click(submitForApprovalButton);
    }


    public void startApprovalForDefaultUsers(){
        pressNextButton();
        pressSubmitForApprovalButton();
    }

    public void approveQuotation(String message){
        pressApproveButton();
        insertComment(message);
        pressSendButton();
    }



}