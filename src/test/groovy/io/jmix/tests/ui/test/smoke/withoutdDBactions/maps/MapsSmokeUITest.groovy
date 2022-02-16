package io.jmix.tests.ui.test.smoke.withoutdDBactions.maps

import io.jmix.tests.ui.menu.Menus
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static com.codeborne.selenide.Selenide.sleep
import static io.jmix.masquerade.Selectors.$j

class MapsSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String MAP_J_TEST_ID = "map"

    @Test
    @DisplayName("Maps smoke test")
    void checkMaps() {
        def mapScreens = [Menus.GEO_POINT_BROWSE,
                          Menus.GEO_POLYLINE_BROWSE,
                          Menus.GEO_POLYGON_BROWSE,
                          Menus.IMAGE_LAYER_BROWSE,
                          Menus.WMS_LAYER_BROWSE]

        mapScreens.each {
            $j(MainScreen).openGeoBrowse(it)
            checkSelenideElementByJtestId(MAP_J_TEST_ID)
            sleep(500)
            closeTab()
        }
    }
}
