import baseScenarios.BaseScenario;
import common.CommonMethods;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.mainPages.PuDashboard;
import pageObjects.mainPages.TopMenu;
import pageObjects.popUpWindows.confirmationPopUp.SfdcSyncConfirmationModal;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestPuViewPage;
import pageObjects.quotationTabs.supportRequestPage.SupportRequestStartPage;
import pageObjects.quotationTabs.productsAndPricesPage.ProductLine;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesCommonActionButtons;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.GeneralSection;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationCommonActionButtonsSection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2016-12-27.
 */
public class CreateSupportTest extends BaseScenario {
    protected CreateSupportTest() throws Exception {
    }


    private final String CUSTOMER = "29187";
    private final String INUDSTRY_USAGE_LEVEL1 = "Electric Utility";
    private final String INUDSTRY_USAGE_LEVEL2 = "Hydro";
    private final String QUOTATION_TYPE = "Product quotation";
    private final String PROJECT_NAME = new CommonMethods(driver).timestamp();
    private final String PU_USER = "asko.hokkanen@fi.abb.com";
    private String quotationNumber = "";

    @BeforeTest
    public void before() throws Exception {
        driver = new CommonMethods(driver).browserSetup();
        driver.get(new CommonMethods(driver).getPropertyFromConfigurationFile("environment_url"));
    }

    @Test(priority = 1)
    public void shouldLogIntoToCqp() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logInToCqp(USERNAME, PASSWORD);
        new TopMenu(driver).pressLogoutHyperlink();
        loginPage.logInToCqp(USERNAME, PASSWORD);
        assertTrue(true);

    }


    @Test(priority = 2)
    public void shouldCreateQuotation() throws InterruptedException {
        LsuDashboard lsuDashboard = new LsuDashboard(driver);
        CustomerDataSection customerDataSection = lsuDashboard.pressNewQuotationButton();
        customerDataSection.selectCustomerFromSearch(CUSTOMER);
        customerDataSection.pressTodayRfqButton();
        customerDataSection.setIndustryUsageLevelOne(INUDSTRY_USAGE_LEVEL1);
        customerDataSection.setIndustryUsageLevelTwo(INUDSTRY_USAGE_LEVEL2);
        GeneralSection generalSection = customerDataSection.pressSaveAndCollapseButton();
        generalSection.insertProjectName(PROJECT_NAME);
        generalSection.setQuotationType(QUOTATION_TYPE);
        AdditionalDataSection additionalDataSection = generalSection.pressSaveAndCollapseButton(AdditionalDataSection.class);
        additionalDataSection.setQuotationLanguage("Arabic");
        QuotationClassificationCommonActionButtonsSection quotationClassificationCommonActionButtonsSection = new QuotationClassificationCommonActionButtonsSection(driver);
        SfdcSyncConfirmationModal sfdcSyncConfirmationModal = quotationClassificationCommonActionButtonsSection.pressCreateQuotationButton();
        sfdcSyncConfirmationModal.pressConfirmButton();

    }

    @Test(priority = 3)
    public void shouldAddProducts() throws InterruptedException {
        ProductsAndPricesCommonActionButtons productsAndPricesCommonActionButtons = new ProductsAndPricesCommonActionButtons(driver);
        productsAndPricesCommonActionButtons.addProductFromLvDrive("ACS800-01-0120-3+E202+K454");
        ProductLine productLine = new ProductLine(driver);
        productLine.setApplication(1, "Calander Line");
    }

    @Test(priority = 4)
    public void shouldStartSupportRequest() throws InterruptedException {
        ProductLine productLine = new ProductLine(driver);
        SupportRequestStartPage supportRequestStartPage = productLine.createSupportRequest(1, "LV AC Drives Helsinki support");
        supportRequestStartPage.createSupportRequestForSpecificUser("Asko Hokkanen", "First Auto Support", "Special discount");
        quotationNumber = supportRequestStartPage.getQuotationNumber();

    }/*
    @Test(priority = 5)
    public void shouldOpenRequest() throws InterruptedException {
        TopMenu topMenu = new TopMenu(driver);
        LoginPage loginPage = topMenu.pressLogoutHyperlink();
        PuDashboard puDashboard = loginPage.logInToCqp(PU_USER, "a", PuDashboard.class);
        SupportRequestPuViewPage supportRequestPuViewPage = puDashboard.openSupportRequest(quotationNumber);
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
        SupportRequestPuViewPage supportRequestPuViewPage = new SupportRequestPuViewPage(driver);
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
