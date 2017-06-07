package pageObjects.configurators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesPage;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class LvDrivePage extends ConfiguratorsPage {
    public LvDrivePage(WebDriver driver) {
        super(driver);
    }



    private By uncodeProductField = By.name("TypeCodeToParse");
    private By uncodeProductButton = By.xpath("//input[@name='TypeCodeToParse']/../../../../..//input[@name='submit']");
    private By summaryButton = By.xpath("//table[5]//td[6]//input[@name='submit']");
    private By priceCheckButton = By.xpath("//table[3]//input[7]");
    private By addToQuotationButton = By.xpath("//input[@name='submit' and @value='Add to quotation']");
    private final String FRAME_ID = "innerframe";

    public ProductsAndPricesPage addProductFromLvDrive(String productId) {
        waitForPageLoad(driver);
        initLvDrive();
        insertProductIdToUncode(productId);
        pressUncodeButton();
        pressSummaryButton();
        pressPriceCheckButton();
        pressAddToQuotationButton();
        closeLvDrive();
        return new ProductsAndPricesPage(driver);
    }

    private LvDrivePage initLvDrive() {
        driver.switchTo().frame(findElement(By.id(FRAME_ID)));
        return this;
    }

    private LvDrivePage closeLvDrive() {
        driver.switchTo().parentFrame();
        return this;
    }

    private void insertProductIdToUncode(String productId) {
        waitForPageLoad(driver);
        waitOnPresenceOfElement(uncodeProductField);
        sendKeys(uncodeProductField, productId);
    }

    private void pressUncodeButton() {
        waitOnButton(uncodeProductButton);
        click(uncodeProductButton);
    }

    private void pressSummaryButton() {
        waitOnButton(summaryButton);
        click(summaryButton);
    }

    private void pressPriceCheckButton() {
        waitOnButton(priceCheckButton);
        click(priceCheckButton);
    }

    private void pressAddToQuotationButton() {
        waitOnButton(addToQuotationButton);
        click(addToQuotationButton);
    }

}
