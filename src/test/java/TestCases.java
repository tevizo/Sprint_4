import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

public class TestCases {
    private WebDriver driver;
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String EXPECTED_DROP_TEXT = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String EXPECTED_CONFIRMATION_TEXT = "Заказ оформлен";

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
    }
//Тестовый сценарий по выпадающему списку
    @Test
    public void test() {
        driver.get(PAGE_URL);
        //Объект класса главной страницы ScooterPageTest
        ScooterPageTest scooterPageTest = new ScooterPageTest(driver);

        scooterPageTest.waitForLoading();//ждем загрузки страницы
        scooterPageTest.clickOnCookies(); //клик по кукам
        scooterPageTest.scrollToDropDownList();//скроллим до выпадающего списка и кликаем по нему
        scooterPageTest.clickOnDropDownButton();//кликаем на выпадающий список
        scooterPageTest.waitForLoading();//ждем появления списка
        String actualText = scooterPageTest.getDropListText();//получаем текст в выпадающем списке
        assertEquals("Текст не совпадает", EXPECTED_DROP_TEXT, actualText);//проверяем текст
    }

//Тестовый сценарий по оформлению заказа
    @Test
    public void test2(){
        driver.get(PAGE_URL);
        //Объект класса главной страницы ScooterOrderTest
        ScooterOrderTest scooterOrderTest = new ScooterOrderTest(driver);

        scooterOrderTest.waitForLoading();//ждем загрузки страницы
        scooterOrderTest.clickOnCookies();//клик по кукам
        scooterOrderTest.clickOnFirstOrderButton();//кликаем по кнопке Заказать
        //вызов  метода, заполняющего первую страницу заказа
        scooterOrderTest.orderFirstPage("Айдын", "Байдаров", "Ул. Черная, д. 5", "Бульвар Рокоссовского","+79783453275");

        //вызов  метода, заполняющего вторую страницу заказа
        scooterOrderTest.orderSecondPage("23.02.2023", "Подъезд со стороны двора");
        String actualConfirmationText = scooterOrderTest.getConfirmationText();
        //проверка подтверждения заказа
        Assert.assertThat(actualConfirmationText, containsString(EXPECTED_CONFIRMATION_TEXT));
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
