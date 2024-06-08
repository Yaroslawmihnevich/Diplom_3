package stellarBurgerTests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PersonalAccountTest extends BaseSeleniumTest {

    private final String browserName;

    public PersonalAccountTest(String browserName) {
        this.browserName = browserName;
    }

    @Before
    @Step("Register user")
    public void beforeEach() {
        registerUser(false);
    }

    @After
    @Step("Delete user")
    public void afterEach() {
        deleteUser(false);
    }

    @Parameters(name = "Browser: {0}")
    public static Object[][] browsers() {
        return new Object[][]{
                {"chrome"},
                {"yandex"}
        };
    }

    @Test
    @Description("Проверка перехода по клику на «Личный кабинет».")
    public void testClickPersonalAccountButton() {
        setWebDriver(browserName);

        loadPage(BASE_URL + "/login");
        loginPage.login(email, password);
        mainPage.clickPersonalAccountButton();
        assertEquals(BASE_URL + "/account", getCurrentUrl());
    }

    @Test
    @Description("Переход из личного кабинета в конструктор")
    public void testClickConstructorButton() {
        setWebDriver(browserName);

        loadPage(BASE_URL + "/login");
        loginPage.login(email, password);
        mainPage.clickPersonalAccountButton();
        personalAccountPage.clickConstructorButton();
        assertEquals(BASE_URL + "/", getCurrentUrl());
    }

    @Test
    @Description("Проверка перехода по клику на «Конструктор» и на логотип Stellar Burgers.")
    public void testClickStellarBurgerLogo() {
        setWebDriver(browserName);

        loadPage(BASE_URL + "/login");
        loginPage.login(email, password);
        mainPage.clickPersonalAccountButton();
        personalAccountPage.clickStellarBurgersLogo();
        assertEquals(BASE_URL + "/", getCurrentUrl());
    }
}
