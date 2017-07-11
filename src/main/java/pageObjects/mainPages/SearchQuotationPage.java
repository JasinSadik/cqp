package pageObjects.mainPages;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import common.CommonMethods;
import org.apache.commons.lang.ObjectUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLElement;
import pageObjects.popUpWindows.ChangeLsuPopUp;

/**
 * Created by PLADSAM on 2017-06-09.
 */
public class SearchQuotationPage extends LsuDashboard {
    public SearchQuotationPage(WebDriver driver) {
        super(driver);
    }



    //fields to fill in
    private By referenceNumberField = By.xpath("//*[@id='SearchFields']/div[1]/div[2]/input");
    private By orderNumberField = By.xpath("//*[@id='SixthElement3Group']/input");
    private By projectNameField = By.xpath("//*[@id='inputIcon']");
    private By outdoorSalesPersonField = By.xpath("//*[@id='FourthElement3Group']/input");
    private By quotationOwnerField = By.xpath("//*[@id='SearchFields']/div[7]/div[1]/input");
    private By additionalOwnerField = By.xpath("//*[@id='SearchFields']/div[8]/div[1]/input");
    private By fromDateField = By.xpath("//*[@id='dateFrom']");
    private By toDateField = By.xpath("//*[@id='dateTo']");
    private By customerNameField = By.xpath("//*[@id='SearchFields']/div[1]/div[3]/input");
    private By customerQuotationReferenceField = By.xpath("//*[@id='SearchFields']/div[2]/div[2]/input");
    private By customerCityField = By.xpath("//*[@id='ContainerSecondGruop']/div[2]/input");
    private By customerStateField = By.xpath("//*[@id='ContainerThirdGruop']/div[2]/input");
    private By customerZipCodeField = By.xpath("//*[@id='inputIcon']");
    private By customerRegionField = By.xpath("//*[@id='ContainerSixthGruop']/div[2]/input");
    private By productIdField = By.xpath("//*[@id='FourthElement7Group']/input");
    private By productDescriptionField = By.xpath("//*[@id='SixthElement2Group']/input");
    private By opportunityNumberField = By.xpath("//*[@id='SearchFields']/div[10]/div[2]/input");

    //dropdown lists
    private By expandStatusDropdownList = By.xpath("//*[@id='SearchFields']/div[1]/div[1]/ul/li/span/span/span/span");
    private By expandScreeningDecisionDropdownList = By.xpath("//*[@id='ThirdElement3Group']/span[2]/span/span[2]/span");
    private By expandCategoryDropdownList = By.xpath("//*[@id='SecondElement3Group']/span[2]/span/span[2]/span");
    private By expandLocalSalesUnitDropdownList = By.xpath("//*[@id='SearchFields']/div[11]/div/span[2]/span/span/span");
    private By expandTeamsDropdownList = By.xpath("//*[@id='SearchFields']/div[7]/div[2]/span[2]/span/span/span");

    //checkboxes
    private By favoritesCheckbox = By.xpath("//*[@id='FourthElement2Group']/input");
    private By searchOnlyValidQuotationsCheckbox = By.xpath("//*[@id='FourthElement1Group']/input");
    private By searchInOthersLSUCheckbox = By.xpath("//*[@id='FourthElement4Group']/div[3]/input");
    private By holdingCheckbox = By.xpath("//*[@id='regionsTreeview_tv_active']/div/span[1]/input");

    //buttons
    private By searchButton = By.xpath("//*[@id='SearchQuotaPage']/div[4]/button[1]");

