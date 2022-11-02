package io.jmix.tests.ui.test.smoke.crud.dashboards

import io.jmix.tests.ui.screen.dashboard.DashboardBrowse
import io.jmix.tests.ui.screen.dashboard.DashboardEditor
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.DashboardHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class DashboardsSmokeUITest extends BaseUiTest implements DashboardHelper {
    public static final String DASHBOARDS_TABLE_J_TEST_ID = "persistentDashboardsTable"
    public static final String DASHBOARD_BASE_NAME = "Test dashboard "
    public static final String DASHBOARD_BASE_CODE = "testdashboard"

    @BeforeEach
    void openDashboardBrowse() {
        $j(MainScreen).openDashboardBrowse()
    }

    @Test
    @DisplayName("Creates dashboard")
    void createDashboard() {
        def dashboardName = getUniqueName(DASHBOARD_BASE_NAME)
        def dashboardCode = getUniqueName(DASHBOARD_BASE_CODE)

        createNewDashboard(dashboardCode, dashboardName)

        $j(DashboardBrowse).with {
            checkRecordIsDisplayed(dashboardName, DASHBOARDS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Edits dashboard")
    void editDashboard() {
        def dashboardName = getUniqueName(DASHBOARD_BASE_NAME)
        def dashboardEditedName = getUniqueName(DASHBOARD_BASE_NAME)
        def dashboardCode = getUniqueName(DASHBOARD_BASE_CODE)

        createNewDashboard(dashboardCode, dashboardName)

        selectRowAndClickButton(dashboardName, $j(DashboardBrowse).editBtn)

        $j(DashboardEditor).with {
            fillTextField(title, dashboardEditedName)
            clickButton(ok)
        }

        $j(DashboardBrowse).with {
            checkRecordIsDisplayed(dashboardEditedName, DASHBOARDS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Removes dashboard")
    void removeDashboard() {
        def dashboardName = getUniqueName(DASHBOARD_BASE_NAME)
        def dashboardCode = getUniqueName(DASHBOARD_BASE_CODE)

        createNewDashboard(dashboardCode, dashboardName)

        selectRowAndClickButton(dashboardName, $j(DashboardBrowse).removeBtn)

        clickYesInAConfirmationDialog()
        $j(DashboardBrowse).with {
            checkRecordIsNotDisplayed(dashboardName, DASHBOARDS_TABLE_J_TEST_ID)
        }
    }

}
