package io.jmix.tests.ui.test.smoke


import com.codeborne.selenide.Selenide
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.Label
import io.jmix.masquerade.component.SideMenu
import io.jmix.tests.ui.menu.Menus
import io.jmix.tests.ui.screen.addonscreen.EmailSendingScreen
import io.jmix.tests.ui.screen.addonscreen.PivotTableScreen
import io.jmix.tests.ui.screen.addonscreen.RESTScreen
import io.jmix.tests.ui.screen.addonscreen.search.SearchPersonBrowse
import io.jmix.tests.ui.screen.addonscreen.search.SearchPersonEditor
import io.jmix.tests.ui.screen.administration.audit.EntityLogBrowse
import io.jmix.tests.ui.screen.administration.datatools.EntityInspectorBrowse
import io.jmix.tests.ui.screen.administration.emailhistory.EmailHistoryScreen
import io.jmix.tests.ui.screen.administration.emailtemplates.EmailTemplateBrowse
import io.jmix.tests.ui.screen.administration.emailtemplates.EmailTemplateEditor
import io.jmix.tests.ui.screen.administration.jmx.BeanInspectScreen
import io.jmix.tests.ui.screen.administration.jmx.JMXConsoleScreen
import io.jmix.tests.ui.screen.administration.jmx.OperationResultDialog
import io.jmix.tests.ui.screen.administration.session.UserSessionBrowse
import io.jmix.tests.ui.screen.administration.tenants.TenantBrowse
import io.jmix.tests.ui.screen.administration.tenants.TenantEditor
import io.jmix.tests.ui.screen.application.customer.CustomerBrowse
import io.jmix.tests.ui.screen.application.customer.CustomerEditor
import io.jmix.tests.ui.screen.bpm.ModelerScreen
import io.jmix.tests.ui.screen.bpm.ProcessDefinitionBrowse
import io.jmix.tests.ui.screen.charts.ChartScreen
import io.jmix.tests.ui.screen.dashboard.DashboardBrowse
import io.jmix.tests.ui.screen.dashboard.DashboardEditor
import io.jmix.tests.ui.screen.imap.ImapConfigurationBrowse
import io.jmix.tests.ui.screen.imap.ImapConfigurationEditor
import io.jmix.tests.ui.screen.reports.browser.ReportBrowse
import io.jmix.tests.ui.screen.reports.dialog.*
import io.jmix.tests.ui.screen.reports.editor.ReportEditor
import io.jmix.tests.ui.screen.system.dialog.ConfirmationDialog
import io.jmix.tests.ui.screen.system.dialog.UnsavedChangesBPMDialog
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.UiHelper
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpUriRequest
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.util.EntityUtils
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static com.codeborne.selenide.Selenide.sleep
import static io.jmix.masquerade.Conditions.*
import static io.jmix.masquerade.Selectors.$j

class SmokeUITest extends BaseUiTest implements UiHelper {
    public static final String CUSTOMERS_TABLE_J_TEST_ID = "customersTable"
    public static final String SEARCH_PERSON_TABLE_J_TEST_ID = "searchPersonsTable"
    public static final String M_BEANS_TABLE_J_TEST_ID = "mbeansTable"
    public static final String APP_TITLE_LABEL_J_TEST_ID = "appTitleLabel"
    public static final String SENDING_MESSAGE_TABLE_J_TEST_ID = "sendingMessageTable"
    public static final String TENANTS_TABLE_J_TEST_ID = "tenantsTable"
    public static final String DASHBOARDS_TABLE_J_TEST_ID = "persistentDashboardsTable"
    public static final String IMAP_CONFIG_TABLE_J_TEST_ID = "mailBoxesTable"
    public static final String EMAIL_TEMPLATES_TABLE_J_TEST_ID = "emailTemplatesTable"
    public static final String SALES_USER_TABLE_J_TEST_ID = "sales_UserTable"
    public static final String SESSIONS_TABLE_J_TEST_ID = "sessionsTable"
    public static final String ENTITY_LOG_TABLE_J_TEST_ID = "loggedEntityTable"
    public static final String PIVOT_TABLE_J_TEST_ID = "pivotTable"
    public static final String REGIONS_TABLE_J_TEST_ID = "regionsTable"
    public static final String REPORTS_TABLE_J_TEST_ID = "reportsTable"

