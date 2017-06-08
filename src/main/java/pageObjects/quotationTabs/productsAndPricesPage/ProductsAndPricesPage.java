package pageObjects.quotationTabs.productsAndPricesPage;


import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pageObjects.configurators.LvDrivePage;
import pageObjects.configurators.MotConfPage;
import pageObjects.quotationTabs.QuotationNavigationBar;

public class ProductsAndPricesPage extends QuotationNavigationBar {

    public ProductsAndPricesPage(WebDriver driver) {
        super(driver);
    }



    private By addToQuotationButton = By.xpath("//button[contains(text(), 'Add to quotation')]");
    private By quickSearchField = By.id("ProductAutoComplete");
    private By searchInQuickSearchButton = By.cssSelector(".icon-search");
    private By allTab = By.xpath("//div[@class = 'abbMenu']//div[contains(text(), 'All')]");
    private By priceDetailsTab = By.xpath("//div[@class = 'abbMenu']//div[contains(text(), 'Price details')]");
    private By technicalDetailsTab = By.xpath("//div[@class = 'abbMenu']//div[contains(text(), 'Technical')]");
    private By termsAndConditionsTab = By.xpath("//div[@class = 'abbMenu']//div[contains(text(), 'Terms')]");
    private By fcmTab = By.xpath("//div[@class = 'abbMenu']//div[contains(text(), 'FCM')]");
    private By addProductButton = By.xpath("//button[contains(text(), 'Add product')]");

    public boolean isAddProductButtonDisplayed() {
        setTimeout(driver, 1);
        boolean elementStatus = false;
        int counter = 0;
        while (!elementStatus && counter<30){
            try {
                driver.findElement(addProductButton);
                elementStatus = true;
            } catch (NoSuchElementException | ElementNotFoundException e) {
                counter++;
            }
         }
        setTimeout(driver, 30);

        return elementStatus;
    }

    public void switchToAllTab() {
        waitOnPresenceOfElement(allTab);
        click(allTab);
    }

    public void switchToPriceDetailsTab() {
        waitOnPresenceOfElement(priceDetailsTab);
        click(priceDetailsTab);
    }

    public void switchToTechnicalDetailsTab() {
        waitOnPresenceOfElement(technicalDetailsTab);
        click(technicalDetailsTab);
    }

    public void switchToTermsAndConditionsTab() {
        waitOnPresenceOfElement(termsAndConditionsTab);
        click(termsAndConditionsTab);
    }

    public void switchToFcmTab() {
        waitOnPresenceOfElement(fcmTab);
        click(fcmTab);
    }

    public void addProductFromLvDrive(String productId) {
        pressAddProductButton();
        moveToElement(By.xpath("//a[contains(text(),'Add product from configurator')]"));
        selectElementFromDropdownListByHtmlElement("LV DRIVE", "a");
        new LvDrivePage(driver).addProductFromLvDrive(productId);
    }

    public void addProductFromMotConf(String productId) {
        pressAddProductButton();
        moveToElement(By.xpath("//a[contains(text(),'Add product from configurator')]"));
        selectElementFromDropdownListByHtmlElement("Search MotConf product", "a");
        new MotConfPage(driver).addProductFromMotConf(productId);
    }

    public void addLocalProduct(String productId) {
        pressAddProductButton();
        selectElementFromDropdownListByHtmlElement("Add local product", "a");
    }


    private void pressAddProductButton() {
        waitOnButton(addProductButton);
        click(addProductButton);
    }

    public void pressAddToQuotationButton() {
        waitOnButton(addToQuotationButton);
        click(addToQuotationButton);
     }


    /*
    public ProductsAndPricesPage addProductFromQuickSearch(String productForSearch) {
        waitForPageLoad(driver);
        waitOnPresenceOfElement(quickSearchField);
        clear(quickSearchField);
        sendKeys(quickSearchField, productForSearch);
        new Actions(driver).sendKeys(findElement(quickSearchField),Keys.ENTER).build().perform();
        pressAddToQuotationButton();
        return this;
    }
    */

    /*
    public <T extends ProductsAndPricesPage> T addProductFromQuickSearch(String productForSearch, Class<T> clazz) {
        addProductFromQuickSearch(productForSearch);
        return PageFactory.initElements(driver, clazz);
    }
    */
}

