package pageObjects.quotationTabs.productsAndPricesPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by PLJAHAS on 2016-12-20.
 */
public class ProductLine extends ProductsAndPricesCommonActionButtons {
    public ProductLine(WebDriver driver) {
        super(driver);
    }

    private int positionLine = 1;

    private void setPositionLine(int desiredLine) {
        positionLine = desiredLine;
        editApplicationButton = By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//b[contains(text(), 'Edit application*')]");
        getEditApplicationExpandButton = By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//span[@role='button']");
    }

    private By editApplicationButton = By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//b[contains(text(), 'Edit application*')]");
    private By getEditApplicationExpandButton = By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//span[@role='button']");

    private ProductLine pressEditApplicationButton() {
        waitOnButton(editApplicationButton);
        click(editApplicationButton);
        return this;
    }

    private ProductLine pressEditApplicationExpandButton() {
        waitOnButton(getEditApplicationExpandButton);
        click(getEditApplicationExpandButton);
        return this;
    }

    public ProductLine setApplication(int desiredLine, String value){
        setPositionLine(desiredLine);
        System.out.println(editApplicationButton.toString());
        System.out.println(getEditApplicationExpandButton.toString());
        pressEditApplicationButton();
        pressEditApplicationExpandButton();
        selectElementFromDropdownListSimple(value);
        return this;
    }

}
