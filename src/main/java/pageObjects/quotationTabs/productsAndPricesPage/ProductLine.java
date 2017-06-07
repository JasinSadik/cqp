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

    private void pressEditApplicationButton() {
        waitOnButton(editApplicationButton);
        click(editApplicationButton);
     }

    private void pressEditApplicationExpandButton() {
        waitOnButton(setEditApplicationExpandButton);
        click(setEditApplicationExpandButton);
    }

    public void setApplication(int desiredLine, String value){
        setPositionLine(desiredLine);
        pressEditApplicationButton();
        pressEditApplicationExpandButton();
        selectElementFromDropdownListByHtmlElement(value);
    }

    public void createSupportRequest(int desiredLine, String workqueue){
        setPositionLine(desiredLine);
        pressActionMenuDropdownList();
        moveToElement(supportRequestListElement);
        selectElementFromDropdownListByHtmlElement(workqueue, "a");
    }

    public void pressActionMenuDropdownList(){
        waitOnElementToBeClickable(actionMenuDropdownList);
        click(actionMenuDropdownList);
    }

}
