package pageObjects.quotationTabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.quotationTabs.fullCostAndFinalizationPage.DocumentGenerationSection;
import pageObjects.mainPages.TopMenu;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesCommonActionButtons;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationCommonActionButtonsSection;

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

    public QuotationClassificationCommonActionButtonsSection goToQuotationClassificationTab() {
        waitForPageLoad(driver);
        waitOnElementToBeClickable(quotationClassificationTab);
        while (!findElement(quotationClassificationTab).getAttribute("class").contains("hover")) {
            click(quotationClassificationTab);
        }
        return new QuotationClassificationCommonActionButtonsSection(driver);
    }


    public <T extends ProductsAndPricesCommonActionButtons> T goToProductAndPriceTab(Class<T> clazz) {
        waitForPageLoad(driver);
        waitOnElementToBeClickable(productAndPricesTab);
        while (!findElement(productAndPricesTab).getAttribute("class").contains("hover")) {
            click(productAndPricesTab);
        }
        return PageFactory.initElements(driver, clazz);
    }

    public DocumentGenerationSection goToFullCostAndFinalizationTab() {
        waitForPageLoad(driver);
        waitOnElementToBeClickable(fullCostAndFinalizationTab);
        while (!findElement(fullCostAndFinalizationTab).getAttribute("class").contains("hover")) {
            click(fullCostAndFinalizationTab);
        }
        return new DocumentGenerationSection(driver);
    }

    public String getQuotationNumber() {
        return getText(quotationNumberLabel);
    }

    public ApprovalRequestPage goToApprovalTab() {
        waitForPageLoad(driver);
        waitOnElementToBeClickable(approvalRequestsTab);
        while (!findElement(approvalRequestsTab).getAttribute("class").contains("hover")) {
            click(approvalRequestsTab);
        }
        return new ApprovalRequestPage(driver);
    }

    public CloseQuotationPage goToCloseQuotationTab() {
        waitForPageLoad(driver);
        waitOnElementToBeClickable(closeQuotationTab);
        while (!findElement(closeQuotationTab).getAttribute("class").contains("hover")) {
            click(closeQuotationTab);
        }
        return new CloseQuotationPage(driver);
    }
}