    public static final String APPLICATION_NAME = "Sample Sales"
    public static final String ADMIN = "admin"

    public static final String LDAP_USER_LOGIN = "username"
    public static final String LDAP_USER_PASSWORD = "password"

    public static final String REQUEST_URL = "http://localhost:8080/oauth/token?grant_type=password&username=admin&password=admin"
    public static final String AUTHORIZATION_HEADER_NAME = "Authorization"
    public static final String CONTENT_TYPE_HEADER_NAME = "Content-Type"
    public static final String AUTHORIZATION_HEADER_VALUE = "Basic Y2xpZW50OnNlY3JldA=="
    public static final String CONTENT_TYPE_HEADER_VALUE = "application/x-www-form-urlencoded"

    public static final String SEARCH_PERSON = "searchperson "
    public static final String BEAN_NAME = "jmix.search:type=EntityIndexing"

    public static final String IMAP_CONFIGURATION_NAME = "Haulmont test"
    public static final String IMAP_HOST = "host"
    public static final String IMAP_PORT = "port"
    public static final String IMAP_USERNAME = "username@haulmont.dev"
    public static final String IMAP_PASSWORD = "password"

    public static final String PROCESS_ID = "process"
    public static final String PROCESS_NAME = "Process"

    public static final String PROCESS_DEPLOYED_NOTIFICATION_TEXT = "Process deployed"
    public static final String IMAP_SUCCESS_CONNECT_NOTIFICATION_TEXT = "Connection is successfully established"

    public static final String USER_ENTITY_NAME = "User"
    public static final String USER_ENTITY_FULL_STRING = "User (sales_User)"
    public static final String CUSTOMER_ENTITY_NAME = "Customer"
    public static final String CUSTOMER_ENTITY_FULL_NAME = "Customer (sales_Customer)"
    public static final String CUSTOMER_ENTITY_NAME_WITH_PREFIX = "sales_Customer"
    public static final String CUSTOMER_NAME = "Hildred Ellis"

    public static final String MAP_J_TEST_ID = "map"
    public static final String HTML_EDITOR_J_TEST_ID = "htmlEditor"

    public static final String PIE3D_CHART_J_TEST_ID = "pie3dChart"
    public static final String SERIAL_CHART_J_TEST_ID = "lineChart"
    public static final String FUNNEL_CHART_J_TEST_ID = "funnel"
    public static final String ANGULAR_GAUGE_CHART_J_TEST_ID = "gaugeChart"
    public static final String RADAR_CHART_J_TEST_ID = "radarChart"
    public static final String STOCK_CHART_J_TEST_ID = "stockChart"
    public static final String GANTT_CHART_J_TEST_ID = "ganttChart"
    public static final String DATABINDING_CHART_J_TEST_ID = "pieChart"
    public static final String CHART_J_TEST_ID = "chart"
    public static final String GAUGE_CHART_J_TEST_ID = "gauge"
    public static final String INCREMENTAL_CHART_J_TEST_ID = "orderHistoryChart"
    public static final String DIAGRAM_FROM_JSON_CHART_J_TEST_ID = "serialChart"

    public static final String SELECT_BUTTON_J_TEST_ID = "lookupSelectAction"
    public static final String GENERAL_GROUP_NAME = "General"
    public static final String SHOW_ALL_RECORDS_MODE = "Show all records"
    public static final String REPORT_CREATING_TYPE_USING_WIZARD = "Using wizard"
    public static final String SENT_STATUS = "Sent"

    @Test
    @DisplayName("Smoke test")
    void smokeTest() {
        loginAsAdmin()
        maximizeWindowSize()

        checkCRUDActions()
        checkEmailSendingScreen()
        checkBPM()
        checkCharts()
        checkMaps()
        checkReport()
        checkAudit()
        checkSessions()
        checkDynamicAttributes()
        checkDatatools()
        checkEmailTemplates()
        checkDashboard()
        checkGrapeJS()
        checkPivotTable()
        checkExportsScreen()
        checkMultitenancy()
        checkLDAP()
        checkImap()
        checkREST()
        checkSearch()
        checkTranslations()

    }