    //fieldsAfterSearch
    private By referenceNumberSearchField = By.xpath("//*[@id='grid']/div[2]/table/tbody/tr/td[2]");
    private By numberOfHitsOnePage = By.xpath("//div[contains(@data-bind, 'visible : items().length > 0')]//span[contains(text(), 'Total number of hits')]");
    //private By numberOfHitsMoreThanOnePage = By.xpath("//div[contains(@data-bind, 'visible : kendoGridDisplay() == true && isResultPageVisible && (hasPreviousPage() || hasNextPage())')]//span[contains(text(), 'Total number of hits')]");
    private By numberOfHitsMoreThanOnePage = By.xpath("//*[@id='SearchQuotaPage']/div[9]/span");
    private By numberOfHits = By.xpath("//div[contains(@data-bind,'visible' ) and not(contains(@style,'display: none'))]//span[contains(text(), 'Total number of hits')]");//dobre

    private By isMoreThanOnePage = By.xpath("//*[@id='SearchQuotaPage']/div[9]/ul/li[9]/a");

//INSERTs
    public void insertReferenceNumber(String referenceNumber) {
        waitOnPresenceOfElement(referenceNumberField);
        clear(referenceNumberField);
        sendKeys(referenceNumberField, referenceNumber);
    }

    public void insertOrderNumber(String orderNumber) {
        waitOnPresenceOfElement(orderNumberField);
        clear(orderNumberField);
        sendKeys(orderNumberField, orderNumber);
    }

    public void insertProjectName(String projectName) {
        waitOnPresenceOfElement(projectNameField);
        clear(projectNameField);
        sendKeys(projectNameField, projectName);


    }

    public void insertOutdoorSalesPerson(String outdoorSalesPerson) {
        waitOnPresenceOfElement(outdoorSalesPersonField);
        clear(outdoorSalesPersonField);
        sendKeys(outdoorSalesPersonField, outdoorSalesPerson);

    }

    public void insertQuotationOwner(String quotationOwner) {

        waitOnPresenceOfElement(quotationOwnerField);
        clear(quotationOwnerField);
        sendKeys(quotationOwnerField, quotationOwner);
    }

    public void insertAdditionalOwner(String additionalOwner) {

        waitOnPresenceOfElement(additionalOwnerField);
        clear(additionalOwnerField);
        sendKeys(additionalOwnerField, additionalOwner);
    }

    public void insertFromDate(String fromDate) {

        waitOnPresenceOfElement(fromDateField);
        clear(fromDateField);
        sendKeys(fromDateField, fromDate);
    }

    public void insertToDate(String toDate) {

        waitOnPresenceOfElement(toDateField);
        clear(toDateField);
        sendKeys(toDateField, toDate);
    }

    public void insertCustomerName(String customerName) {
        waitOnPresenceOfElement(customerNameField);
        clear(customerNameField);
        sendKeys(customerNameField, customerName);

    }

    public void insertCustomerQuotationReference(String customerQuotationReference) {

        waitOnPresenceOfElement(customerQuotationReferenceField);
        clear(customerQuotationReferenceField);
        sendKeys(customerQuotationReferenceField, customerQuotationReference);
    }

    public void insertCustomerCity(String customerCity) {

        waitOnPresenceOfElement(customerCityField);
        clear(customerCityField);
        sendKeys(customerCityField, customerCity);
    }

    public void insertCustomerState(String customerState) {

        waitOnPresenceOfElement(customerStateField);
        clear(customerStateField);
        sendKeys(customerStateField, customerState);
    }

    public void insertCustomerZipCode(String customerZipCode) {

        waitOnPresenceOfElement(customerZipCodeField);
        clear(customerZipCodeField);
        sendKeys(customerZipCodeField, customerZipCode);
    }

    public void insertCustomerRegion(String customerRegion) {

        waitOnPresenceOfElement(customerRegionField);
        clear(customerRegionField);
        sendKeys(customerRegionField, customerRegion);
    }

    public void insertProductId(String productId) {

        waitOnPresenceOfElement(productIdField);
        clear(productIdField);
        sendKeys(productIdField, productId);
    }

    public void insertProductDescription(String productDescription) {

        waitOnPresenceOfElement(productDescriptionField);
        clear(productDescriptionField);
        sendKeys(productDescriptionField, productDescription);
    }

