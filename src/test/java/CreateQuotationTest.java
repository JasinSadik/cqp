import common.CommonMethods;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.popUpWindows.confirmationPopUp.SfdcSyncConfirmationModal;
import pageObjects.quotationTabs.QuotationNavigationBar;
import pageObjects.quotationTabs.productsAndPricesPage.ProductLine;
import pageObjects.quotationTabs.productsAndPricesPage.ProductsAndPricesCommonActionButtons;
import pageObjects.quotationTabs.quotationClassificationPage.QuotationClassificationCommonActionButtonsSection;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.GeneralSection;

import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class CreateQuotationTest extends BaseScenario {
    private final String CUSTOMER = "29187";
    private final String INUDSTRY_USAGE_LEVEL1 = "Electric Utility";
    private final String INUDSTRY_USAGE_LEVEL2 = "Hydro";
    private final String QUOTATION_TYPE = "Product quotation";
    private final String PROJECT_NAME = new CommonMethods(driver).timestamp();

    protected CreateQuotationTest() throws Exception {
    }

    @BeforeTest
    public void before() throws Exception {
        driver = new CommonMethods(driver).browserSetup();
        driver.get(new CommonMethods(driver).getPropertyFromConfigurationFile("environment_url"));
    }

    @Test(priority = 1)
    public void shouldLogIntoToCqp() {
        LoginPage loginPage = new LoginPage(driver);
        LsuDashboard lsuDashboard = loginPage.logInToCqp(USERNAME, PASSWORD);
        assertTrue(true);
    }

    @Test(priority = 2)
    public void shouldOpenQuotationFromSearch() {
        LsuDashboard lsuDashboard = new LsuDashboard(driver);
        QuotationNavigationBar quotationNavigationBar = lsuDashboard.openQuotationFromQuickSearch("CQ189504");
    }

    @Test(priority = 3)
    public void shouldAddProductFromMotconf() throws InterruptedException {
        QuotationNavigationBar quotationNavigationBar = new QuotationNavigationBar(driver);
        Thread.sleep(15000); // taby do poprawy
        ProductsAndPricesCommonActionButtons productsAndPricesCommonActionButtons = quotationNavigationBar.goToProductAndPriceTab(ProductsAndPricesCommonActionButtons.class);
        productsAndPricesCommonActionButtons.addProductFromLvDrive("ACS800-01-0120-3+E202+K454");
        productsAndPricesCommonActionButtons.addProductFromMotConf("3GKP182410-AEH+066+069");
        Thread.sleep(1000);


    }
//    @Test(priority = 2)
//    public void shouldCreateQuotation() {
//        LsuDashboard lsuDashboard = new LsuDashboard(driver);
//        CustomerDataSection customerDataSection = lsuDashboard.pressNewQuotationButton();
//        customerDataSection.selectCustomerFromSearch(CUSTOMER);
//        customerDataSection.pressTodayRfqButton();
//        customerDataSection.setIndustryUsageLevelOne(INUDSTRY_USAGE_LEVEL1);
//        customerDataSection.setIndustryUsageLevelTwo(INUDSTRY_USAGE_LEVEL2);
//        GeneralSection generalSection = customerDataSection.pressSaveAndCollapseButton();
//        generalSection.insertProjectName(PROJECT_NAME);
//        generalSection.setQuotationType(QUOTATION_TYPE);
//        QuotationClassificationCommonActionButtonsSection quotationClassificationCommonActionButtonsSection = generalSection.pressSaveAndCollapseButton(QuotationClassificationCommonActionButtonsSection.class);
//        SfdcSyncConfirmationModal sfdcSyncConfirmationModal = quotationClassificationCommonActionButtonsSection.pressCreateQuotationButton();
//        sfdcSyncConfirmationModal.pressConfirmButton();
//
//    }


    @AfterTest
    public void after() {
        driver.close();
        driver.quit();

    }
}