    static void checkTranslations() {
        $j(MainScreen).logout()
        loginAsAdminRus()
        $j(MainScreen).openReportsBrowse()
        Selenide.sleep(2000)
        closeTab()
        $j(MainScreen).openResourceRoleBrowse()
        Selenide.sleep(2000)
        closeTab()
        $j(MainScreen).openModelerScreen()
        Selenide.sleep(2000)
        closeTab()
        $j(MainScreen).openImapConfigurationBrowse()
        Selenide.sleep(2000)
        closeTab()
        $j(MainScreen).logout()
        loginAsAdmin()
    }

    static void checkREST() {
        HttpUriRequest request = new HttpPost(REQUEST_URL)
        request.addHeader(AUTHORIZATION_HEADER_NAME, AUTHORIZATION_HEADER_VALUE)
        request.addHeader(CONTENT_TYPE_HEADER_NAME, CONTENT_TYPE_HEADER_VALUE)
        HttpResponse response = HttpClientBuilder.create().build().execute(request)

        HttpEntity httpEntity = response.getEntity()
        String result = EntityUtils.toString(httpEntity)

        int posBeg = result.indexOf(":\"") + 2
        int posEnd = result.indexOf("\",")

        String token = result.substring(posBeg, posEnd)

        $j(MainScreen).openRESTScreen()
        $j(RESTScreen).with {
            restField.setValue(token)
            Selenide.sleep(2000)
            restField.setValue("")
        }
        closeTab()
    }

    static void checkSearch() {
        $j(MainScreen).openSearchPersonBrowse()
        $j(SearchPersonBrowse).with {
            clickButton(createBtn)
        }
        def name1 = getUniqueName(SEARCH_PERSON)
        def descr1 = getUniqueName(SEARCH_PERSON)
        def name2 = getUniqueName(SEARCH_PERSON)
        def descr2 = getUniqueName(SEARCH_PERSON)

        $j(SearchPersonEditor).with {
            fillTextField(nameField, name1)
            fillTextField(descriptionField, descr1)
            clickButton(ok)
        }
        $j(SearchPersonBrowse).with {
            checkRecordIsDisplayed(name1, SEARCH_PERSON_TABLE_J_TEST_ID)
            clickButton(createBtn)
        }
        $j(SearchPersonEditor).with {
            fillTextField(nameField, name2)
            fillTextField(descriptionField, descr2)
            clickButton(ok)
        }
        $j(SearchPersonBrowse).with {
            checkRecordIsDisplayed(name2, SEARCH_PERSON_TABLE_J_TEST_ID)
        }
        $j(MainScreen).openJMXConsoleScreen()
        $j(JMXConsoleScreen).with {
            fillTextField(objectNameField, "search")
            clickButton(refresh)
            selectRowInTableByText(BEAN_NAME, M_BEANS_TABLE_J_TEST_ID)
            clickButton(inspect)
        }
        $j(BeanInspectScreen).with {
            invokeEnqueueIndexAll()
        }
        $j(OperationResultDialog).with {
            clickButton(closeBtn)
        }
        $j(BeanInspectScreen).with {
            clickButton(closeBtn)
        }
        $j(MainScreen).openSearchPersonBrowse()
        $j(SearchPersonBrowse).with {
            fillSearchField(SEARCH_PERSON)
            clickSearchButton()
            Selenide.sleep(3000)
            closeResultTab()
        }
        closeTab()
    }

    static void checkCRUDActions() {
        $j(MainScreen).openCustomerBrowse()
        $j(CustomerBrowse).with {
            clickButton(createBtn)
        }
        def customerName = getUniqueName(CUSTOMER_ENTITY_NAME)
        def customerEditedName = getUniqueName(CUSTOMER_ENTITY_NAME)
        def customerEmail = getUniqueName("email@com.")

        $j(CustomerEditor).with {
            fillTextField(nameField, customerName)
            fillTextField(emailField, customerEmail)
            gradeField.openOptionsPopup().select("CustomerGrade.SILVER")
            clickButton(ok)
        }
        $j(CustomerBrowse).with {
            checkRecordIsDisplayed(customerName, CUSTOMERS_TABLE_J_TEST_ID)
            selectRowInTableByText(customerName, CUSTOMERS_TABLE_J_TEST_ID)
            clickButton(editBtn)
        }
        $j(CustomerEditor).with {
            fillTextField(nameField, customerEditedName)
            clickButton(ok)
        }
        $j(CustomerBrowse).with {
            checkRecordIsDisplayed(customerEditedName, CUSTOMERS_TABLE_J_TEST_ID)
            selectRowInTableByText(customerEditedName, CUSTOMERS_TABLE_J_TEST_ID)
            clickButton(removeBtn)
        }
        $j(ConfirmationDialog).with {
            clickButton(yes)
        }
        $j(CustomerBrowse).with {
            checkRecordIsNotDisplayed(customerEditedName, CUSTOMERS_TABLE_J_TEST_ID)
        }
        closeTab()
    }

