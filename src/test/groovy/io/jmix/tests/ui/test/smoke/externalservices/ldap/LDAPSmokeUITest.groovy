package io.jmix.tests.ui.test.smoke.externalservices.ldap

import io.jmix.masquerade.component.Label
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Conditions.VISIBLE
import static io.jmix.masquerade.Conditions.value
import static io.jmix.masquerade.Selectors.$j

class LDAPSmokeUITest extends BaseUiTest implements UiHelper {

    public static final String APPLICATION_NAME = "Sample Sales"
    public static final String APP_TITLE_LABEL_J_TEST_ID = "appTitleLabel"

    public static final String LDAP_USER_LOGIN = "login"
    public static final String LDAP_USER_PASSWORD = "password"

    @Test
    @DisplayName("Logins as LDAP user")
    void checkLDAP() {
        $j(MainScreen).logout()
        loginAsCustomUser(LDAP_USER_LOGIN, LDAP_USER_PASSWORD)
        $j(Label, APP_TITLE_LABEL_J_TEST_ID)
                .shouldBe(VISIBLE)
                .shouldHave(value(APPLICATION_NAME))
    }
}
