import common.CommonMethods;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

/**
 * Created by PLJAHAS on 2016-12-15.
 */
public abstract class BaseScenario {
    protected WebDriver driver;

    final String USERNAME = new CommonMethods(driver).getPropertyFromConfigurationFile("user");
    final String PASSWORD = new CommonMethods(driver).getPropertyFromConfigurationFile("password");

    protected BaseScenario() throws Exception {
    }


    @Before
    public void before() throws Exception {
        driver = new CommonMethods(driver).browserSetup();
        driver.get(new CommonMethods(driver).getPropertyFromConfigurationFile("environment_url"));
    }

    @After
    public void after(){
        driver.close();
        driver.quit();
    }



}
