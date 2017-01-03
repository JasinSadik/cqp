package pageObjects.quotationTabs.fullCostAndFinalizationPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.ApprovalRequestPage;
import pageObjects.quotationTabs.QuotationNavigationBar;

/**
 * Created by PLJAHAS on 2016-12-23.
 */
public class DocumentGenerationSection extends QuotationNavigationBar{
    public DocumentGenerationSection(WebDriver driver) {
        super(driver);
    }

    private By openButton = By.xpath("//div[@id='generateDocument']//button[contains(text(),'Open')]");
    private By startApprovalHyperlink = By.xpath("//a[text() = 'Start approval']");
    private By createNewRevisionButton = By.xpath("//button[text()='Create new revision']");
    private By issueDocumentButton = By.xpath("//button[text()='Issue Document ']");

    private void pressOpenSectionButton(){
        if(!findElement(openButton).getAttribute("style").contains("display: none")){
            waitOnButton(openButton);
            click(openButton);
        }
    }


    public void generateAndIssueDocumentManually(){
        pressOpenSectionButton();
        pressCreateNewRevisionButton();
        issueDocumentManually();
    }

    private void issueDocumentManually() {
        waitOnButton(issueDocumentButton);
        click(issueDocumentButton);
        selectElementFromDropdownListByHtmlElement("manually" , "a");
    }

    private void pressCreateNewRevisionButton() {
        waitOnButton(createNewRevisionButton);
        click(createNewRevisionButton);
    }


    public ApprovalRequestPage pressStartApprovalHyperlink(){
        pressOpenSectionButton();
        waitOnElementToBeClickable(startApprovalHyperlink);
        click(startApprovalHyperlink);
        return new ApprovalRequestPage(driver);
    }




}
