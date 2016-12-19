import common.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

/**
 * Created by PLJAHAS on 2016-12-15.
 */
public abstract class BaseScenario {
    protected WebDriver driver;

    final String USERNAME = new CommonMethods(driver).getPropertyFromConfigurationFile("user");
    final String PASSWORD = new CommonMethods(driver).getPropertyFromConfigurationFile("password");

    protected BaseScenario() throws Exception {
    }








}