    public void insertOpportunityNumber(String opportunityNumber) {

        waitOnPresenceOfElement(opportunityNumberField);
        clear(opportunityNumberField);
        sendKeys(opportunityNumberField, opportunityNumber);
    }

    //CLEARs
    public void clearReferenceNumber() {
        waitForPageLoad(driver);
        clear(referenceNumberField);
    }
    public void clearOrderNumber() {
        waitForPageLoad(driver);
        clear(orderNumberField);
    }
    public void clearProjectName() {
        waitForPageLoad(driver);
        clear(projectNameField);
    }
    public void clearOutdoorSalesPerson() {
        waitForPageLoad(driver);
        clear(outdoorSalesPersonField);
    }
    public void clearQuotationOwner() {
        waitForPageLoad(driver);
        clear(quotationOwnerField);
    }
    public void clearAdditionalOwner() {
        waitForPageLoad(driver);
        clear(additionalOwnerField);
    }
    public void clearFromDate() {
        waitForPageLoad(driver);
        clear(fromDateField);
    }
    public void clearToDate() {
        waitForPageLoad(driver);
        clear(toDateField);
    }
    public void clearCustomerName() {
        waitForPageLoad(driver);
        clear(customerNameField);
    }
    public void clearCustomerQuotationReference() {
        waitForPageLoad(driver);
        clear(customerQuotationReferenceField);
    }
    public void clearCustomerCity() {
        waitForPageLoad(driver);
        clear(customerCityField);
    }
    public void clearCustomerState() {
        waitForPageLoad(driver);
        clear(customerStateField);
    }
    public void clearCustomerZipCode() {
        waitForPageLoad(driver);
        clear(customerZipCodeField);
    }
    public void clearCustomerRegion() {
        waitForPageLoad(driver);
        clear(customerRegionField);
    }
    public void clearProductId() {
        waitForPageLoad(driver);
        clear(productIdField);
    }
    public void clearProductDescription() {
        waitForPageLoad(driver);
        clear(productDescriptionField);
    }
    public void clearOpportunityNumber() {
        waitForPageLoad(driver);
        clear(opportunityNumberField);
    }

    //dropdown list SETs
    public void expandStatusdropdownList()
    {
        waitOnElementToBeClickable(expandStatusDropdownList);
        expandDrowdownList(expandStatusDropdownList);
    }
    public void setStatus(String status){
        waitOnButton(expandStatusDropdownList);
        expandStatusdropdownList();
        selectElementFromDropdownListByHtmlElement(status);
    }

    public void expandScreeningDecisiondropdownList()
    {
        waitOnElementToBeClickable(expandScreeningDecisionDropdownList);
        expandDrowdownList(expandScreeningDecisionDropdownList);
    }
    public void setScreeningDecision(String screeningDecision){
        waitOnButton(expandScreeningDecisionDropdownList);
        expandScreeningDecisiondropdownList();
        selectElementFromDropdownListByHtmlElement(screeningDecision);
    }

    public void expandCategoryDropdownList()
    {
        waitOnElementToBeClickable(expandCategoryDropdownList);
        expandDrowdownList(expandCategoryDropdownList);
    }
    public void setCategory(String category){
        waitOnButton(expandCategoryDropdownList);
        expandCategoryDropdownList();
        selectElementFromDropdownListByHtmlElement(category);
    }

    public void expandLocalSalesUnitDropdownList()
    {
        waitOnElementToBeClickable(expandLocalSalesUnitDropdownList);
        expandDrowdownList(expandLocalSalesUnitDropdownList);
    }
    public void setLocalSalesUnit(String localSalesUnit){
        waitOnButton(expandLocalSalesUnitDropdownList);
        expandLocalSalesUnitDropdownList();
        selectElementFromDropdownListByHtmlElement(localSalesUnit);
    }

