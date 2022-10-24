package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static constants.Constant.timeoutVariables.EXPLICIT_WAIT;

public class BasePage {
    // экземпляр драйвера
    protected WebDriver driver;

    //конструктор
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //Переменные - эл-ты страницы
    private final By login = By.xpath("//input[@placeholder='Username']");
    private final By pass = By.xpath("//input[@placeholder='Password']");
    private final By loginBtn = By.xpath("//input[@value='Login']");

    public void checkLogsElements() {
        int checkExist = driver.findElements(login).size();
        checkExist = driver.findElements(pass).size();
        checkExist = driver.findElements(loginBtn).size();
    }

    public void open(String url) {
        driver.get(url);
    }

    public WebElement waitElementIsVisible(WebElement element){
        new WebDriverWait(driver, EXPLICIT_WAIT).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    /**
     * any int number - alphabetical sort
     * chose == 1  - reverse sort cards
     */
    public void compareCards(int chose){
        final By card = By.xpath("//div[@class='inventory_item']");

        ArrayList<WebElement> listCards = (ArrayList<WebElement>)driver.findElements(card);
        ArrayList<String> obtainedList = new ArrayList<>();
        ArrayList<String> sortedList = new ArrayList<>();
        for(WebElement we:listCards){
            obtainedList.add(we.getAttribute("textContent"));
        }
        for(String s:obtainedList){
            sortedList.add(s);
        }

        Collections.sort(sortedList);
        //Sorting
        if(chose == 1)
            Collections.reverse(sortedList);

        Assert.assertTrue(sortedList.equals(obtainedList));
    }

    public void addAndRemoveElements(List<WebElement> listBtns) {
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
    }

}


