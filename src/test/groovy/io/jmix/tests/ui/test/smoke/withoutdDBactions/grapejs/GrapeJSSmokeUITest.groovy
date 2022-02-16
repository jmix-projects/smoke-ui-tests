package io.jmix.tests.ui.test.smoke.withoutdDBactions.grapejs

import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class GrapeJSSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String HTML_EDITOR_J_TEST_ID = "htmlEditor"

    @Test
    @DisplayName("Checks displaying GrapeJS screen")
    void checkGrapeJS() {
        $j(MainScreen).openGrapeJSScreen()
        checkSelenideElementByJtestId(HTML_EDITOR_J_TEST_ID)
    }
}
