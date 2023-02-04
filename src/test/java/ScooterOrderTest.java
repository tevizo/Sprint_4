import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class ScooterOrderTest {
    private WebDriver driver;
    private static final By FIRST_ELEMENT_ORDER_CLICK = By.xpath(".//button[@class='Button_Button__ra12g']");
    //локаторы для первого шага
    private static final By NAME_FIELD = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");
    private static final By LAST_NAME_FIELD = By.xpath(".//*[@id='root']/div/div[2]/div[2]/div[2]/input");
    private static final By ADDRESS_FIELD = By.xpath(".//*[@id='root']/div/div[2]/div[2]/div[3]/input");
    private static final By METRO_STATION_FIELD = By.xpath(".//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");
    private static final By PHONE_NUMBER_FIELD = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private static final By NEXT_BUTTON = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //локаторы для второго шага
    private static final By DELIVER_DATE_FIELD = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div/div/input");
    private static final By RENT_DURATION_FIELD = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div/div[2]/span");
    private static final By COLOR_FIELD = By.xpath(".//label/input[@id='black']");
    private static final By MESSAGE_FIELD = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private static final By ORDER_BUTTON = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");
    private static final By APPROVE_ORDER_BUTTON = By.xpath(".//button[text()='Да']");
    //метод для ожидания загрузки страницы
    public void waitForLoading() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    //метод кликает по кукам
    public void clickOnCookies() {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }
    //метод кликает по кнопке Заказать
    public void clickOnFirstOrderButton() {
        driver.findElement(FIRST_ELEMENT_ORDER_CLICK).click();
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
        driver.findElement(By.xpath(".//div[text()='Бульвар Рокоссовского']/parent::button")).click();
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

    //конструктор класса ScooterOrderTest
    public ScooterOrderTest(WebDriver driver) {
        this.driver = driver;
    }
}
