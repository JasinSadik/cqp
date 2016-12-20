package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by PLJAHAS on 2016-12-16.
 */
public class CommonMethods extends Page {

    protected WebElement element;

    public CommonMethods(WebDriver driver) {
        super(driver);
    }


    //..........................Waiters......................
    protected void waitOnButton(WebElement element) {
        waitOnElementToBeVisible(element);
        waitOnElementToBeClickable(element);
    }

    protected void waitOnButton(By by) {
        waitOnElementToBeVisible(by);
        waitOnPresenceOfElement(by);
        waitOnElementToBeClickable(by);
    }

    protected void waitOnPresenceOfElement(By by) {
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            System.out.print("Timeout - Element not found");
        }
    }

    protected void waitOnElementToBeVisible(WebElement element) {
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            System.out.print("Timeout - Element not found");
        }
    }

    protected void waitOnElementToBeVisible(By by) {
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            System.out.print("Timeout - Element not found");
        }
    }

    protected void waitOnElementToBeClickable(WebElement element) {
        try {
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            System.out.print("Timeout - Element not found");
        }
    }

    protected void waitOnElementToBeClickable(By by) {
        try {
            new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(by));
        } catch (TimeoutException e) {
            System.out.print("Timeout - Element not found");
        }
    }

    protected void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }

    //..........................SeleniumMethods......................
    protected WebElement findElement(By by) {
        waitOnElementToBeVisible(by);
        element = driver.findElement(by);
        return element;
    }

    protected void sendKeys(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    protected void setAttribute(WebDriver driver, WebElement element, String attributeName, String key) {
        boolean elementStatus = true;
        while (elementStatus == true) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].setAttribute('+'attributeName'+', '" + key + "')", element);
                elementStatus = false;
            } catch (WebDriverException e) {
                elementStatus = true;
            }
        }
        System.out.print(timestamp() + " --> ");
        element = null;
    }

    protected void click(By by) {
        boolean elementStatus = true;
        while (elementStatus) {
            try {
                new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", driver.findElement(by));
                elementStatus = false;

            } catch (WebDriverException e) {
                System.out.println("Timeout - element not found");
            }
        }
    }

    protected void clear(By by) {
        driver.findElement(by).clear();
    }

    protected String getText(By by) {
        return driver.findElement(by).getText();
    }

    //..........................ConfigParser......................
    public String getPropertyFromConfigurationFile(String key) throws Exception {
        boolean check = false;
        BufferedReader configFile = new BufferedReader(new FileReader(new File(System.getProperty("user.dir") + "\\src\\main\\java\\common\\configuration.txt")));
        while (!check) {
            String[] line = configFile.readLine().split(":");
            if (line[0].contains(key)) {
                key = "";
                for (int i = 1; i < line.length; i++) {
                    key = (line.length - 1 == i) ? key + line[i].trim() : key + line[i].trim() + ":";
                }
                configFile.close();
                break;
            }
        }
        return key;
    }

    //.........................SqlMethods.......................

    protected Statement initDB() throws Exception {
        Class.forName(getPropertyFromConfigurationFile("class_db"));
        Connection conn;
        conn = DriverManager.getConnection(getPropertyFromConfigurationFile("db_url"), "", "");
        Statement stmt = conn.createStatement();
        return stmt;
    }

    protected void updateDB(String table, String columnNameLocation, String valueLocation, String columnName, String value, String position) throws Exception {
        Statement stmt = initDB();
        String environment = getPropertyFromConfigurationFile("environment");
        stmt.executeUpdate("UPDATE [" + environment + "].[dbo].[" + table + "] SET " + columnName + "=NULL WHERE " + columnNameLocation + "='" + valueLocation + "' AND Position = " + "'" + position + "'");

    }

    protected ResultSet readDB(String table, String columnNameLocation, String valueLocation, String columnName, String position) throws Exception {
        ResultSet rs;
        Statement stmt = initDB();
        String environment = getPropertyFromConfigurationFile("environment");
        rs = stmt.executeQuery("SELECT [" + columnName + "] FROM [" + environment + "].[dbo].[" + table + "] WHERE " + columnNameLocation + "='" + valueLocation + "'");
        return rs;
    }

    //..........................BrowserSetup....................
    public WebDriver browserSetup() throws Exception {
        String browser = getPropertyFromConfigurationFile("browser");
        int timeout = Integer.parseInt(getPropertyFromConfigurationFile("timeout"));
        switch (browser) {
            case "IE":
                driver = new InternetExplorerDriver();
                driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                break;
            case "CHROME":
                System.setProperty("webdriver.chrome.driver", getPropertyFromConfigurationFile("chrome_path"));
                driver = new ChromeDriver();
                driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
                break;
        }
        return driver;
    }

    public void setTimout(WebDriver driver, int timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    //..........................Others......................
    protected void snapshot(WebDriver driver, String testName) throws Exception {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String newFileSrc = testName + "_" + timestamp() + ".jpg";

        FileUtils.copyFile(screenshot, new File(getPropertyFromConfigurationFile("snapshot_path") + newFileSrc));
        System.out.println("New screenshot was saved: " + newFileSrc);
    }

    public String timestamp() {
        java.util.Date date = new java.util.Date();
        String timestamp = (new Timestamp(date.getTime())).toString().substring(0, 19);
        return timestamp;
    }

    protected void selectElementFromDropdownList(String comboBoxId, String value) {
        int index = 1;
        By comboBoxButton = By.xpath("//input[contains(@id , '" + comboBoxId + "')]/..//span[contains(text(), 'select')]");
        By comboBoxList = By.xpath("//ul[contains(@id , '" + comboBoxId + "_listbox')]/li");
        By comboBoxElement = By.xpath("//ul[contains(@id , '" + comboBoxId + "_listbox')]/li[" + index + "]");
        waitOnButton(comboBoxButton);
        while(driver.findElements(comboBoxList).size() == 0){
            // to fill up
        }
        click(comboBoxButton);
        while (driver.findElements(comboBoxList).size() > index - 1) {
            waitOnElementToBeClickable(driver.findElement(comboBoxElement));
            if (driver.findElement(comboBoxElement).getText().toLowerCase().contains(value.toLowerCase())) {
                click(comboBoxElement);
                break;
            } else {
                index++;
                comboBoxElement = By.xpath("//ul[contains(@id , '" + comboBoxId + "_listbox')]/li[" + index + "]");
            }
        }
    }

    protected void scrollToElement(By by) {
        element = driver.findElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

}
