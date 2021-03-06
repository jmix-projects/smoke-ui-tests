package io.jmix.tests.ui.test

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import io.jmix.tests.ui.screen.system.login.LoginScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.openqa.selenium.Dimension

import static io.jmix.masquerade.Selectors.$j

/**
 * Base class for UI tests in ui package
 */
abstract class BaseUiTest {

    /**
     * Login as Administrator
     */
    static void loginAsAdmin() {
        Selenide.open('/')
        $j(LoginScreen).loginAsAdmin()
    }

    /**
     * Login as Administrator with Russian locale
     */
    static void loginAsAdminRus() {
        Selenide.open('/')
        $j(LoginScreen).loginWithLocale('Russian')
    }

    /**
     * Login as Administrator with English locale
     */
    static void loginAsAdminEn() {
        Selenide.open('/')
        $j(LoginScreen).loginWithLocale('English')
    }

    /**
     * Login as custom user
     */
    static void loginAsCustomUser(String login, String password) {
        Selenide.open('/')
        $j(LoginScreen).login(login, password)
    }

    static void maximizeWindowSize() {
        WebDriverRunner.webDriver.manage().window().setSize(new Dimension(1920, 1080))
    }

    /**
     * Login before each class
     */
    @BeforeAll
    static void beforeAll() {
        loginAsAdminEn()
        maximizeWindowSize()
    }

    /**
     * Logout after each class
     */
    @AfterAll
    static void logoutAfterAll() {
        $j(MainScreen).logout()
    }
}