    static void checkLDAP() {
        $j(MainScreen).logout()
        loginAsCustomUser(LDAP_USER_LOGIN, LDAP_USER_PASSWORD)
        $j(Label, APP_TITLE_LABEL_J_TEST_ID).shouldBe(VISIBLE).shouldHave(value(APPLICATION_NAME))
        $j(MainScreen).logout()
        loginAsAdmin()
    }

    static void checkEmailSendingScreen() {
        $j(MainScreen).openEmailSendingScreen()
        def subject = getUniqueName("Email subject")
        $j(EmailSendingScreen).with {
            fillTextField(subjectField, subject)
            clickButton(sendEmail)
        }
        Selenide.sleep(1000)
        $j(MainScreen).openEmailHistoryScreen()
        $j(EmailHistoryScreen).with {
            checkRecordIsDisplayed(subject, SENDING_MESSAGE_TABLE_J_TEST_ID)
            checkRecordIsDisplayed(SENT_STATUS, SENDING_MESSAGE_TABLE_J_TEST_ID)
        }
        closeTab()
    }

    static void checkMultitenancy() {
        $j(MainScreen).openTenantBrowse()
        $j(TenantBrowse).with {
            clickButton(createBtn)
        }
        def tenantIdStr = getUniqueName("tenant")
        def tenantNameStr = getUniqueName("Tenant ")
        $j(TenantEditor).with {
            fillTextField(tenantIdField, tenantIdStr)
            fillTextField(nameField, tenantNameStr)
            clickButton(ok)
        }
        $j(TenantBrowse).with {
            checkRecordIsDisplayed(tenantIdStr, TENANTS_TABLE_J_TEST_ID)
            selectRowInTableByText(tenantIdStr, TENANTS_TABLE_J_TEST_ID)
            clickButton(removeBtn)
        }
        $j(ConfirmationDialog).with {
            clickButton(yes)
        }
        $j(TenantBrowse).with {
            checkRecordIsNotDisplayed(tenantIdStr, TENANTS_TABLE_J_TEST_ID)
        }
        closeTab()
    }

    static void checkExportsScreen() {
        $j(MainScreen).openCustomerBrowse()
        $j(CustomerBrowse).with {
            clickButton(excelExport)
            clickButton(jsonExport)
        }
        closeTab()
    }

    static void checkPivotTable() {
        $j(MainScreen).openPivotTableScreen()
        checkSelenideElementByJtestId(PIVOT_TABLE_J_TEST_ID)
        $j(PivotTableScreen).with {
            clickButton(xlsExport)
        }
        closeTab()
    }

    static void checkGrapeJS() {
        $j(MainScreen).openGrapeJSScreen()
        checkSelenideElementByJtestId(HTML_EDITOR_J_TEST_ID)
        closeTab()
    }

    static void checkDashboard() {
        $j(MainScreen).openDashboardBrowse()
        $j(DashboardBrowse).with {
            clickButton(createBtn)
        }
        def dashboardName = getUniqueName("Test dashboard ")
        def dashboardCode = getUniqueName("testdashboard")
        $j(DashboardEditor).with {
            fillTextField(title, dashboardName)
            fillTextField(code, dashboardCode)
            clickButton(okBtn)
        }
        $j(DashboardBrowse).with {
            checkRecordIsDisplayed(dashboardName, DASHBOARDS_TABLE_J_TEST_ID)
            selectRowInTableByText(dashboardName, DASHBOARDS_TABLE_J_TEST_ID)
            clickButton(removeBtn)
        }
        $j(ConfirmationDialog).with {
            clickButton(yes)
        }
        $j(DashboardBrowse).with {
            checkRecordIsNotDisplayed(dashboardName, DASHBOARDS_TABLE_J_TEST_ID)
        }
        closeTab()
    }

