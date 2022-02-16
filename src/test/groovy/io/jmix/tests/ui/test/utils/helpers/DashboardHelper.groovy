package io.jmix.tests.ui.test.utils.helpers

import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.screen.dashboard.DashboardBrowse
import io.jmix.tests.ui.screen.dashboard.DashboardEditor

import static io.jmix.masquerade.Selectors.$j

trait DashboardHelper extends UiHelper {
    public static final String DASHBOARDS_TABLE_J_TEST_ID = "persistentDashboardsTable"

    def createNewDashboard(dashboardCode, dashboardName) {
        $j(DashboardBrowse).with {
            clickButton(createBtn)
        }

        $j(DashboardEditor).with {
            fillTextField(title, dashboardName)
            fillTextField(code, dashboardCode)
            clickButton(ok)
        }
    }

    def selectRowAndClickButton(String name, Button button) {
        $j(DashboardBrowse).with {
            checkRecordIsDisplayed(name, DASHBOARDS_TABLE_J_TEST_ID)
            selectRowInTableByText(name, DASHBOARDS_TABLE_J_TEST_ID)
            clickButton(button)
        }
    }
}