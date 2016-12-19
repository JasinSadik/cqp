import common.CommonMethods;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.mainPages.LoginPage;
import pageObjects.mainPages.LsuDashboard;
import pageObjects.popUpWindows.confirmationPopUp.ConfirmationModal;
import pageObjects.quotationTabs.quotationClassificationPage.AdditionalDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.CustomerDataSection;
import pageObjects.quotationTabs.quotationClassificationPage.GeneralSection;

import static org.junit.Assert.assertTrue;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class CreateQuotationTest extends BaseScenario {


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
    public void shouldCreateQuotation() throws InterruptedException {
        LsuDashboard lsuDashboard = new LsuDashboard(driver);
        CustomerDataSection customerDataSection = lsuDashboard.pressNewQuotationButton();
        customerDataSection.selectCustomerFromSearch("29187");
        Thread.sleep(2000);
        customerDataSection.insertRfqReceivedDate("12-19-16");
        customerDataSection.setIndustryUsageLevelOne("Electric Utility");
        Thread.sleep(2000);
        customerDataSection.setIndustryUsageLevelTwo("Hydro");
        GeneralSection generalSection = customerDataSection.pressSaveAndCollapseButton();
        generalSection.insertProjectName("New project - Jasin Test");
        generalSection.setQuotationType("product quotation");
        AdditionalDataSection additionalDataSection = generalSection.pressSaveAndCollapseButton(AdditionalDataSection.class);
        ConfirmationModal confirmationModal = additionalDataSection.setQuotationLanguage("English (Zimbabwe)");
        confirmationModal.pressYesButton();
        Thread.sleep(5000);

    }

    @AfterTest
    public void after() {
        driver.close();
        driver.quit();

    }
}
