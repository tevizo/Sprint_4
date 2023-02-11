import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import static org.hamcrest.CoreMatchers.containsString;


public class ScooterOrderTest {
    private WebDriver driver;
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private static final String EXPECTED_CONFIRMATION_TEXT = "Заказ оформлен";

    @Before
    public void setUp() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
    }

//Тестовый сценарий по оформлению заказа
    @Test
    public void firstButtonScooterOrderFlow(){
        driver.get(PAGE_URL);
        //Объект класса главной страницы ScooterOrderTest
        MainPage mainPageOrderTest = new MainPage(driver);

        mainPageOrderTest.waitForLoading();//ждем загрузки страницы
        mainPageOrderTest.clickOnCookies();//клик по кукам
        mainPageOrderTest.clickOnFirstOrderButton();//кликаем по кнопке Заказать
        //вызов  метода, заполняющего первую страницу заказа
        mainPageOrderTest.orderFirstPage("Айдын", "Байдаров", "Ул. Черная, д. 5", "Бульвар Рокоссовского","+79783453275");

        //вызов  метода, заполняющего вторую страницу заказа
        mainPageOrderTest.orderSecondPage("23.02.2023", "Подъезд со стороны двора");
        String actualConfirmationText = mainPageOrderTest.getConfirmationText();
        //проверка подтверждения заказа
        Assert.assertThat(actualConfirmationText, containsString(EXPECTED_CONFIRMATION_TEXT));
    }
    @Test
    public void secondButtonScooterOrderFlow(){
        driver.get(PAGE_URL);
        //Объект класса главной страницы ScooterOrderTest
        MainPage mainPageOrderTest = new MainPage(driver);

        mainPageOrderTest.waitForLoading();//ждем загрузки страницы
        mainPageOrderTest.clickOnCookies();//клик по кукам
        mainPageOrderTest.clickOnSecondOrderButton();//кликаем по кнопке Заказать
        //вызов  метода, заполняющего первую страницу заказа
        mainPageOrderTest.orderFirstPage("Артем", "Валуев", "Ул. Валуева, д. 5", "Римская","89783453275");

        //вызов  метода, заполняющего вторую страницу заказа
        mainPageOrderTest.orderSecondPage("23.07.2073", "Подъезд есть");
        String actualConfirmationText = mainPageOrderTest.getConfirmationText();
        //проверка подтверждения заказа
        Assert.assertThat(actualConfirmationText, containsString(EXPECTED_CONFIRMATION_TEXT));
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
