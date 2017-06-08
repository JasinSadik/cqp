package testToBeUpdated;

import common.CommonMethods;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.mainPages.TopMenu;
import pageObjects.popUpWindows.CreditLimitPopUp;
import pageObjects.popUpWindows.confirmationPopUp.SfdcSyncConfirmationModal;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestCreationPage;
import pageObjects.quotationTabs.productsAndPricesPage.ProductLine;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesPage;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.GeneralSection;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationPage;
import scenarios.ScenarioSweden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2016-12-27.
 */
public class CreateSupportTest extends ScenarioSweden {



    private final String CUSTOMER = "320345";
    private final String INUDSTRY_USAGE_LEVEL1 = "Electric Utility";
    private final String INUDSTRY_USAGE_LEVEL2 = "Hydro";
    private final String QUOTATION_TYPE = "Product quotation";
    private final String PROJECT_NAME = new CommonMethods(driver).timestamp();
    private final String PU_USER = "asko.hokkanen@fi.abb.com";
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
      //  customerDataSection.setIndustryUsageLevelTwo(INUDSTRY_USAGE_LEVEL2);
        GeneralSection generalSection = new GeneralSection(driver);
        customerDataSection.pressSaveAndCollapseButton();
        generalSection.insertProjectName(PROJECT_NAME);
        generalSection.setQuotationType(QUOTATION_TYPE);
        AdditionalDataSection additionalDataSection = new AdditionalDataSection(driver);
        generalSection.pressSaveAndCollapseButton();
        additionalDataSection.setQuotationLanguage("Arabic");
        QuotationClassificationPage quotationClassificationPage = new QuotationClassificationPage(driver);
        quotationClassificationPage.pressCreateQuotationButton();
      //  sfdcSyncConfirmationModal.pressConfirmButton();

    }

    @Test(priority = 3)
    public void shouldAddProducts() throws InterruptedException {
        ProductsAndPricesPage productsAndPricesPage = new ProductsAndPricesPage(driver);
        ProductLine productLine = new ProductLine(driver);
        productsAndPricesPage.addProductFromLvDrive("ACS800-01-0120-3+E202+K454");
        productLine.setApplication(1, "Calander Line");
    }

    @Test(priority = 4)
    public void shouldStartSupportRequest() throws InterruptedException {
        ProductLine productLine = new ProductLine(driver);
        SupportRequestCreationPage supportRequestCreationPage = new SupportRequestCreationPage(driver);
        productLine.createSupportRequest(1, "LV AC Drives Helsinki support");
        supportRequestCreationPage.createSupportRequestForSpecificUser("Asko Hokkanen", "First Auto Support", "Special discount");
        ProductsAndPricesPage productsAndPricesPage = new ProductsAndPricesPage(driver);
        assertTrue("The support request is not sent. Probably caused by K2 unavailability.", productsAndPricesPage.isAddProductButtonDisplayed());
        quotationNumber = supportRequestCreationPage.getQuotationNumber();

    }/*
    @Test(priority = 5)
    public void shouldOpenRequest() throws InterruptedException {
        TopMenu topMenu = new TopMenu(driver);
        LoginPage loginPage = topMenu.pressLogoutHyperlink();
        PuDashboard puDashboard = loginPage.logInToCqp(PU_USER, "a", PuDashboard.class);
        SupportRequestMainActionPage supportRequestPuViewPage = puDashboard.openSupportRequest(quotationNumber);
        assertTrue(supportRequestPuViewPage.checkDelegateButtonAvailability());
        assertTrue(supportRequestPuViewPage.checkDelegateToGroupButtonAvailability());
        assertTrue(supportRequestPuViewPage.checkForwardButtonAvailability());
        assertTrue(supportRequestPuViewPage.checkInformButtonAvailability());
        assertTrue(supportRequestPuViewPage.checkOnHoldButtonAvailability());
        assertTrue(supportRequestPuViewPage.checkReplyAndCloseButtonAvailability());
        assertTrue(supportRequestPuViewPage.checkRequestMoreInfoButtonAvailability());
    }

    @Test(priority = 6)
    public void shouldReplyToRequest() throws InterruptedException {
        SupportRequestMainActionPage supportRequestPuViewPage = new SupportRequestMainActionPage(driver);
        supportRequestPuViewPage.replyAndCloseRequest("dupa dupa");
        assertEquals("Answered", supportRequestPuViewPage.getCurrentStatus());
    }
*/
    @AfterTest
    public void after() {
        driver.close();
        driver.quit();
    }


}
