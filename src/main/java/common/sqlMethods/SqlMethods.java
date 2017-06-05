package common.sqlMethods;

import common.CommonMethods;
import common.Page;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;

import java.sql.*;

/**
 * Created by PLJAHAS on 2016-12-30.
 */
public class SqlMethods extends Page {

    public SqlMethods(WebDriver driver) {
        super(driver);
    }


    public String getUsersEmail(String user) {
        String useremail = user.toLowerCase().trim().split(" ")[0] + "." + user.toLowerCase().trim().split(" ")[1];
        ResultSet rs = null;
        try {
            useremail = readDB("UserPreferences", "Username", " WHERE Username LIKE '%" + useremail + "%'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return useremail;
    }

    protected Statement initDB() throws Exception {
        Class.forName(new CommonMethods(driver).getPropertyFromConfigurationFile("class_db"));
        Connection conn;
        conn = DriverManager.getConnection(new CommonMethods(driver).getPropertyFromConfigurationFile("db_url"), "", "");
        Statement stmt = conn.createStatement();
        return stmt;
    }

    protected void updateDB(String table, String updateStatement) throws Exception {
        Statement stmt = initDB();
        String environment = new CommonMethods(driver).getPropertyFromConfigurationFile("environment");
        stmt.executeUpdate("UPDATE [" + environment + "].[dbo].[" + table + "] " + updateStatement);

    }

    protected String readDB(String table, String columnName, String readStatement) throws Exception {
        ResultSet rs = null;
        Statement stmt = initDB();
        String environment = null;
        environment = new CommonMethods(driver).getPropertyFromConfigurationFile("environment");
        rs = stmt.executeQuery("SELECT [" + columnName + "] FROM [" + environment + "].[dbo].[" + table + "]" + readStatement);
        String singleResult = null;
        while (rs.next()) {
            singleResult = rs.getString(columnName);
        }
        if(singleResult==null){
            singleResult = "n/a";
        }
        return singleResult;
    }


}
