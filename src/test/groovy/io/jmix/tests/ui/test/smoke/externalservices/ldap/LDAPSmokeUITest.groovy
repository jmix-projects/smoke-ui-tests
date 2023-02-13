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

    boolean isLocalServer = false

    // LDAP for external server settings by default (isLocalServer = false)
    public static final String LDAP_USER_LOGIN = "euclid"
    public static final String LDAP_USER_PASSWORD = "password"

    // LDAP for local server settings (isLocalServer = true)
    public static final String LDAP_USER_LOGIN_LOCAL = "ldapuser"
    public static final String LDAP_USER_PASSWORD_LOCAL = "11passwd11"


    @Test
    @DisplayName("Logins as LDAP user")
    void checkLDAP() {
        $j(MainScreen).logout()
        if (isLocalServer) {
            loginAsCustomUser(LDAP_USER_LOGIN_LOCAL, LDAP_USER_PASSWORD_LOCAL)
        } else {
            loginAsCustomUser(LDAP_USER_LOGIN, LDAP_USER_PASSWORD)
        }
        $j(Label, APP_TITLE_LABEL_J_TEST_ID)
                .shouldBe(VISIBLE)
                .shouldHave(value(APPLICATION_NAME))
    }
}
