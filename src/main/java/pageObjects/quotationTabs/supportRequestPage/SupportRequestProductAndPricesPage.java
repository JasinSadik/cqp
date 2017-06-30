package pageObjects.quotationTabs.supportRequestPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PLLIKOS on 2017-06-13.
 */
public class SupportRequestProductAndPricesPage extends SupportRequestPage {
    public SupportRequestProductAndPricesPage(WebDriver driver) {
        super(driver);
    }

    private By backToSupportRequestButton = By.xpath("//*[@id='selectedProductPageContent']//button[text() = 'Back to support request']");
    private By applyDiscountToWholeRequestButton = By.xpath("//*[@id='productsPrices']//button[text() = 'Apply']");
    private By applyDiscountToWholeRequestField = By.xpath("//*[@id='productsPrices']//button[text() = 'Apply']/..//input[contains (@style, 'display: inline-block')]");
    private By productTpDiscountField = By.xpath("//tr[@class = 'lineItem product'][1]//span[contains (@class, 'transferPriceDiscountValue')]");

    private int positionLine = 1;

    private void setPositionLine(int desiredLine) {
        positionLine = desiredLine;
        productTpDiscountField = By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//span[contains (@class, 'transferPriceDiscountValue')]");
    }

    public void insertDiscountOnWholeRequest(String discountOnWholeRequest) {
        waitOnPresenceOfElement(applyDiscountToWholeRequestField);
        clear(applyDiscountToWholeRequestField);
        sendKeys(applyDiscountToWholeRequestField, discountOnWholeRequest);
    }

    public void pressApplyDicountToWholeRequestButton() {
        waitOnButton(applyDiscountToWholeRequestButton);
        click(applyDiscountToWholeRequestButton);
    }

    public void pressBackToSupportRequestButton() {
        waitOnButton(backToSupportRequestButton);
        click(backToSupportRequestButton);
    }

    public String getProductTpDiscountField(int positionLine) {
        setPositionLine(positionLine);
        waitOnPresenceOfElement(productTpDiscountField);
        return getText(productTpDiscountField);
    }
}

