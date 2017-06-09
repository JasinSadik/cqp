package scenarios;

import common.CommonMethods;

/**
 * Created by PLJAHAS on 2017-06-05.
 */
public class ScenarionSuesmot extends BaseScenario {

    final protected String CUSTOMER =  "320345";
    final protected String INUDSTRY_USAGE_LEVEL1 = "Electric Utility";
    final protected String INUDSTRY_USAGE_LEVEL2 = "Hydro";
    final protected String QUOTATION_TYPE = "Product quotation";
    final protected String LANGUAGE = "Spanish";
    final protected String LV_DRIVE_APPLICATION = "Calander Line";
    final protected String MOTCONF_APPLICATION = "Centrifuge";
    final protected String LV_DRIVE_PRODUCT_WITHOUT_VC = "ACS800-01-0120-3";
    final protected String LV_DRIVE_PRODUCT_WITH_VC = "ACS800-01-0120-3+E202+K454";
    final protected String MOTCONF_PRODUCT_WITHOUT_VC = "3GKP182410-AEH";
    final protected String MOTCONF_PRODUCT_WITH_VC = "3GKP182410-AEH+066+069";
    final protected String LSU = "SUESMOT";

    protected String quotationNumber = "";


    final protected String USERNAME = new CommonMethods(driver).getPropertyFromConfigurationFile("user");
    final protected String PASSWORD = new CommonMethods(driver).getPropertyFromConfigurationFile("password");

    protected ScenarionSuesmot() throws Exception {
    }
}
