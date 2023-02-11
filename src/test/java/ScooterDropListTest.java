import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class ScooterDropListTest {
    private WebDriver driver;
    private static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";

    private static final String expectedDropText1 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String expectedDropText2 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    private static final String expectedDropText3 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    private static final String expectedDropText4 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    private static final String expectedDropText5 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    private static final String expectedDropText6 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    private static final String expectedDropText7 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    private static final String expectedDropText8 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

    private final String popupButton;
    private final String popupText;
    private final String expectedText;

    public ScooterDropListTest(String popupButton, String popupText, String expectedText){
        this.popupButton = popupButton;
        this.popupText = popupText;
        this.expectedText = expectedText;
    }
    @Parameterized.Parameters
    public static Object[][] getTextsFromLists() {
        return new Object[][] {
                { ".//div[@id='accordion__heading-0']", "//*[@id='accordion__panel-0']", expectedDropText1},
                { ".//div[@id='accordion__heading-1']", "//*[@id='accordion__panel-1']", expectedDropText2},
                { ".//div[@id='accordion__heading-2']", "//*[@id='accordion__panel-2']", expectedDropText3},
                { ".//div[@id='accordion__heading-3']", "//*[@id='accordion__panel-3']", expectedDropText4},
                { ".//div[@id='accordion__heading-4']", "//*[@id='accordion__panel-4']", expectedDropText5},
                { ".//div[@id='accordion__heading-5']", "//*[@id='accordion__panel-5']", expectedDropText6},
                { ".//div[@id='accordion__heading-6']", "//*[@id='accordion__panel-6']", expectedDropText7},
                { ".//div[@id='accordion__heading-7']", "//*[@id='accordion__panel-7']", expectedDropText8},
        };
        }
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
    }
    @Test
    public void checkDropListTexts(){
        driver.get(PAGE_URL);
        //Объект класса MainPage
        MainPage mainDropListPage = new MainPage(driver);
        mainDropListPage.waitForLoading();//ждем загрузки страницы
        mainDropListPage.clickOnCookies(); //клик по кукам
        String actualDropListText = mainDropListPage.getTextFromDropList(popupButton, popupText, expectedText);
        Assert.assertEquals(expectedText, actualDropListText);//проверка соответствия текстов

    }
    @After
    public void tearDown() {
        driver.quit();
    }
}

