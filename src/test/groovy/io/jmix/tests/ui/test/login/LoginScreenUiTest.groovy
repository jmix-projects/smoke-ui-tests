package io.jmix.tests.ui.test.login

import com.codeborne.selenide.Selenide
import io.jmix.masquerade.component.Notification
import io.jmix.tests.ui.screen.system.login.LoginScreen
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Conditions.caption
import static io.jmix.masquerade.Selectors.$j

class LoginScreenUiTest {

    @Test
    @DisplayName("Checks that the user cannot login with incorrect credentials")
    void loginWithIncorrectCredentials() {
        Selenide.open('http://localhost:8080')
        $j(LoginScreen).login('username', 'password')

        $j(Notification.class)
                .shouldHave(caption('Login failed'))
                .clickToClose()
    }
}
