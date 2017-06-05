package common.sqlMethods;

import org.openqa.selenium.WebDriver;

/**
 * Created by PLJAHAS on 2017-06-02.
 */
public class Sql_UserPreferences extends SqlMethods {
    public Sql_UserPreferences(WebDriver driver) {
        super(driver);
    }

    public String getDefaultUnit(String UserName) throws Exception {
        String defalutUnit = readDB("UserPreferences", "DefaultUnit", "WHERE Username = '" + UserName + "'");
        return defalutUnit;
    }

}
