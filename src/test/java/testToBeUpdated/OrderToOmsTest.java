package testToBeUpdated;

import common.CommonMethods;
import common.Parser;
import common.sqlMethods.Sql_ApprovalBehavior;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.popUpWindows.CreditLimitPopUp;
import pageObjects.popUpWindows.Toasts;
import pageObjects.quotationTabs.CloseQuotationPage;
import pageObjects.quotationTabs.fullCostAndFinalizationPage.DocumentGenerationSection;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.productsAndPricesPage.ProductLine;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesPage;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.GeneralSection;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationPage;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestCreationPage;
import scenarios.ScenarionSuesmot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class OrderToOmsTest extends ScenarionSuesmot {

    private final String PROJECT_NAME = new CommonMethods(driver).timestamp();

    protected OrderToOmsTest() throws Exception {
    }


    @BeforeTest
    public void before() throws Exception {
        driver = new CommonMethods(driver).browserSetup();
        driver.get(new CommonMethods(driver).getPropertyFromConfigurationFile("environment_url"));

        new Sql_ApprovalBehavior(driver).BindingGeneralApprovalSetNo(LSU);
        new Sql_ApprovalBehavior(driver).NonBindingGeneralApprovalSetNo(LSU);
    }

    @Test(priority = 1)
    public void shouldLogIntoToCqp() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInToCqp(USERNAME, PASSWORD);
        LsuDashboard lsuDashboard  = new LsuDashboard(driver);
        assertTrue(lsuDashboard.getCurrentlyLoggedUser().contains(USERNAME));
    }


    @Test(priority = 2)
    public void shouldCreateQuotation() {
        LsuDashboard lsuDashboard = new LsuDashboard(driver);
        CustomerDataSection customerDataSection = lsuDashboard.pressNewQuotationButton();
        customerDataSection.selectCustomerFromSearch(CUSTOMER);
        CreditLimitPopUp creditLimitPopUp = new CreditLimitPopUp(driver);
        creditLimitPopUp.pressOkButton();
        customerDataSection.pressTodayRfqButton();
        customerDataSection.setIndustryUsageLevelOne(INUDSTRY_USAGE_LEVEL1);
        customerDataSection.setIndustryUsageLevelTwo(INUDSTRY_USAGE_LEVEL2);
        GeneralSection generalSection = new GeneralSection(driver);
        customerDataSection.pressSaveAndCollapseButton();
        generalSection.insertProjectName(PROJECT_NAME);
        generalSection.setQuotationType(QUOTATION_TYPE);
        generalSection.pressBindingQuotationCategoryButton();
        AdditionalDataSection additionalDataSection = new AdditionalDataSection(driver);
        generalSection.pressSaveAndCollapseButton();
        //  additionalDataSection.setQuotationLanguage(LANGUAGE);
        QuotationClassificationPage quotationClassificationPage = new QuotationClassificationPage(driver);
        quotationClassificationPage.pressCreateQuotationButton();
        assertTrue(new Toasts(driver).isQuotationSavedSuccessfullyToatstrDisplayed());

    }

    @Test(priority = 3)
    public void shouldAddProducts(){
        quotationNumber = new SupportRequestCreationPage(driver).getQuotationNumber();
        ProductsAndPricesPage productsAndPricesPage = new ProductsAndPricesPage(driver);
        productsAndPricesPage.addProductFromLvDrive(LV_DRIVE_PRODUCT_WITH_VC);
        ProductLine productLine = new ProductLine(driver);
        productLine.setApplication(1, LV_DRIVE_APPLICATION);
    }

    @Test(priority = 4)
    public void shouldGenerateAndIssueDocument(){
        QuotationNavigationBar quotationNavigationBar = new QuotationNavigationBar(driver);
        DocumentGenerationSection documentGenerationSection = new DocumentGenerationSection(driver);
        quotationNavigationBar.goToFullCostAndFinalizationTab();
        documentGenerationSection.generateAndIssueDocumentManually();
        assertTrue(new Toasts(driver).isDocumentCreatedToastDisplayed());
        assertTrue(new Toasts(driver).isDocumentIssuedToastDisplayed());
    }

    @Test(priority = 5)
    public void shouldSetWonStatus(){
        QuotationNavigationBar quotationNavigationBar = new QuotationNavigationBar(driver);
        CloseQuotationPage closeQuotationTab = new CloseQuotationPage(driver);
        quotationNavigationBar.goToCloseQuotationTab();
        closeQuotationTab.setWonStatus();
        assertTrue(new Toasts(driver).isQuoationStatusChangedDisplayed());
    }

    @Test(priority = 6)
    public void shouldSavePreOrderData(){
        new CloseQuotationPage(driver).fillInPreOrderData("12345678");
        assertTrue(new Toasts(driver).isPreOrderDataSectionSaved());
    }

    @Test(priority = 7)
    public void shouldSendOrder(){
        CloseQuotationPage closeQuotationPage = new CloseQuotationPage(driver);
        closeQuotationPage.selectProductForOrdering(1, "OMS");
        closeQuotationPage.pressSubmitOrderButton();
        assertTrue(new Toasts(driver).isOrderSentSuccessfully());
    }

    @Test(priority = 8)
    public void shouldConfirmCorrectDataSending(){
        System.out.println(new Parser().parseOrderXml(quotationNumber,"LSUCode", false, true));
        System.out.println(new Parser().parseOrderXml(quotationNumber,"CustomerCode", false, false));
        System.out.println(new Parser().parseOrderXml(quotationNumber,"CustomerCountryCode", false, false));
        System.out.println(new Parser().parseOrderXml(quotationNumber,"CustomerName", false, false));
        System.out.println(new Parser().parseOrderXml(quotationNumber,"DomainOIS", true, false));
    }




    @AfterTest
    public void after() {
        driver.close();
        driver.quit();

    }
}
