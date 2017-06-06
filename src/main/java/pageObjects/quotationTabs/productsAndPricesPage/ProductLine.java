package pageObjects.quotationTabs.productsAndPricesPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestCreationPage;

/**
 * Created by PLJAHAS on 2016-12-20.
 */
public class ProductLine extends ProductsAndPricesPage {
    public ProductLine(WebDriver driver) {
        super(driver);
    }

    private int positionLine = 1;

    private void setPositionLine(int desiredLine) {
        positionLine = desiredLine;
        editApplicationButton = By.xpath("//tbody[@class = 'product']["+positionLine+"]//button[text()='Edit application']");
        setEditApplicationExpandButton = By.xpath("//tbody[@class = 'product']["+positionLine+"]//span[@role='button']");
        actionMenuDropdownList  =  By.xpath("//tbody[@class = 'product']["+positionLine+"]//td[@class = 'actions']//li[contains (@class, 'additionalActions')]/a");
        supportRequestListElement = By.xpath("//tbody[@class = 'product']["+positionLine+"]//td[@class = 'actions']//li[contains (@class, 'support')]");
    }

    private By editApplicationButton = By.xpath("//tbody[@class = 'product']["+positionLine+"]//button[text()='Edit application']");
    private By setEditApplicationExpandButton = By.xpath("//tbody[@class = 'product']["+positionLine+"]//span[@role='button']");
    private By actionMenuDropdownList  =  By.xpath("//tbody[@class = 'product']["+positionLine+"]//td[@class = 'actions']//li[contains (@class, 'additionalActions')]/a");
    private By supportRequestListElement = By.xpath("//tbody[@class = 'product']["+positionLine+"]//td[@class = 'actions']//li[contains (@class, 'support')]");

    //*[@id="quoteProducts"]/tbody[1]/tr/td[6]/ul/li[1]/a

    private ProductLine pressEditApplicationButton() {
        waitOnButton(editApplicationButton);
        click(editApplicationButton);
        return this;
    }

    private ProductLine pressEditApplicationExpandButton() {
        waitOnButton(setEditApplicationExpandButton);
        click(setEditApplicationExpandButton);
        return this;
    }

    public ProductLine setApplication(int desiredLine, String value){
        setPositionLine(desiredLine);
        pressEditApplicationButton();
        pressEditApplicationExpandButton();
        selectElementFromDropdownListByHtmlElement(value);
        return this;
    }

    public SupportRequestCreationPage createSupportRequest(int desiredLine, String workqueue){
        setPositionLine(desiredLine);
        pressActionMenuDropdownList();
        actions.moveToElement(findElement(supportRequestListElement)).build().perform();
        selectElementFromDropdownListByHtmlElement(workqueue, "a");
        return new SupportRequestCreationPage(driver);
    }

    public ProductLine pressActionMenuDropdownList(){
        waitOnElementToBeClickable(actionMenuDropdownList);
        click(actionMenuDropdownList);
        return this;
    }

}