    static void checkImap() {
        $j(MainScreen).openImapConfigurationBrowse()
        $j(ImapConfigurationBrowse).with {
            clickButton(createBtn)
        }
        $j(ImapConfigurationEditor).with {
            [name, host, username, password].each {
                it.shouldBe(VISIBLE, EDITABLE, REQUIRED)
            }
            fillTextField(name, IMAP_CONFIGURATION_NAME)
            fillTextField(host, IMAP_HOST)
            fillTextField(port, IMAP_PORT)
            fillTextField(username, IMAP_USERNAME)
            fillTextField(password, IMAP_PASSWORD)
            selectSSLTLSecureConnection()
            clickButton(checkConnectionBtn)
            checkNotification(IMAP_SUCCESS_CONNECT_NOTIFICATION_TEXT)
            jmixFlagTextField.shouldBe(VISIBLE, EDITABLE, REQUIRED)
            fillTextField(jmixFlagTextField, "test")
            clickButton(ok)
        }

        $j(ImapConfigurationBrowse).with {
            checkRecordIsDisplayed(IMAP_CONFIGURATION_NAME, IMAP_CONFIG_TABLE_J_TEST_ID)
        }
        closeTab()
    }

    static void checkEmailTemplates() {
        $j(MainScreen).openEmailTemplateBrowse()

        $j(EmailTemplateBrowse).with {
            createFromDesignerEditor()
        }
        def tempname = getUniqueName("Test template")
        def tempcode = getUniqueName("testtemplate")
        $j(EmailTemplateEditor).with {

            name.shouldBe(REQUIRED)
            fillTextField(name, tempname)
            code.shouldBe(REQUIRED)
            fillTextField(code, tempcode)

            fillTextField(subject, "test subject")
            fillTextField(from, "1@1.test")
            fillTextField(to, "2@2.test")
            fillTextField(cc, "3@3.test")
            fillTextField(bcc, "4@4.test")
            clickButton(ok)
        }
        $j(EmailTemplateBrowse).with {
            checkRecordIsDisplayed(tempname, EMAIL_TEMPLATES_TABLE_J_TEST_ID)
            selectRowInTableByText(tempname, EMAIL_TEMPLATES_TABLE_J_TEST_ID)
            clickButton(removeBtn)
        }
        $j(ConfirmationDialog).with {
            clickButton(yes)
        }
        $j(EmailTemplateBrowse).with {
            checkRecordIsNotDisplayed(tempname, EMAIL_TEMPLATES_TABLE_J_TEST_ID)
        }
        closeTab()
    }

    static void checkDatatools() {
        $j(MainScreen).openEntityInspectorBrowse()

        $j(EntityInspectorBrowse).with {
            findEntityByFilter(USER_ENTITY_NAME, USER_ENTITY_FULL_STRING)
            selectShowMode(SHOW_ALL_RECORDS_MODE)
            checkRecordIsDisplayed(ADMIN, SALES_USER_TABLE_J_TEST_ID)
        }
        closeTab()
    }

    static void checkDynamicAttributes() {
        $j(MainScreen).openDynamicAttributeBrowse()
        closeTab()
    }

    static void checkSessions() {
        $j(MainScreen).openUserSessionBrowse()
        $j(UserSessionBrowse).with {
            checkRecordIsDisplayed(ADMIN, SESSIONS_TABLE_J_TEST_ID)
        }
        closeTab()
    }

    static void checkAudit() {
        $j(MainScreen).openEntityLogBrowse()
        $j(EntityLogBrowse).with {
            openSetupTab()
            createSetup()
            selectNameFromDropdown(CUSTOMER_ENTITY_FULL_NAME)
            setCheckbox(autoCheckBox, true)
            setCheckbox(manualCheckBox, true)
            setCheckbox(selectAllCheckBox, true)
            saveSetup()
            selectSetupRecord(CUSTOMER_ENTITY_NAME_WITH_PREFIX)
            applyChanges()
            checkAppliedChangesNotification()
            selectSetupRecord(CUSTOMER_ENTITY_NAME_WITH_PREFIX)
            clickButton(remove)
        }
        $j(ConfirmationDialog).with {
            clickButton(yes)
        }
        $j(EntityLogBrowse).with {
            checkRecordIsNotDisplayed(CUSTOMER_ENTITY_NAME_WITH_PREFIX, ENTITY_LOG_TABLE_J_TEST_ID)
            applyChanges()
            checkAppliedChangesNotification()
        }
        closeTab()
    }