    public void expandTeamsDropdownList()
    {
        waitOnElementToBeClickable(expandTeamsDropdownList);
        expandDrowdownList(expandTeamsDropdownList);
    }
    public void setTeams(String teams){
        waitOnButton(expandTeamsDropdownList);
        expandTeamsDropdownList();
        selectElementFromDropdownListByHtmlElement(teams);
    }

    //checkbox MARKs
    public void setFavoritesCheckbox(boolean expectedState)
    {
        setCheckboxState(favoritesCheckbox,expectedState);
    }
    public void setSearchOnlyValidQuotationsCheckbox (boolean expectedState)
    {
        setCheckboxState(searchOnlyValidQuotationsCheckbox,expectedState);
    }
    public void setSearchInOtherLSUCheckbox(boolean expectedState)
    {
        setCheckboxState(searchInOthersLSUCheckbox,expectedState);
    }
    public void setHoldingCheckbox(boolean expectedStatus)
    {
        setCheckboxState(holdingCheckbox,expectedStatus);
    }

    private void extendCountries(String continent)
    {
        By by = By.xpath("//span[text()='"+continent+"']/.././descendant::span[@class='k-icon k-minus']");
        //span[text()='Europe']/.././descendant::span[@class='k-icon k-minus']

            waitOnElementToBeClickable(by);
            click(by);



    }

    //span[text()='Poland']/../../../../div/span[text()='Europe']
    //span[text()='Poland']/../../../../div/span[@class='k-state-selected k-in']
    //span[text()='Poland']/../../../../div/span[1]
    //By by = By.xpath("//span[text()='"+country+"']/../../../../div/span[1]"); // extend and roll będę używał do setCountryCheckbox
    //span[text()='Poland']/../../../../ul

/*
    public void setCountryCheckbox(boolean expectedStatus,String country)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        By by = By.xpath("//span[text()='"+country+"']/../../../../ul");
       // js.executeScript("arguments[0].setAttribute('style','display: block')", findElement(by));
        by = By.xpath("//span[text()='"+country+"']/.././span/input");
        setCheckboxState(by,expectedStatus);

        /*by = By.xpath("//span[text()='"+country+"']/.././span/input");
        setCheckboxState(by,expectedStatus);
    }
*/

    public void setCountryCheckbox(boolean expectedStatus,String country)
    {
        By by = By.xpath("//span[text()='"+country+"']/../../../../div/span[1]");
        click(by);
        by = By.xpath("//span[text()='"+country+"']/.././span/input");
        setCheckboxState(by,expectedStatus);
        by = By.xpath("//span[text()='"+country+"']/../../../../div/span[1]");
        click(by);

    }

    //boxes REMOVE
    public void removeBox(String string)
    {
        By by = By.xpath("//span[text()='"+string+"']/../div");
        waitOnElementToBeClickable(by);
        click(by);
    }


    //essential methods for SearchQuotationPage
    public void clickSearchButton() {
        waitOnButton(searchButton);
        click(searchButton);
    }

    public boolean isSearchQuotationPage()
    {
        boolean isSearchQuotationPage = true;
        waitForPageLoad(driver);
        if(!driver.getTitle().contains("Search Quotation")){
            isSearchQuotationPage = false;
        }
        return isSearchQuotationPage;
    }

    public int getTotalNumberOfHits()
    {
        waitForPageLoad(driver);
        String numberOfHitsS = "";
        if (isVisible(referenceNumberSearchField))
        {
            waitForPageLoad(driver);

                if(isVisible(isMoreThanOnePage)) //do ulepszenia
                {
                     numberOfHitsS = driver.findElement(numberOfHitsMoreThanOnePage).getText();
                }
                else
                {
                    numberOfHitsS = driver.findElement(numberOfHitsOnePage).getText();
                }

            return Integer.parseInt(numberOfHitsS.substring(0, numberOfHitsS.length() - 1).split(":")[1].trim());

        }
        else
        {
            return 0;
        }


    }

    public boolean wasQuotationSearchable(int x) {
        return x > 0;
    }



}
