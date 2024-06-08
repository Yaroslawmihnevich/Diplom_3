package stellarBurgerTests;

import io.qameta.allure.Description;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseSeleniumTest {

    private final String browserName;

    public ConstructorTest(String browserName) {
        this.browserName = browserName;
    }

    @Parameters(name = "Browser: {0}")
    public static Object[][] browsers() {
        return new Object[][]{
                {"chrome"},
                {"yandex"}
        };
    }

    @Test
    @Description("Проверка, что работает переход к разделу «Булки».")
    public void testClickBunTab() {
        setWebDriver(browserName);

        loadPage(BASE_URL);
        mainPage.clickIngredientsTab();
        mainPage.clickBunTab();
        assertTrue(mainPage.getBunTabClassValue().contains("current"));
    }

    @Test
    @Description("Проверка, что работает переход к разделу «Соусы».")
    public void testClickSauceTab() {
        setWebDriver(browserName);

        loadPage(BASE_URL);
        mainPage.clickSaucesTab();
        assertTrue(mainPage.getSauceTabClassValue().contains("current"));
    }

    @Test
    @Description("Проверка, что работает переход к разделу «Начинки».")
    public void testClickIngredientsTab() {
        setWebDriver(browserName);

        loadPage(BASE_URL);
        mainPage.clickIngredientsTab();
        assertTrue(mainPage.getIngredientsTabClassValue().contains("current"));
    }
}
