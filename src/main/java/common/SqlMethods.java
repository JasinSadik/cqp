package common;

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

    public void BindingGeneralApprovalSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalBinding = 0 WHERE LsuID = '" + Lsu + "'");
    }


    public void BindingGeneralApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void BindingGeneralApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }


    public void BindingCurrencyApprovalSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyBinding = 0 WHERE LsuID = ''" + Lsu + "'" + "'");
    }

    public void BindingCurrencyApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void BindingCurrencyApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }

    public void BindingQualificationTestApprovalSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestBinding = 0 WHERE LsuID = '" + Lsu + "'");
    }

    public void BindingQualificationTestApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void BindingQualificationTestApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }


    public void NonBindingGeneralApprovalSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalNoBinding = 0 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingGeneralApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalNoBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingGeneralApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET GeneralApprovalNoBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }


    public void NonBindingCurrencyApprovalsSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyNoBinding = 0 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingCurrencyApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyNoBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingCurrencyApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET CurrencyNoBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingQualificationTestApprovalSetNo(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestNoBinding = 0 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingQualificationTestApprovalSetNonBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestNoBinding = 1 WHERE LsuID = '" + Lsu + "'");
    }

    public void NonBindingQualificationTestApprovalSetBlocking(String Lsu) throws Exception {
        updateDB("ApprovalBehavior", "SET QualificationTestNoBinding = 2 WHERE LsuID = '" + Lsu + "'");
    }

    public String getUsersEmail(String user) {
        String useremail = user.toLowerCase().trim().split(" ")[0] + "." + user.toLowerCase().trim().split(" ")[1];
        ResultSet rs = null;
        try {
            rs = readDB("UserPreferences", "Username", " WHERE Username LIKE '%" + useremail + "%'");
            while (rs.next()) {
                useremail = rs.getString("Username");
            }
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

    protected ResultSet readDB(String table, String columnName, String readStatement) throws Exception {
        ResultSet rs = null;
        Statement stmt = initDB();
        String environment = null;
        environment = new CommonMethods(driver).getPropertyFromConfigurationFile("environment");
        rs = stmt.executeQuery("SELECT [" + columnName + "] FROM [" + environment + "].[dbo].[" + table + "]" + readStatement);
        return rs;
    }


}
