package pageObjects.configurators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesPage;

/**
 * Created by PLJAHAS on 2016-12-22.
 */
public class MotConfPage extends ConfiguratorsPage {
    public MotConfPage(WebDriver driver) {
        super(driver);
    }

    private By searchByProductNumberField = By.id("TextBox_ProductNumber");
    private By searchButton = By.id("Button_Search");
    private By addToCardButton = By.name("btnCart");
    private final String FRAME_ID = "innerframe";
    private final String CHECK_AVAILABILITY_LINK = "Availability";
    private final String VC_FIELD_ID = "tbFld";

    public void addProductFromMotConf(String productId) {
        initMotConf();
        insertProductNumberInSearchField(productId.split("\\+")[0]);
        pressSearchButton();
        selectSearchedProduct(productId.split("\\+")[0]);
        initMotConf();
        addVariantCodes(productId);
        pressAddToCardButton();
        closeMotConf();
    }


    private void initMotConf() {
        driver.switchTo().parentFrame();
        driver.switchTo().frame(findElement(By.id(FRAME_ID)));
    }

    private void closeMotConf() {
        driver.switchTo().parentFrame();
    }

    private void insertProductNumberInSearchField(String productId) {
        waitOnElementToBeVisible(searchByProductNumberField);
        sendKeys(searchByProductNumberField, productId);
    }

    private void pressSearchButton() {
        waitOnButton(searchButton);
        click(searchButton);
    }

    private void selectSearchedProduct(String productId) {
        click(By.linkText(productId));
        waitOnPresenceOfElement(By.partialLinkText(CHECK_AVAILABILITY_LINK));
        click(By.partialLinkText(CHECK_AVAILABILITY_LINK));
    }

    private void addVariantCodes(String productId) {
        String[] variantCodes = productId.split("\\+");
        if (variantCodes.length > 1) {
            for (int i = 1; i < variantCodes.length; i++) {
                waitOnPresenceOfElement(By.id(VC_FIELD_ID + (i - 1)));
                setAttribute(By.id(VC_FIELD_ID + (i - 1)), "value", variantCodes[i]);
            }
        }
    }

    private void pressAddToCardButton() {
        waitOnButton(addToCardButton);
        click(addToCardButton);
    }


}
