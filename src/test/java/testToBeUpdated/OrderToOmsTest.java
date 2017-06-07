package testToBeUpdated;

import common.CommonMethods;
import common.sqlMethods.Sql_ApprovalBehavior;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.popUpWindows.confirmationPopUp.SfdcSyncConfirmationModal;
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
import scenarios.ScenarioSweden;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class OrderToOmsTest extends ScenarioSweden {

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
        customerDataSection.pressTodayRfqButton();
        customerDataSection.setIndustryUsageLevelOne(INUDSTRY_USAGE_LEVEL1);
        customerDataSection.setIndustryUsageLevelTwo(INUDSTRY_USAGE_LEVEL2);
        customerDataSection.pressSaveAndCollapseButton();
        GeneralSection generalSection =new GeneralSection(driver);
        generalSection.insertProjectName(PROJECT_NAME);
        generalSection.setQuotationType(QUOTATION_TYPE);
        AdditionalDataSection additionalDataSection = new AdditionalDataSection(driver);
        generalSection.pressSaveAndCollapseButton();
        additionalDataSection.setQuotationLanguage(LANGUAGE);
        QuotationClassificationPage quotationClassificationPage = new QuotationClassificationPage(driver);
        SfdcSyncConfirmationModal sfdcSyncConfirmationModal = new SfdcSyncConfirmationModal(driver);
        quotationClassificationPage.pressCreateQuotationButton();
        sfdcSyncConfirmationModal.pressConfirmButton();

    }

    @Test(priority = 3)
    public void shouldAddProducts(){
        QuotationNavigationBar quotationNavigationBar = new QuotationNavigationBar(driver);
        ProductsAndPricesPage productsAndPricesPage = new ProductsAndPricesPage(driver);
        quotationNavigationBar.goToProductAndPriceTab();
        productsAndPricesPage.addProductFromLvDrive(LV_DRIVE_PRODUCT_WITH_VC);
        productsAndPricesPage.addProductFromMotConf(MOTCONF_PRODUCT_WITHOUT_VC);
        ProductLine productLine = new ProductLine(driver);
        productLine.setApplication(1, LV_DRIVE_APPLICATION);
        productLine.setApplication(2, MOTCONF_APPLICATION);

    }

    @Test(priority = 4)
    public void shouldGenerateAndIssueDocument(){
        QuotationNavigationBar quotationNavigationBar = new QuotationNavigationBar(driver);
        DocumentGenerationSection documentGenerationSection = new DocumentGenerationSection(driver);
        quotationNavigationBar.goToFullCostAndFinalizationTab();
        documentGenerationSection.generateAndIssueDocumentManually();
        CloseQuotationPage closeQuotationTab = new CloseQuotationPage(driver);
        quotationNavigationBar.goToCloseQuotationTab();
        closeQuotationTab.setWonStatus();
        closeQuotationTab.fillInPreOrderData();
        closeQuotationTab.setProductOrderingSystem(1, "OMS");
        closeQuotationTab.setProductOrderingSystem(2, "OMS");
        closeQuotationTab.setProductOrderingSystem(3, "OMS");
        closeQuotationTab.setProductOrderingSystem(4, "OMS");
        closeQuotationTab.submitOrder();
    }

    @AfterTest
    public void after() {
        driver.close();
        driver.quit();

    }
}
