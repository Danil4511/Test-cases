package pages.OurCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.base.BasePage;

public class Cases_home_page extends BasePage {

    public Cases_home_page(WebDriver driver) {super(driver);}


    //Переменные - эл-ты страницы
    private final By login = By.xpath("//input[@placeholder='Username']");
    private final By pass = By.xpath("//input[@placeholder='Password']");
    private final By loginBtn = By.xpath("//input[@value='Login']");

    //Step 1 & 2
    public Cases_home_page enterUserData(String LOGIN, String PASSWORD) {
        //Step 1 - check all elements on the page
        checkLogsElements();

        //Step 2 - Log in
        driver.findElement(login).sendKeys(LOGIN);
        driver.findElement(pass).sendKeys(PASSWORD);
        driver.findElement(loginBtn).click();

        return this;
    }


}
