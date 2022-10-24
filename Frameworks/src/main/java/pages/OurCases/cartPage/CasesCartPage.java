package pages.OurCases.cartPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.OurCases.listing.CasesListingPage;
import pages.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CasesCartPage extends BasePage {
    public CasesCartPage(WebDriver driver) {super(driver);}

    private final By cartItem = By.xpath("//div[@class='cart_item_label']");
    private final By cartTitle = By.xpath("//span[text()='Your Cart']");
    private final By cartTableLabel = By.xpath("//div[text()='QTY']");
    private final By cartDescLabel = By.xpath("//div[text()='DESCRIPTION']");
    private final By removeButton = By.xpath("//button[text()='Remove']");
    private final By logOutBtn = By.xpath("//a[@id='logout_sidebar_link']");
    private final String[] nameLabels = {
            "YOUR CART",
            "QTY",
            "DESCRIPTION"};

    //Step 5
    public CasesCartPage checkMainElements() {
        /**
         * Check main elements of a cart page
         */
        checkElementIsExist(cartTitle, nameLabels[0]);
        checkElementIsExist(cartTableLabel, nameLabels[1]);
        checkElementIsExist(cartDescLabel, nameLabels[2]);

        //Check our product description
        Assert.assertEquals(driver.findElement(cartItem).getText(), ChosenProduct);

        return this;
    }


    //Step 6
    public CasesCartPage removeProduct() {
        List<WebElement> product = (List<WebElement>)driver.findElements(cartItem);

        //Removing product and checking what it is will be deleted
        driver.findElement(removeButton).click();
        // If == [](nothing) - all OK, the element is deleted
        Assert.assertNotEquals(driver.findElements(cartItem), product);

        return this;
    }
}
