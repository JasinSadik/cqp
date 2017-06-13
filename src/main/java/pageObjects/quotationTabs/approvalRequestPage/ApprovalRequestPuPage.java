package pageObjects.quotationTabs.approvalRequestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLLIKOS on 2017-06-13.
 */
public class ApprovalRequestPuPage extends QuotationNavigationBar {
    public ApprovalRequestPuPage(WebDriver driver) {
        super(driver);
    }

    private By nextButton = By.xpath("//button[text() = 'Next']");
    private By submitForApprovalButton = By.xpath("//button[text() = 'Submit for approval']");
    private By approveButton = By.xpath("//button[text() = 'Approve']");
    private By sendButton = By.xpath("//button[text() = 'Send']");
    private By commentForApprovalField = By.xpath("//span[text() = 'Add a comment (*)']/../div/div");
    private By sendApproveByPuButton = By.xpath("//*[@id='editApproval']//button[text() = 'Send']");

    private void insertComment(String message){
        waitOnPresenceOfElement(commentForApprovalField);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeys(commentForApprovalField, message);
    }
    public void pressSendButton() {
        waitOnButton(sendButton);
        click(sendButton);
    }

    public void pressApproveButton() {
        waitOnButton(approveButton);
        click(approveButton);
    }

    public void pressNextButton() {
        waitOnButton(nextButton);
        click(nextButton);
    }


    public void pressSubmitForApprovalButton() {
        waitOnButton(submitForApprovalButton);
        click(submitForApprovalButton);
    }


    public void startApprovalForDefaultUsers(){
        pressNextButton();
        pressSubmitForApprovalButton();
    }


    public void pressSendByPuApproverButton (){
        waitOnButton(sendApproveByPuButton);
        click(sendApproveByPuButton);

    }
    public void approveQuotation(String message){
        pressApproveButton();
        insertComment(message);
    }
}



