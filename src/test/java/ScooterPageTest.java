import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;

//Класс для первого тестового сценария
public class ScooterPageTest {
    private final WebDriver driver;
    private static final By DROP_DOWN_BUTTON = By.className("accordion__heading");
    private static final By DROP_LIST_TEXT = By.xpath("//*[@id='accordion__panel-0']");
    //конструктор класса ScooterPageTest
    public ScooterPageTest(WebDriver driver) {
        this.driver = driver;
    }

    //метод для ожидания загрузки страницы
    public void waitForLoading() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //метод кликает по кукам
    public void clickOnCookies() {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }

    //метод скроллит страницу
    public void scrollToDropDownList() {
        WebElement element = driver.findElement(DROP_DOWN_BUTTON);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    //метод кликает по выпадающему списку
    public void clickOnDropDownButton(){
        //метод жмет на кнопку выпадающего списка
        driver.findElement(DROP_DOWN_BUTTON).click();
    }
    //метод достает текст в выпадающем списке
    public String getDropListText() {
      return driver.findElement(DROP_LIST_TEXT).getText();
    }


}


