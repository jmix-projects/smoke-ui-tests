package io.jmix.tests.ui.test.smoke.withdataexport.pivottable

import com.codeborne.selenide.Selenide
import io.jmix.tests.ui.screen.addonscreen.PivotTableScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class PivotTableSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String PIVOT_TABLE_J_TEST_ID = "pivotTable"

    @Test
    @DisplayName("Checks screen with pivot table and exports table in XLS format")
    void checkPivotTable() {
        $j(MainScreen).openPivotTableScreen()
        checkSelenideElementByJtestId(PIVOT_TABLE_J_TEST_ID)
        $j(PivotTableScreen).with {
            clickButton(xlsExport)
        }
        Selenide.sleep(1000)
    }
}
