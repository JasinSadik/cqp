package pageObjects.quotationTabs.productsAndPricesPage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.quotationTabs.QuotationNavigationBar;

public class ProductsAndPricesCommonActionButtons extends QuotationNavigationBar {

    public ProductsAndPricesCommonActionButtons(WebDriver driver) {
        super(driver);
    }


    private By addToQuotationButton = By.cssSelector("#divActionMenu > div:nth-child(2) > button.primaryButton");
    private By quickSearchField = By.id("ProductAutoComplete");

    public ProductsAndPricesCommonActionButtons setProductToSearchInQuickSearchField (String productForSearch){
        waitOnPresenceOfElement(quickSearchField);
        clear(quickSearchField);
        sendKeys(quickSearchField,productForSearch);
        return this;
    }

    public ProductsAndPricesCommonActionButtons pressAddToQuotationButton (){
        waitOnButton(addToQuotationButton);
         click(addToQuotationButton);
        return this;
    }



}







//package quotationTabs;
//
//
//import common.CommonMethods;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import  common.CommonMethods.*;
//import  common.CommonMethods.click;
//import  quotationTabs.ProductAndPricesTab.Locators.CommonLocators.Inputs.quickSearchId;
//
//
//public class ProductAndPricesTab {
//
//    private  String c = "class";
//    private  String l = "link";
//    private  String i = "id";
//    private  String x = "xpath";
//    private  String n = "name";
//    private  WebElement element;
//    private  WebDriver driver;
////-------------------Page Title	Assertion
//
//    private  String productsAndPricesPageTitle()
//    {
//        return "Products - ABB CQP";
//    }
//
//    public  void quotationClassificationPageTitleCheck(WebDriver driver) {
//        assert driver.getTitle() == ProductAndPricesTab.productsAndPricesPageTitle();
//        System.out.println("Product And Prices Tab was displayed");
//    }
//    //-------------------Common
//    public  class Common extends CommonMethods {
//        public  class Buttons extends Locators{
//             public  void productActions(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.productActionsXpath());
//                click(driver, element);
//                System.out.println("Product Actions button was clicked");
//            }
//
//            public  void addToQuotation(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.addToQuotationXpath());
//                click(driver, element);
//                System.out.println("Add To Quotation button was clicked");
//            }
//
//            public  void addProduct(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.addProductXpath());
//                click(driver, element);
//                System.out.println("Add Product button was clicked");
//            }
//
//            public  void editQuoteSummary(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.editQuoteSummaryXpath());
//                click(driver, element);
//                System.out.println("Edit Quote Summary button was clicked");
//            }
//
//            public  void saveQuoteSummary(WebDriver driver) {
//                element = findElement(driver, x,  Locators.CommonLocators.Buttons());
//                click(driver, element);
//                System.out.println("Save Quote Summary button was clicked");
//            }
//
//            public  void configure(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.configureXpath());
//                click(driver, element);
//                System.out.println("Configure button was clicked");
//            }
//
//            public  void showSimulatedTransferPrices(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.showSimulatedTransferPricesXpath());
//                click(driver, element);
//                System.out.println("Show simulated transfer prices button was clicked");
//            }
//
//            public  void showRealTransferPrices(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.showRealTransferPricesXpath());
//                click(driver, element);
//                System.out.println("Show real transfer prices button was clicked");
//            }
//
//            public  void clearAllSimulationDiscounts(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.clearAllSimulationDiscountsXpath());
//                click(driver, element);
//                System.out.println("Clear all simulation discounts button was clicked");
//            }
//
//            public  void fcmSubtab(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.fcmSubtabXpath());
//                click(driver, element);
//                System.out.println("FCM Subtab was opened");
//            }
//
//            public  void allSubtab(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.allSubtabXpath());
//                click(driver, element);
//                System.out.println("All Subtab was opened");
//            }
//
//            public  void technicalDetailsSubtab(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.technicalDetailsSubtabXpath());
//                click(driver, element);
//                System.out.println("Technical Details Subtab was opened");
//            }
//
//            public  void priceDetailsSubtab(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.priceDetailsSubtabXpath());
//                click(driver, element);
//                System.out.println("Price Details Subtab was opened");
//            }
//
//            public  void termsAndConditionsSubtab(WebDriver driver) {
//                element = findElement(driver, x, CommonLocators.Buttons.termsAndConditionsSubtabXpath());
//                click(driver, element);
//                System.out.println("Terms & Conditions Subtab was opened");
//            }
//        }
//
//        public  class Inputs {
//            public  void quickSearch(WebDriver driver, String key) {
//                element = findElement(driver, i, quickSearchId());
//                sendKeys(driver, element, key);
//                System.out.println("Quick search was filled in with value - " + key);
//
//            }
//        }
//
//    }
//    //-------------------Price Details
//    public  class PriceDetailsSubtab extends Locators {
//        public  class Line  {
//            public  class Main {
//                public  class DescriptionColumn {
//                    public  class Buttons {
//                        public  void application(WebDriver driver, int lineNumber) {
//                            element = findElement(driver, x, Locators.Line.Main.DescriptionColumn.Buttons.applicationXpath(lineNumber));
//                            click(driver, element);
//                            System.out.println("Application button (line " + lineNumber + ") was clicked");
//                        }
//
//                        public  void productCategory(WebDriver driver, int lineNumber) {
//                            element = findElement(driver, x, Locators.Line.Main.DescriptionColumn.Buttons.productCategoryXpath(lineNumber));
//                            click(driver, element);
//                            System.out.println("Product Category button (line " + lineNumber + ") was clicked");
//                        }
//
//                        public  void saveProductCategory(WebDriver driver, int lineNumber) {
//                            element = findElement(driver, x, Locators.Line.Main.DescriptionColumn.Buttons.saveProductCategoryXpath(lineNumber));
//                            click(driver, element);
//                            System.out.println("Save Product Category button (line " + lineNumber + ") was clicked");
//                        }
//                    }
//                    public  class Inputs {
//                        public  void productCategory(WebDriver driver, int lineNumber, String key) {
//                            element = findElement(driver, x, Locators.Line.Main.DescriptionColumn.Inputs.productCategoryXpath(lineNumber));
//                            sendKeys(driver, element, key);
//                            System.out.println("Product category (line "+lineNumber+") was filled in with value - " + key);
//                        }
//                        public  void quantity(WebDriver driver, int lineNumber, String key) {
//                            element = findElement(driver, x, Locators.Line.Main.DescriptionColumn.Inputs.quantityXpath(lineNumber));
//                            sendKeys(driver, element, key, false);
//                            System.out.println("Quantity (line "+lineNumber+") was set to " + key);
//                        }
//                    }
//
//                }
//                public  class toCustomerColumn {
//                    public  class Inputs {
//                        public  void discount(WebDriver driver, int lineNumber, String key) {
//                            element = findElement(driver, x, Locators.Line.Main.ToCustomerColumn.Inputs.discountXpath(lineNumber));
//                            sendKeys(driver, element, key, false);
//                            System.out.println("Discount (line "+lineNumber+") was set to " + key);
//                        }
//                        public  void netPrice(WebDriver driver, int lineNumber, String key) {
//                            element = findElement(driver, x, Locators.Line.Main.ToCustomerColumn.Inputs.netPriceXpath(lineNumber));
//                            setAttribute(driver, element, "aria-valuenow", key);
//                            System.out.println("Net price (line "+lineNumber+") was set to " + key);
//                        }
//                    }
//                }
//                public  class LineActions {
//                    public  class Buttons {
//                        public  void save(WebDriver driver, int lineNumber) {
//                            element = findElement(driver, x, Locators.Line.Main.LineActions.Buttons.saveXpath(lineNumber));
//                            click(driver, element);
//                            System.out.println("Save Product button (line " + lineNumber + ") was clicked");
//                        }
//
//                        public  void edit(WebDriver driver, int lineNumber) {
//                            element = findElement(driver, x, Locators.Line.Main.LineActions.Buttons.editXpath(lineNumber));
//                            click(driver, element);
//                            System.out.println("Edit Product button (line " + lineNumber + ") was clicked");
//                        }
//
//                        public  void actions(WebDriver driver, int lineNumber) {
//                            element = findElement(driver, x, Locators.Line.Main.LineActions.Buttons.actionsXpath(lineNumber));
//                            click(driver, element);
//                            System.out.println("Line action button (line " + lineNumber + ") was clicked");
//                        }
//
//                        public  void expand_reduce(WebDriver driver, int lineNumber) {
//                            element = findElement(driver, x, Locators.Line.Main.LineActions.Buttons.expand_reduceXpath(lineNumber));
//                            click(driver, element);
//                            System.out.println("Line action button (line " + lineNumber + ") was clicked");
//                        }
//                    }
//                }
//            }
//
//        }
//
//    }
//
//
//    //-------------------All
//
//
//    //-------------------Technical Details
//
//
//    //-------------------Terms & Conditions
//
//
//    //-------------------FCM
//
////klasa pod common dziedzicząca
//
//
//    //-------------------Locators
//    protected static class Locators {
//        public static class CommonLocators {
//            public static class Buttons {
//
//                String saveQuoteSummaryXpath =  "//table[@id='productPrices']//tr[@class= 'product priceSummary']//td[@class='productActions']//div[contains(@class, 'icon saveAction')]";
//
//
//             /*   public static String saveQuoteSummaryXpath() {
//                  return "//table[@id='productPrices']//tr[@class= 'product priceSummary']//td[@class='productActions']//div[contains(@class, 'icon saveAction')]";
//                }
//                */
//                public static String editQuoteSummaryXpath() {
//                    return "//table[@id='productPrices']//tr[@class= 'product priceSummary']//td[@class='productActions']//div[contains(@class, 'startSaveAction')]";
//                }
//
//                public static String addProductXpath() {
//                    return "//*[@id='divActionMenu']//button[contains(text(),'Add product')]";
//                }
//
//                public static String productActionsXpath() {
//                    return "//*[@id='divMenuLastItem']//button[contains(text(), 'Product actions')]";
//                }
//
//                public static String addToQuotationXpath() {
//                    return "//*[@id='divActionMenu']//button[contains(text(), 'Add to quotation')]";
//                }
//
//                public static String configureXpath() {
//                    return "//*[@id='divActionMenu']//button[contains(text(), 'Configure')]";
//                }
//
//                public static String allSubtabXpath() {
//                    return "//*[@id='divTableProducts']//div[contains(text(), 'All')]";
//                }
//
//                public static  String priceDetailsSubtabXpath() {
//                    return "//*[@id='divTableProducts']//div[contains(text(), 'Price details')]";
//                }
//
//                public static  String technicalDetailsSubtabXpath() {
//                    return "//*[@id='divTableProducts']//div[contains(text(), 'Technical details')]";
//                }
//
//                public static  String termsAndConditionsSubtabXpath() {
//                    return "//*[@id='divTableProducts']//div[contains(text(), 'Terms & conditions')]";
//                }
//
//                public static  String fcmSubtabXpath() {
//                    return "//*[@id='divTableProducts']//div[contains(text(), 'FCM')]";
//                }
//
//                public static  String showSimulatedTransferPricesXpath() {
//                    return "//*[@id='divSimulatedTransferPrice']//span[contains(text(), 'Show simulated transfer prices')]";
//                }
//
//                public static  String showRealTransferPricesXpath() {
//                    return "//*[@id='divSimulatedTransferPrice']//span[contains(text(), 'Show real transfer prices')]";
//                }
//
//                public static  String clearAllSimulationDiscountsXpath() {
//                    return "//*[@id='divSimulatedTransferPrice']//span[contains(text(), 'Clear all simulation discounts')]";
//                }
//            }
//            public static  class Inputs {
//                public static  String quickSearchId() {
//                    return "ProductAutoComplete";
//                }
//            }
//        }
//        public static  class Line {
//            public static  class Main {
//                public static  class DescriptionColumn {
//                    public static  class Buttons {
//                        public static  String applicationXpath(int lineNumber) {
//                            return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]//b[contains(text(), 'Edit application')]/..", lineNumber);
//                        }
//
//                        public static  String productCategoryXpath(int lineNumber) {
//                            return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]//button[@class= 'smallButton']", lineNumber);
//                        }
//
//                        public static  String saveProductCategoryXpath(int lineNumber) {
//                            return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]/td[2]//div[@class='icon saveAction']", lineNumber);
//                        }
//                    }
//                    public static  class Inputs {
//                        public static  String quantityXpath(int lineNumber) {
//                            return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]//input[contains(@class,'formatted-value qtyInline')]", lineNumber);
//                        }
//                        public static  String productCategoryXpath(int lineNumber) {
//                           return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]//div[@class='description']//input[@class='bordered']", lineNumber);
//                        }
//
//
//
//                    }
//                }
//                public static  class ToCustomerColumn{
//                    public static  class Inputs {
//                        public static  String discountXpath(int lineNumber) {
//                            return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]//span[contains(@class,'formatted-value salesPriceDiscountField')]", lineNumber);
//                        }
//                        public static  String netPriceXpath(int lineNumber) {
//                            return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]//input[contains(@class,'k-formatted-value netPriceField')]", lineNumber);
//                        }
//                    }
//                }
//
//                public static  class LineActions {
//                    public static  class Buttons {
//                        public static  String saveXpath(int lineNumber) {
//                            //return "//*[@id='productsPrices']/tbody/tr[2]/td[7]/div/a";
//                            return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]//td[@class='lineItemActions']//a[contains(@class,'saveProduct')]", lineNumber);
//                        }
//
//                        public static  String editXpath(int lineNumber) {
//                            return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]//div[@class='lineItemActionsDiv']//a[contains(@class,'editProduct')]", lineNumber);
//                        }
//
//                        public static  String actionsXpath(int lineNumber) {
//                            return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]//td[@class='lineItemActions']//div[@id='productLineCommonOperations']/a", lineNumber);
//                        }
//
//                        public static  String expand_reduceXpath(int lineNumber) {
//                            return String.format("(//table[@id='productsPrices']//tr[contains(@class, 'product')])[%d]//td[@class='lineItemActions']//div[contains(@class, 'collapseAction') or contains(@class, 'expandAction')]", lineNumber);
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//}
