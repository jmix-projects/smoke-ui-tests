package io.jmix.tests.ui.test.smoke.withoutdDBactions.dynamicattributes

import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class DynamicAttributesSmokeUITest extends BaseUiTest implements UiHelper {

    @Test
    @DisplayName("Checks displaying Dynamic attributes screen")
    void checkDynamicAttributes() {

        $j(MainScreen).openDynamicAttributeBrowse()
        // TODO add some basic actions
    }
}
