package io.jmix.tests.ui.test.smoke.withdataexport.reports

import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.screen.application.customer.CustomerBrowse
import io.jmix.tests.ui.screen.reports.browser.ReportBrowse
import io.jmix.tests.ui.screen.reports.dialog.*
import io.jmix.tests.ui.screen.reports.editor.ReportEditor
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Conditions.DISABLED
import static io.jmix.masquerade.Selectors.$j

class ReportsSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String CUSTOMERS_TABLE_J_TEST_ID = "customersTable"
    public static final String REGIONS_TABLE_J_TEST_ID = "regionsTable"
    public static final String REPORTS_TABLE_J_TEST_ID = "reportsTable"
    public static final String CUSTOMER_ENTITY_NAME = "Customer"
    public static final String CUSTOMER_ENTITY_FULL_NAME = "Customer (sales_Customer)"
    public static final String CUSTOMER_NAME = "Shelby Robinson"
    public static final String SELECT_BUTTON_J_TEST_ID = "lookupSelectAction"
    public static final String GENERAL_GROUP_NAME = "General"
    public static final String REPORT_CREATING_TYPE_USING_WIZARD = "Using wizard"

    @Test
    @DisplayName("Creates and runs a report")
    void checkReport() {

        def reportBasicName = "Report for entity \"" + CUSTOMER_ENTITY_NAME + "\""
        def reportName = getUniqueName(reportBasicName)

        $j(MainScreen).openReportsBrowse()
        $j(ReportBrowse).with {
            chooseCreatingType(REPORT_CREATING_TYPE_USING_WIZARD)
        }
        $j(ReportCreationDialog).with {
            selectEntity(CUSTOMER_ENTITY_FULL_NAME)
            checkReportName(reportBasicName)
            clickButton(nextBtn)
        }

        def list = ["Name", "Email", "Grade"]
        def str = getString(list)

        $j(ReportSimpleRegionDialog).with {
            chooseAnyElements(list)
            clickButton(ok)
        }

        $j(ReportRegionsDialog).with {
            checkRecordIsDisplayed(str, REGIONS_TABLE_J_TEST_ID)
            clickButton(nextBtn)
        }

        $j(SaveReportDialog).save()

        $j(ReportEditor).with {
            name.setValue(reportName)
            selectValueInComboBox(group, GENERAL_GROUP_NAME)
            clickButton(ok)
        }

        $j(ReportBrowse).with {
            checkRecordIsDisplayed(reportName, REPORTS_TABLE_J_TEST_ID)
            selectRowInTableByText(reportName, REPORTS_TABLE_J_TEST_ID)
            clickButton(run)
        }
        $j(InputParametersDialog).with {
            clickButton(entityPicker)
        }

        $j(CustomerBrowse).with {
            $j(Button, SELECT_BUTTON_J_TEST_ID).shouldBe(DISABLED)
            selectRowInTableByText(CUSTOMER_NAME, CUSTOMERS_TABLE_J_TEST_ID)
            clickButton($j(Button, SELECT_BUTTON_J_TEST_ID))
        }

        $j(InputParametersDialog).with {
            clickButton(printReportButton)
            clickButton(cancelButton)
        }
    }
}
