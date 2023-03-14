import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;


public class MainPage {
    private WebDriver driver;
    private static final By FIRST_ELEMENT_ORDER_CLICK = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    private static final By SECOND_ELEMENT_ORDER_CLICK = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    //локаторы для первого шага
    private static final By NAME_FIELD = By.xpath(".//input[@placeholder='* Имя']");
    private static final By LAST_NAME_FIELD = By.xpath(".//input[@placeholder='* Фамилия']");
    private static final By ADDRESS_FIELD = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private static final By METRO_STATION_FIELD = By.xpath(".//input[@placeholder='* Станция метро']");
    private static final By PHONE_NUMBER_FIELD = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private static final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");

    //локаторы для второго шага
    private static final By DELIVER_DATE_FIELD = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static final By RENT_DURATION_FIELD = By.xpath(".//div[@class='Dropdown-control']/div/span");
    private static final By COLOR_FIELD = By.xpath(".//label/input[@id='black']");
    private static final By MESSAGE_FIELD = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private static final By ORDER_BUTTON = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private static final By APPROVE_ORDER_BUTTON = By.xpath(".//button[text()='Да']");
    //метод для ожидания загрузки страницы
    public void waitForLoading() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    //метод кликает по кукам
    public void clickOnCookies() {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }
    //метод кликает по первой кнопке Заказать
    public void clickOnFirstOrderButton() {
        driver.findElement(FIRST_ELEMENT_ORDER_CLICK).click();
    }
    //метод кликает по второй кнопке Заказать
    public void clickOnSecondOrderButton() {
        WebElement element = driver.findElement(SECOND_ELEMENT_ORDER_CLICK);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(SECOND_ELEMENT_ORDER_CLICK));
        driver.findElement(SECOND_ELEMENT_ORDER_CLICK).click();
    }
    //ПЕРВЫЙ ШАГ
    //метод заполняет поле Имя
    public void setName(String userName) {
        driver.findElement(NAME_FIELD).sendKeys(userName);
    }
    //метод заполняет поле Фамилия
    public void setLastName(String userLastName) {
        driver.findElement(LAST_NAME_FIELD).sendKeys(userLastName);
    }
    //метод заполняет поле Адрес
    public void setAddress(String address) {
        driver.findElement(ADDRESS_FIELD).sendKeys(address);
    }
    //метод заполняет поле Станция метро
    public void setMetroStation(String metroStation) {
        driver.findElement(METRO_STATION_FIELD).sendKeys(metroStation);
        driver.findElement(By.xpath(".//div[@class='select-search__select']/ul/li/button")).click();
    }
    //метод заполняет поле Номер телефона
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(PHONE_NUMBER_FIELD).sendKeys(phoneNumber);
    }
    //метод кликает на Далее
    public void clickOnNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }
    //метод первого шага
    public void orderFirstPage(String userName, String userLastName, String address, String metroStation, String phoneNumber){
        setName(userName);
        setLastName(userLastName);
        setAddress(address);
        setMetroStation(metroStation);
        setPhoneNumber(phoneNumber);
        clickOnNextButton();
    }

    //ВТОРОЙ ШАГ
    //метод заполняет поле Дата доставки
    public void setDeliverDate(String deliverDate) {
        driver.findElement(DELIVER_DATE_FIELD).sendKeys(deliverDate);
    }
    //метод заполняет поле Срок аренды
    public void setRentDuration() {
        driver.findElement(RENT_DURATION_FIELD).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-option'][1]")).click();

    }
    //метод выбирает цвет самоката
    public void setScooterColor() {
        driver.findElement(COLOR_FIELD).click();
    }
    //метод заполняет поле Комментарий для курьера
    public void setMessage(String message) {
        driver.findElement(MESSAGE_FIELD).sendKeys(message);
    }
    //метод кликает на Заказать
    public void clickOnOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(ORDER_BUTTON));
        driver.findElement(ORDER_BUTTON).click();
    }
    //метод находит модальное окно и подтверждает заказ
    public void approveOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']")));
        driver.findElement(APPROVE_ORDER_BUTTON).click();
    }
    //метод достает текст после подтверждения заказа
    public String getConfirmationText() {
       return driver.findElement(By.xpath(".//div[text()='Заказ оформлен']")).getText();
    }
    //метод второго шага
    public void orderSecondPage(String deliverDate, String message) {
        setDeliverDate(deliverDate);
        setRentDuration();
        setScooterColor();
        setMessage(message);
        clickOnOrderButton();
        approveOrder();
    }

    //конструктор класса MainPage
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    //метод возвращает текст из попапа
    public String getTextFromDropList(String newDropButton, String newDropText){
        //скроллим до элемента
        WebElement element = driver.findElement(By.xpath(newDropButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        //ждем кликабельности
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(newDropButton)));
        //жмем на кнопку выпадающего списка
        driver.findElement(By.xpath(newDropButton)).click();
        //помещаем логику в переменную, которая получит текст и сравнит его с ожидаемым результатом
        String testActualText = driver.findElement(By.xpath(newDropText)).getText();
       return testActualText;

    }
}
