package pages.OurCases.listing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.base.BasePage;

import java.util.List;

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
     * Step 2
     */
    public CasesListingPage checkMainElements() {
        //Check main elements on the listing page
        int countTitle = driver.findElements(title).size();
        int countShoppingCart = driver.findElements(shoppingCart).size();
        int countSortContainer = driver.findElements(sortContainer).size();

        return this;
    }

    public void checkCountCards() {
        //Check count of products on the page
        int countCard = driver.findElements(card).size();
        Assert.assertEquals(countCard, 6);

        compareCards(2);

    }

    /**
     * ШАГ 3
     */
    public void checkAddBtn() {
        List<WebElement> listBtns = driver.findElements(addBtn);

        //Check button "Add to Cart" is active?
        for (WebElement listBtn : listBtns) {
            Boolean activeBtn = Boolean.valueOf(listBtn.getAttribute("disabled"));
            Assert.assertEquals(false, activeBtn);
        }

    }

    /**
     * ШАГ 4 и 5
     * We need to add, check it, and after remove all elements from a crt
     * Click all "Add to Cart" buttons
     * Fix it
     * Click all "Remove" buttons
     * Fix it
     */
    public void addAndRemoveElements() {
        List<WebElement> listBtns = driver.findElements(addBtn);

        //Add all elements to a cart
        for (WebElement listBtn : listBtns)
            listBtn.click();
        By countAddToCart = By.xpath("//a[@class='shopping_cart_link']");

        //Check count of our elements
        int countAdd = Integer.parseInt(driver.findElement(countAddToCart).getAttribute("innerText"));
        Assert.assertEquals(countAdd, 6);

        //remove elements from cart
        listBtns = driver.findElements(By.xpath
                ("//button[text()='Remove']"));
        for (WebElement listBtn : listBtns)
            listBtn.click();

        //Check that all elements was removed
        countAddToCart = By.xpath("//a[@class='shopping_cart_link']");
        String CountAdd = driver.findElement(countAddToCart).getAttribute("innerText");
        Assert.assertEquals(CountAdd, "");

    }



    /**
     * ШАГ 6
     * We need sort reverse our products
     */
    public void reverseCards() {
        driver.findElement(sortContainer).click();
        WebElement selectOption = driver.findElement(reverseOption);
        waitElementIsVisible(selectOption).click();

        compareCards(1);

    }

    /**
     * ШАГ 7
     * We need click to the menu button and
     * check all elements and
     * log out from the site
     */
    public void menuLinksAndLogout() {
        driver.findElement(menuBtn).click();
        WebElement selectOption = driver.findElement(logOutBtn);
        waitElementIsVisible(selectOption);

        List<WebElement> listLinks = driver.findElements(menuLinks);
        for (int i = 0;i<listLinks.size();i++)
        {
            //If not equal - not all elements is ok
            if (!nameLinks[i].equals(listLinks.get(i).getText()))
                Assert.fail();
        }

        //ШАГ 8 - LOGOUT
        selectOption.click();
        checkLogsElements();

    }

    /**
     * This method needs for collect all standart method in one
     */
    public void doStandartActions() {
        checkMainElements();
        checkCountCards();
        checkAddBtn();
        addAndRemoveElements();
        reverseCards();
        menuLinksAndLogout();
    }

    //This method for the last test-case "RemoveFromCartPageTest"
    public void addAnyProduct() {
        List<WebElement> listBtns = driver.findElements(By.xpath
                ("//button[text()='Add to cart']"));

        // Chose random product from list
        int chosenProduct = (int) (Math.random()*6);
        listBtns.get(chosenProduct).click();
        // check icon near a cart == 1
        int countAdd = Integer.parseInt(driver.findElement(shoppingCart).getAttribute("innerText"));
        Assert.assertEquals(countAdd, 1);

        //Checking the button has label "REMOVE"
        chosenProduct+=1;
        By productPath = By.xpath("//div[@class='inventory_item']["+chosenProduct+"]//button[text()='Remove']");
        Assert.assertEquals(driver.findElement(productPath).getText(), "REMOVE");

        //  * Our data about chosen product,
        //  * we need to save this data to compare it next step
        WebElement choseProduct = driver.findElement(By.xpath("//div[@class='inventory_item']["+chosenProduct+"]"));
        ChosenProduct = choseProduct.getText();

        //Press button cart
        driver.findElement(shoppingCart).click();

    }

}
