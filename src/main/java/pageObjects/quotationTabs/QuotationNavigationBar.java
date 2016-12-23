package pageObjects.quotationTabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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

    private By quotationClassificationTab = By.xpath("//*[@id='quoteToolbar']//span[contains(text(),'Quotation classification')]");
    private By productAndPricesTab = By.xpath("//*[@id='productsButtonHeader']/span");
    private By supportRequestsTab = By.xpath("//*[@id='quoteToolbar']//span[contains(text(),'Support requests')]");
    private By attachmentsTab = By.xpath("//*[@id='quoteToolbar']//span[contains(text(),'Attachments')]");
    private By fullCostAndFinalizationTab = By.xpath("//*[@id='quoteToolbar']//span[contains(text(),'Full cost and finalization')]");
    private By approvalRequestsTab = By.xpath("//*[@id='quoteToolbar']//span[contains(text(),'Approval requests')]");


    public QuotationClassificationCommonActionButtonsSection goToQuotationClassificationTab() {
        waitForPageLoad(driver);
        waitOnButton(quotationClassificationTab);
        click(quotationClassificationTab);
        return new QuotationClassificationCommonActionButtonsSection(driver);
    }


    public <T extends ProductsAndPricesCommonActionButtons> T goToProductAndPriceTab(Class<T> clazz) {
        waitForPageLoad(driver);
        click(productAndPricesTab);
        return PageFactory.initElements(driver, clazz);
    }

}
