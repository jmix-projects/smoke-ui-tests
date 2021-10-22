package io.jmix.tests.ui.screen.system.login

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.*

class LoginScreen extends Composite<LoginScreen> {

    @Wire
    TextField usernameField

    @Wire
    PasswordField passwordField

    @Wire
    Button loginButton

    @Wire
    ComboBox localesField

    @Wire
    CheckBox rememberMeCheckBox

    void loginAsAdmin() {
        submit()
    }

    void login(String username, String password) {
        usernameField.value = username
        passwordField.value = password
        submit()
    }

    void submit() {
        loginButton.click()
    }

    void loginWithLocale(String locale) {
        localesField
                .openOptionsPopup()
                .select(locale)
        submit()
    }
}
