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


        return this;
    }

    //Click PRODUCTS
    /**
     * ШАГ 4 и 5
     */
    public CasesListingPage addAndRemoveElements() {
        List<WebElement> listBtns = (List<WebElement>)
                driver.findElements(By.xpath
                        ("//button[text()='Add to cart']"));

        for (int i = 0;i<listBtns.size();i++)
            listBtns.get(i).click();
        By countAddToCart = By.xpath("//a[@class='shopping_cart_link']");

        int countAdd = Integer.valueOf(driver.findElement(countAddToCart).getAttribute("innerText"));
        Assert.assertEquals(countAdd, 6);

        //remove elements from cart
        listBtns = driver.findElements(By.xpath
                ("//button[text()='Remove']"));
        for (int i = 0;i<listBtns.size();i++)
            listBtns.get(i).click();

        countAddToCart = By.xpath("//a[@class='shopping_cart_link']");
        String CountAdd = driver.findElement(countAddToCart).getAttribute("innerText");
        Assert.assertEquals(CountAdd, "");

        return this;
    }



    /**
     * ШАГ 6
     */
    public CasesListingPage reverseCards() {
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
            if (!nameLinks[i].equals(listLinks.get(i).getText()))
                Assert.fail();
        }

        /**
         * ШАГ 8 - LOGOUT
         */
        selectOption.click();
        checkLogsElements();

        return this;
    }

    public CasesListingPage addAnyProduct() {
        List<WebElement> listBtns = (List<WebElement>)
                driver.findElements(By.xpath
                        ("//button[text()='Add to cart']"));

        // Chose random product from list
        int chosenProduct = (int) (Math.random()*6);
        listBtns.get(chosenProduct).click();
        // check icon near a cart == 1
        int countAdd = Integer.valueOf(driver.findElement(shoppingCart).getAttribute("innerText"));
        Assert.assertEquals(countAdd, 1);

        //Checking the button has label "REMOVE"
        chosenProduct+=1;
        By productPath = By.xpath("//div[@class='inventory_item']["+chosenProduct+"]//button[text()='Remove']");
        Assert.assertEquals(driver.findElement(productPath).getText(), "REMOVE");

        /**
         * Our data about chosen product,
         * we need to save this data to compare it next step
         */
        WebElement choseProduct = driver.findElement(By.xpath("//div[@class='inventory_item']["+chosenProduct+"]"));
        ChosenProduct = choseProduct.getText();

        //Press button cart
        driver.findElement(shoppingCart).click();


        return this;
    }

    public void doStandartActions() {
        checkMainElements();
        checkCountCards();
        checkAddBtn();
        addAndRemoveElements();
        reverseCards();
        menuLinksAndLogout();
    }

}
