package stellarBurgerTests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class RegisterTest extends BaseSeleniumTest {

    private final String browserName;

    public RegisterTest(String browserName) {
        this.browserName = browserName;
    }

    @Before
    @Step("Delete user")
    public void beforeEach() {
        deleteUser();
    }

    @Parameters(name = "Browser: {0}")
    public static Object[][] browsers() {
        return new Object[][]{
                {"chrome"},
                {"yandex"}
        };
    }

    @Test
    @Description("Проверка успешной регистрации.")
    public void testRegistration() {
        setWebDriver(browserName);

        loadPage(BASE_URL);
        mainPage.clickEnterToAccountButton();
        loginPage.clickRegistrationButton();
        registrationPage.registration(name, email, password);
        loginPage.login(email, password);
        mainPage.clickPersonalAccountButton();
        var accountName = personalAccountPage.getNameFieldValue();
        var accountEmail = personalAccountPage.getEmailFieldValue();
        personalAccountPage.clickLogoutButton();

        assertEquals(name, accountName);
        assertEquals(email.toLowerCase(), accountEmail);
    }

    @Test
    @Description("Проверка на ошибку для некорректного пароля. Минимальный пароль — шесть символов.")
    public void testVisibilityErrorOfShortPassword() {
        setWebDriver(browserName);

        loadPage(BASE_URL);
        mainPage.clickEnterToAccountButton();
        loginPage.clickRegistrationButton();
        assertNotNull(registrationPage.waitPasswordErrorVisible("123"));
    }
}
