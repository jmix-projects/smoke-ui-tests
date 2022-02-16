package io.jmix.tests.ui.test.smoke.withdataexport.gridexport

import com.codeborne.selenide.Selenide
import io.jmix.tests.ui.screen.application.customer.CustomerBrowse
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class GridExportSmokeUITest extends BaseUiTest implements UiHelper {

    @Test
    @DisplayName("Exports Customers table in XLS and JSON formats")
    void checkExportsScreen() {
        $j(MainScreen).openCustomerBrowse()

        $j(CustomerBrowse).with {
            clickButton(excelExport)
            clickButton(jsonExport)
        }
        Selenide.sleep(1000)
    }
}