    static void checkBPM() {
        $j(MainScreen).openModelerScreen()
        $j(ModelerScreen).with {
            checkModelerIsDisplayed()
            fillTextField(businessId, PROCESS_ID)
            fillTextField(name, PROCESS_NAME)
            sleep(2000)

            clickButton(deployBtn)
        }

        $j(ConfirmationDialog).with {
            clickButton(yes)
        }
        checkNotification(PROCESS_DEPLOYED_NOTIFICATION_TEXT)
        sleep(1000)

        $j(MainScreen).openProcessDefinitionBrowse()

        $j(ProcessDefinitionBrowse).with {
            checkRecordIsDisplayed(PROCESS_ID)
            checkButtons(DISABLED)
            selectRowInTableByText(PROCESS_ID)
            checkButtons(ENABLED)
            openInModelerBtn.click()
        }

        $j(ModelerScreen).with {
            checkModelerIsDisplayed()
            businessId.shouldHave(value(PROCESS_ID))
            name.shouldHave(value(PROCESS_NAME))
            fillTextField(businessId, PROCESS_ID + 1)
            closeModelerTab()
        }

        $j(UnsavedChangesBPMDialog).with {
            clickButton(doNotSave)
        }
        closeTab()
    }

    static void checkCharts() {
        def map = [[Menus.PIE_CHART_SCREEN, PIE3D_CHART_J_TEST_ID],
                   [Menus.SERIAL_SCREEN, SERIAL_CHART_J_TEST_ID],
                   [Menus.FUNNEL_CHART_SCREEN, FUNNEL_CHART_J_TEST_ID],
                   [Menus.ANGULAR_GAUGE_CHART_SCREEN, ANGULAR_GAUGE_CHART_J_TEST_ID],
                   [Menus.RADAR_CHART_SCREEN, RADAR_CHART_J_TEST_ID],
                   [Menus.STOCK_CHART_SCREEN, STOCK_CHART_J_TEST_ID],
                   [Menus.GANTT_CHART_SCREEN, GANTT_CHART_J_TEST_ID],
                   [Menus.DATABINDING_API_CHART_SCREEN, DATABINDING_CHART_J_TEST_ID],
                   [Menus.DIAGRAM_FROM_DATA_PROVIDER_CHART_SCREEN, CHART_J_TEST_ID],
                   [Menus.GAUGE_CHART_SCREEN, GAUGE_CHART_J_TEST_ID],
                   [Menus.DIAGRAM_FROM_ENTITY_CHART_SCREEN, CHART_J_TEST_ID],
                   [Menus.DIAGRAM_FROM_JSON_CHART_SCREEN, DIAGRAM_FROM_JSON_CHART_J_TEST_ID]
        ]

        map.each {
            $j(MainScreen).openChartScreen(it.get(0) as SideMenu.Menu<ChartScreen>)
            checkSelenideElementByJtestId(it.get(1) as String)
            sleep(1000)
            closeTab()
        }

        $j(MainScreen).openChartScreen(Menus.XY_CHART_SCREEN)
        checkSelenideElementByClass("jmix-amcharts-chart")
        sleep(1000)
        closeTab()

        $j(MainScreen).openChartScreen(Menus.INCREMENTAL_UPDATE_CHART_SCREEN)
        checkSelenideElementByJtestId(INCREMENTAL_CHART_J_TEST_ID)
        Selenide.sleep(5000)
        closeTab()
    }

    static void checkMaps() {
        [Menus.GEO_POINT_BROWSE, Menus.GEO_POLYLINE_BROWSE, Menus.GEO_POLYGON_BROWSE, Menus.IMAGE_LAYER_BROWSE, Menus.WMS_LAYER_BROWSE].each {
            $j(MainScreen).openGeoBrowse(it)
            checkSelenideElementByJtestId(MAP_J_TEST_ID)
            sleep(1000)
            closeTab()
        }
    }

    static void checkReport() {

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
        closeTab()
    }

}
