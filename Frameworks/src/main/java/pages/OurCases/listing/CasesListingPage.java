package pages.OurCases.listing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static constants.Constant.urls.TEST_CASES_HOME_PAGE;

public class CasesListingPage extends BasePage {
    public CasesListingPage(WebDriver driver) {
        super(driver);
    }
    private final By card = By.xpath("//div[@class='inventory_item']");
    private final By shoppingCart = By.xpath("//a[@class='shopping_cart_link']");
    private final By title = By.xpath("//span[@class='title']");
    private final By sortContainer = By.xpath("//select[@class='product_sort_container']");
    private final By addBtn = By.xpath("//button[text()='Add to cart']");
    private final By reverseOption = By.xpath("//select[@class='product_sort_container']//option[@value='za']");
    private final By menuBtn = By.id("react-burger-menu-btn");
    private final By menuLinks = By.xpath("//a[@class='bm-item menu-item']");
    private final By logOutBtn = By.xpath("//a[@id='logout_sidebar_link']");
    private final String[] nameLinks = {
            "ALL ITEMS",
            "ABOUT",
            "LOGOUT",
            "RESET APP STATE"};

    /**
     * ШАГ 2
     */
    public CasesListingPage checkMainElements() {
        int countTitle = driver.findElements(title).size();
        int countShoppingCart = driver.findElements(shoppingCart).size();
        int countSortContainer = driver.findElements(sortContainer).size();

        return this;
    }

    public CasesListingPage checkCountCards() {
        int countCard = driver.findElements(card).size();
        Assert.assertEquals(countCard, 6);

        compareCards(2);

        return this;
    }

    /**
     * ШАГ 3
     */
    public CasesListingPage checkAddBtn() {
        List<WebElement> listBtns = (List<WebElement>)
                driver.findElements(By.xpath
                        ("//button[text()='Add to cart']"));

        //Check button is active?
        for (int i = 0;i<listBtns.size();i++) {
            Boolean activeBtn = Boolean.valueOf(listBtns.get(i).getAttribute("disabled"));
            Assert.assertEquals(false, activeBtn);
        }

        //Click PRODUCTS
        /**
         * ШАГ 4 и 5
         */
        addAndRemoveElements(listBtns);

        return this;
    }

    /**
     * ШАГ 6
     */
    public CasesListingPage revCards() {
        driver.findElement(sortContainer).click();
        WebElement selectOption = driver.findElement(reverseOption);
        waitElementIsVisible(selectOption).click();

        compareCards(1);

        return this;
    }

    /**
     * ШАГ 7
     */
    public CasesListingPage menuLinksAndLogout() {
        driver.findElement(menuBtn).click();
        WebElement selectOption = driver.findElement(logOutBtn);
        waitElementIsVisible(selectOption);

        List<WebElement> listLinks = (List<WebElement>)driver.findElements(menuLinks);
        for (int i = 0;i<listLinks.size();i++)
        {
            //If not equal - not all elements is ok
            if (nameLinks[i].compareTo(listLinks.get(i).getText()) != 0)
                Assert.fail();
        }

        /**
         * ШАГ 8 - LOGOUT
         */
        selectOption.click();

        checkLogsElements();

        return this;
    }

    public void doAllActions() {
        checkMainElements();
        checkCountCards();
        checkAddBtn();
        revCards();
        menuLinksAndLogout();
    }

}
