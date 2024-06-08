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
public class LogoutTest extends BaseSeleniumTest {

    private final String browserName;

    public LogoutTest(String browserName) {
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
    @Description("Проверка выхода по кнопке «Выйти» в личном кабинете.")
    public void testClickLogoutButton() throws Exception {
        setWebDriver(browserName);

        loadPage(BASE_URL + "/login");
        loginPage.login(email, password);
        mainPage.clickPersonalAccountButton();
        personalAccountPage.clickLogoutButton();
        Thread.sleep(1000);
        assertEquals(BASE_URL + "/login", getCurrentUrl());
    }
}
