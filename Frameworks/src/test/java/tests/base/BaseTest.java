package tests.base;

import common.CommonActions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import pages.OurCases.Cases_home_page;
import pages.OurCases.listing.CasesListingPage;
import pages.base.BasePage;

import static common.Config.CLEAR_COOKIES_AND_STORAGE;


//ЕГО задача нести некие общие параметры
//общие методы для всех тестовых классов
//все тесты будут наследоваться от него
public class BaseTest {
    protected WebDriver driver = CommonActions.createDriver();
    //ЭКЗ-ры классов
    protected BasePage basePage = new BasePage(driver);
    private boolean HOLD_BROWSER_OPEN;
    //Наши TEST_CASES
    protected Cases_home_page cases_home_page = new Cases_home_page(driver);
    protected CasesListingPage casesListingPage = new CasesListingPage(driver);


    /*Полезные методы, которые работают для всех тестов*/

    //Очистка куков перед запуском тестов
    //AfterTest - do all do after @Test -annotation
    @AfterTest
    public void clearCookiesAndLocalStorage(){
        if (CLEAR_COOKIES_AND_STORAGE)
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            driver.manage().deleteAllCookies();
            js.executeScript("window.sessionStorage.clear()");
        }
    }

    //Обязательно выполняется alwaysRun освобождает ресы
    @AfterSuite (alwaysRun = true)
    public void close() {
        if (HOLD_BROWSER_OPEN) {
            //quit - освобождает текущие ресурсы
            //close - закрывает тек. вкладку
            driver.quit();
        }
    }

}
