package testToBeUpdated;

import common.CommonMethods;
import common.sqlMethods.Sql_QuoteHeader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.mainPages.TopMenu;
import pageObjects.popUpWindows.CreditLimitPopUp;
import pageObjects.popUpWindows.Toasts;
import pageObjects.popUpWindows.confirmationPopUp.RemoveRequestConfirmationPopUp;
import pageObjects.popUpWindows.confirmationPopUp.SfdcSyncConfirmationModal;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestCreationPage;
import pageObjects.quotationTabs.productsAndPricesPage.ProductLine;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesPage;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.GeneralSection;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationPage;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestPage;
import scenarios.ScenarioSweden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2016-12-27.
 */
public class CreateSupportTest extends ScenarioSweden {


    private final String PROJECT_NAME = new CommonMethods(driver).timestamp();
    private final String PU_USER = "asko.hokkanen@fi.abb.com";
    private final String PU_NAME = "Asko Hokkanen";
    private final String LV_L3_SUPPORT = "LV AC Drives Helsinki support";
    private String quotationNumber = "";

    protected CreateSupportTest() throws Exception {
    }

    @BeforeTest
    public void before() throws Exception {
        driver = new CommonMethods(driver).browserSetup();
        driver.get(new CommonMethods(driver).getPropertyFromConfigurationFile("environment_url"));
    }

    @Test(priority = 1)
    public void shouldLogIntoToCqp() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInToCqp(USERNAME, PASSWORD);
        assertTrue(true);
        new TopMenu(driver).relogOnUser("lidia.kosiorowska@pl.abb.com");
    }


    @Test(priority = 2)
    public void shouldCreateQuotation() throws InterruptedException {
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
        AdditionalDataSection additionalDataSection = new AdditionalDataSection(driver);
        generalSection.pressSaveAndCollapseButton();
        additionalDataSection.setQuotationLanguage("Arabic");
        QuotationClassificationPage quotationClassificationPage = new QuotationClassificationPage(driver);
        quotationClassificationPage.pressCreateQuotationButton();
        new SfdcSyncConfirmationModal(driver).pressConfirmButton();
    }

    @Test(priority = 3)
    public void shouldAddProducts() throws InterruptedException {
        ProductsAndPricesPage productsAndPricesPage = new ProductsAndPricesPage(driver);
        ProductLine productLine = new ProductLine(driver);
        productsAndPricesPage.addProductFromLvDrive(LV_DRIVE_PRODUCT_WITH_VC);
        productLine.setApplication(1, LV_DRIVE_APPLICATION);
    }

    @Test(priority = 4)
    public void shouldStartSupportRequest() throws InterruptedException {
        ProductLine productLine = new ProductLine(driver);
        SupportRequestCreationPage supportRequestCreationPage = new SupportRequestCreationPage(driver);
        productLine.createSupportRequest(1, LV_L3_SUPPORT);
        supportRequestCreationPage.createSupportRequestForSpecificUser(PU_NAME, "First Auto Support", "Special discount");
        ProductsAndPricesPage productsAndPricesPage = new ProductsAndPricesPage(driver);
        assertTrue("The support request is not sent. Probably caused by K2 unavailability.", productsAndPricesPage.isAddProductButtonDisplayed());
        quotationNumber = supportRequestCreationPage.getQuotationNumber();
    }

    @Test(priority = 5)
    public void shouldRemoveSupportRequest() throws InterruptedException {
        SupportRequestPage supportRequestPage = new SupportRequestPage(driver);
        QuotationNavigationBar quotationNavigationBar = new QuotationNavigationBar(driver);
        quotationNavigationBar.goToSupportRequestTab();
        supportRequestPage.pressRemoveRequestIcon(1);
        RemoveRequestConfirmationPopUp removeRequestConfirmationPopUp = new RemoveRequestConfirmationPopUp(driver);
        removeRequestConfirmationPopUp.pressRemoveRequestConfirmationButton();
        assertTrue("Support can not be removed.", new Toasts(driver).isRemoveRequestToastrDisplayed());
    }

    @Test(priority = 6)
    public void shouldRemoveQuotation() throws Exception {
        new QuotationNavigationBar(driver).goToQuotationClassificationTab();
        new QuotationClassificationPage(driver).deleteQuotation();
        assertTrue("Quotation can not be deleted.", new Sql_QuoteHeader(driver).getIsQuotationDeleted(quotationNumber));
    }


    @AfterTest
    public void after() {
        driver.close();
        driver.quit();
    }


}
