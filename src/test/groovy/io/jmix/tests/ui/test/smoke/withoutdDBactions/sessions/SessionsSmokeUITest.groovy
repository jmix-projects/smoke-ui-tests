package io.jmix.tests.ui.test.smoke.withoutdDBactions.sessions

import io.jmix.tests.ui.screen.administration.session.UserSessionBrowse
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class SessionsSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String ADMIN = "admin"
    public static final String SESSIONS_TABLE_J_TEST_ID = "sessionsTable"

    @Test
    @DisplayName("Checks displaying Sessions screen")
    void checkSessionsTest() {
        $j(MainScreen).openUserSessionBrowse()
        $j(UserSessionBrowse).with {
            checkRecordIsDisplayed(ADMIN, SESSIONS_TABLE_J_TEST_ID)
        }
    }
}
