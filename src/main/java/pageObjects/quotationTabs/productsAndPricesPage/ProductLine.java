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
        editApplicationButton = By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//b[contains(text(), 'Edit application*')]");
        setEditApplicationExpandButton = By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//span[@role='button']");
        actionMenuDropdownList  =  By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//div[@id='productLineCommonOperations']/a");
    }

    private By editApplicationButton = By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//b[contains(text(), 'Edit application*')]");
    private By setEditApplicationExpandButton = By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//span[@role='button']");
    private By actionMenuDropdownList  =  By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//div[@id='productLineCommonOperations']/a");

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
        actions.moveToElement(findElement(By.xpath("//tr[@class = 'lineItem product']["+positionLine+"]//span[contains(text(),'Support')]"))).build().perform();
        selectElementFromDropdownListByHtmlElement(workqueue, "a");
    }

    public void pressActionMenuDropdownList(){
        waitOnElementToBeClickable(actionMenuDropdownList);
        click(actionMenuDropdownList);
    }

}
