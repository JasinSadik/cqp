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

    public MotConfPage addProductFromMotConf(String productId) {
        initMotConf();
        insertProductNumberInSearchField(productId.split("\\+")[0]);
        pressSearchButton();
        selectSearchedProduct(productId.split("\\+")[0]);
        initMotConf();
        addVariantCodes(productId);
        pressAddToCardButton();
        closeMotConf();
        return this;
    }


    private MotConfPage initMotConf() {
        driver.switchTo().parentFrame();
        driver.switchTo().frame(findElement(By.id(FRAME_ID)));
        return this;
    }

    private MotConfPage closeMotConf() {
        driver.switchTo().parentFrame();
        return this;
    }

    private MotConfPage insertProductNumberInSearchField(String productId) {
        waitOnElementToBeVisible(searchByProductNumberField);
        sendKeys(searchByProductNumberField, productId);
        return this;
    }

    private MotConfPage pressSearchButton() {
        waitOnButton(searchButton);
        click(searchButton);
        return this;
    }

    private MotConfPage selectSearchedProduct(String productId) {
        click(By.linkText(productId));
        waitOnPresenceOfElement(By.partialLinkText(CHECK_AVAILABILITY_LINK));
        click(By.partialLinkText(CHECK_AVAILABILITY_LINK));
        return this;
    }

    private MotConfPage addVariantCodes(String productId) {
        String[] variantCodes = productId.split("\\+");
        if (variantCodes.length > 1) {
            for (int i = 1; i < variantCodes.length; i++) {
                waitOnPresenceOfElement(By.id(VC_FIELD_ID + (i - 1)));
                setAttribute(By.id(VC_FIELD_ID + (i - 1)), "value", variantCodes[i]);
            }
        }
        return this;
    }

    private ProductsAndPricesPage pressAddToCardButton() {
        waitOnButton(addToCardButton);
        click(addToCardButton);
        return new ProductsAndPricesPage(driver);
    }


}
