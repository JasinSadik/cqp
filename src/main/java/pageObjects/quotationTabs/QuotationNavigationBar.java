package pageObjects.quotationTabs;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.quotationTabs.approvalRequestPage.ApprovalRequestPage;
import pageObjects.quotationTabs.fullCostAndFinalizationPage.DocumentGenerationSection;
import pageObjects.mainPages.TopMenu;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesPage;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationPage;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class QuotationNavigationBar extends TopMenu {
    public QuotationNavigationBar(WebDriver driver) {
        super(driver);
    }

    private By quotationClassificationTab = By.xpath("//*[@id='quoteToolbar']//span[contains(text(),'Quotation classification')]/..");
    private By productAndPricesTab = By.xpath("//*[@id='productsButtonHeader']");
    private By supportRequestsTab = By.xpath("//*[@id='quoteToolbar']//span[contains(text(),'Support requests')]/..");
    private By attachmentsTab = By.xpath("//*[@id='quoteToolbar']//span[contains(text(),'Attachments')]/..");
    private By fullCostAndFinalizationTab = By.xpath("//span[contains(text(),'Full cost and finalization')]/..");
    private By approvalRequestsTab = By.xpath("//*[@id='teaserTiles']//span[contains(text(),'Approval requests')]/..");
    private By closeQuotationTab = By.xpath("//*[@id='teaserTiles']//span[contains(text(),'Close quotation')]/..");
    private By quotationNumberLabel = By.xpath("//div[@id='pageHeaderInfo']//span[contains(text(),'CQ')]");

    public void goToQuotationClassificationTab() {
        openTab(quotationClassificationTab);
    }


    public void goToProductAndPriceTab() {
        openTab(productAndPricesTab);
    }

    public void goToFullCostAndFinalizationTab() {
        openTab(fullCostAndFinalizationTab);
    }

    public String getQuotationNumber() {
        return getText(quotationNumberLabel);
    }

    public void goToApprovalTab() {
        openTab(approvalRequestsTab);
    }

    public void goToCloseQuotationTab() {
        openTab(closeQuotationTab);
    }


    private void openTab(By by) {
        waitForPageLoad(driver);
        waitOnElementToBeClickable(by);
        int attempt = 0;
        boolean isElelemetDisplayed = false;
        while (!isElelemetDisplayed && attempt < 30) {
            try {
                while (!findElement(by).getAttribute("class").contains("hover")) {
                    click(by);
                    isElelemetDisplayed = true;
                }
            } catch (StaleElementReferenceException | ElementNotFoundException e) {
                attempt++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
