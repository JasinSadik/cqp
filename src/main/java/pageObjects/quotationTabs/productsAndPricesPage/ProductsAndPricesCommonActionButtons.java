package pageObjects.quotationTabs.productsAndPricesPage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pageObjects.configurators.LvDrivePage;
import pageObjects.configurators.MotConfPage;
import pageObjects.quotationTabs.QuotationNavigationBar;

public class ProductsAndPricesCommonActionButtons extends QuotationNavigationBar {

    public ProductsAndPricesCommonActionButtons(WebDriver driver) {
        super(driver);
    }

    Actions actions = new Actions(driver);

    private By addToQuotationButton = By.xpath("//button[contains(text(), 'Add to quotation')]");
    private By quickSearchField = By.id("ProductAutoComplete");
    private By searchInQuickSearchButton = By.cssSelector(".icon-search");
    private By allTab = By.xpath("//div[@class = 'abbMenu']//div[contains(text(), 'All')]");
    private By priceDetailsTab = By.xpath("//div[@class = 'abbMenu']//div[contains(text(), 'Price details')]");
    private By technicalDetailsTab = By.xpath("//div[@class = 'abbMenu']//div[contains(text(), 'Technical')]");
    private By termsAndConditionsTab = By.xpath("//div[@class = 'abbMenu']//div[contains(text(), 'Terms')]");
    private By fcmTab = By.xpath("//div[@class = 'abbMenu']//div[contains(text(), 'FCM')]");
    private By addProductButton = By.xpath("//button[contains(text(), 'Add product')]");

    public ProductsAndPricesCommonActionButtons switchToAllTab() {
        waitOnPresenceOfElement(allTab);
        click(allTab);
        return this;
    }

    public ProductsAndPricesCommonActionButtons switchToPriceDetailsTab() {
        waitOnPresenceOfElement(priceDetailsTab);
        click(priceDetailsTab);
        return this;
    }

    public ProductsAndPricesCommonActionButtons switchToTechnicalDetailsTab() {
        waitOnPresenceOfElement(technicalDetailsTab);
        click(technicalDetailsTab);
        return this;
    }

    public ProductsAndPricesCommonActionButtons switchToTermsAndConditionsTab() {
        waitOnPresenceOfElement(termsAndConditionsTab);
        click(termsAndConditionsTab);
        return this;
    }

    public ProductsAndPricesCommonActionButtons switchToFcmTab() {
        waitOnPresenceOfElement(fcmTab);
        click(fcmTab);
        return this;
    }

    public LvDrivePage addProductFromLvDrive(String productId) {
        pressAddProductButton();
        actions.moveToElement(findElement(By.xpath("//a[contains(text(),'Add product from configurator')]"))).build().perform();
        selectElementFromDropdownListByHtmlElement("LV DRIVE", "a");
        new LvDrivePage(driver).addProductFromLvDrive(productId);
        return new LvDrivePage(driver);
    }

    public ProductsAndPricesCommonActionButtons addProductFromMotConf(String productId) {
        pressAddProductButton();
        actions.moveToElement(findElement(By.xpath("//a[contains(text(),'Add product from configurator')]"))).build().perform();
        selectElementFromDropdownListByHtmlElement("Search MotConf product", "a");
        new MotConfPage(driver).addProductFromMotConf(productId);
        return this;
    }

    public ProductsAndPricesCommonActionButtons addLocalProduct(String productId) {
        pressAddProductButton();
        selectElementFromDropdownListByHtmlElement("Add local product", "a");
        return this;
    }


    private ProductsAndPricesCommonActionButtons pressAddProductButton() {
        waitOnButton(addProductButton);
        click(addProductButton);
        return this;
    }

    /*
    public ProductsAndPricesCommonActionButtons addProductFromQuickSearch(String productForSearch) {
        waitForPageLoad(driver);
        waitOnPresenceOfElement(quickSearchField);
        clear(quickSearchField);
        sendKeys(quickSearchField, productForSearch);
        new Actions(driver).sendKeys(findElement(quickSearchField),Keys.ENTER).build().perform();
        pressAddToQuotationButton();
        return this;
    }
*/
    public ProductsAndPricesCommonActionButtons pressAddToQuotationButton() {
        waitOnButton(addToQuotationButton);
        click(addToQuotationButton);
        return this;
    }
/*
    public <T extends ProductsAndPricesCommonActionButtons> T addProductFromQuickSearch(String productForSearch, Class<T> clazz) {
        addProductFromQuickSearch(productForSearch);
        return PageFactory.initElements(driver, clazz);
    }
    */
}

