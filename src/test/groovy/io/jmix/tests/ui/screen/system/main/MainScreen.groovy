package io.jmix.tests.ui.screen.system.main

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.SideMenu
import io.jmix.tests.ui.menu.Menus
import io.jmix.tests.ui.screen.addonscreen.email.EmailSendingScreen
import io.jmix.tests.ui.screen.addonscreen.GrapeJSScreen
import io.jmix.tests.ui.screen.addonscreen.PivotTableScreen
import io.jmix.tests.ui.screen.addonscreen.RESTScreen
import io.jmix.tests.ui.screen.addonscreen.search.SearchPersonBrowse
import io.jmix.tests.ui.screen.administration.applicationsettings.ApplicationSettingsScreen
import io.jmix.tests.ui.screen.administration.audit.EntityLogBrowse
import io.jmix.tests.ui.screen.administration.businesscalendars.browse.BusinessCalendarBrowse
import io.jmix.tests.ui.screen.administration.datatools.EntityInspectorBrowse
import io.jmix.tests.ui.screen.administration.dynattr.browser.DynamicAttributeBrowse
import io.jmix.tests.ui.screen.administration.emailhistory.EmailHistoryScreen
import io.jmix.tests.ui.screen.administration.emailtemplates.EmailTemplateBrowse
import io.jmix.tests.ui.screen.administration.jmx.JMXConsoleScreen
import io.jmix.tests.ui.screen.administration.notifications.NotificationBrowse
import io.jmix.tests.ui.screen.administration.quartzjobs.browse.QuartzJobsBrowse
import io.jmix.tests.ui.screen.administration.security.browser.RoleBrowse
import io.jmix.tests.ui.screen.administration.session.UserSessionBrowse
import io.jmix.tests.ui.screen.administration.tenants.TenantBrowse
import io.jmix.tests.ui.screen.administration.webdav.WebDAVDocumentBrowse
import io.jmix.tests.ui.screen.application.customer.CustomerBrowse
import io.jmix.tests.ui.screen.application.user.UserBrowse
import io.jmix.tests.ui.screen.bpm.ModelerScreen
import io.jmix.tests.ui.screen.bpm.ProcessDefinitionBrowse
import io.jmix.tests.ui.screen.charts.ChartScreen
import io.jmix.tests.ui.screen.dashboard.DashboardBrowse
import io.jmix.tests.ui.screen.imap.ImapConfigurationBrowse
import io.jmix.tests.ui.screen.imap.ImapMessageBrowse
import io.jmix.tests.ui.screen.map.MapScreen
import io.jmix.tests.ui.screen.reports.browser.ReportBrowse
import io.jmix.tests.ui.screen.system.login.LoginScreen

import static io.jmix.masquerade.Components.wire

class MainScreen extends Composite<MainScreen> {

    @Wire
    Button logoutButton
    @Wire
    SideMenu sideMenu

    LoginScreen logout() {
        logoutButton.click()
        wire(LoginScreen)
    }

    EntityInspectorBrowse openEntityInspectorBrowse() {
        sideMenu.openItem(Menus.ENTITY_INSPECTOR_BROWSE)
    }

    EntityLogBrowse openEntityLogBrowse() {
        sideMenu.openItem(Menus.ENTITY_LOG_BROWSE)
    }

    UserBrowse openUserBrowse() {
        sideMenu.openItem(Menus.USER_BROWSE)
    }

    CustomerBrowse openCustomerBrowse() {
        sideMenu.openItem(Menus.CUSTOMER_BROWSE)
    }

    RoleBrowse openResourceRoleBrowse() {
        sideMenu.openItem(Menus.RESOURCE_ROLE_BROWSE)
    }

    RoleBrowse openRowLevelRoleBrowse() {
        sideMenu.openItem(Menus.ROW_LEVEL_ROLE_BROWSE)
    }

    ReportBrowse openReportsBrowse() {
        sideMenu.openItem(Menus.REPORTS_BROWSE)
    }

    DynamicAttributeBrowse openDynamicAttributeBrowse() {
        sideMenu.openItem(Menus.DYNAMIC_ATTRIBUTES_BROWSE)
    }

    ModelerScreen openModelerScreen() {
        sideMenu.openItem(Menus.MODELER)
    }

    ProcessDefinitionBrowse openProcessDefinitionBrowse() {
        sideMenu.openItem(Menus.PROCESS_DEFINITION_BROWSE)
    }

    ChartScreen openChartScreen(SideMenu.Menu<ChartScreen> screen) {
        sideMenu.openItem(screen)
    }

    MapScreen openGeoBrowse(SideMenu.Menu<MapScreen> screen) {
        sideMenu.openItem(screen)
    }

    UserSessionBrowse openUserSessionBrowse() {
        sideMenu.openItem(Menus.USER_SESSION_BROWSE)
    }

    EmailTemplateBrowse openEmailTemplateBrowse() {
        sideMenu.openItem(Menus.EMAIL_TEMPLATE_BROWSE)
    }

    ImapConfigurationBrowse openImapConfigurationBrowse() {
        sideMenu.openItem(Menus.IMAP_CONFIGURATION_BROWSE)
    }

    ImapMessageBrowse openImapMessageBrowse() {
        sideMenu.openItem(Menus.IMAP_MESSAGE_BROWSE)
    }

    DashboardBrowse openDashboardBrowse() {
        sideMenu.openItem(Menus.DASHBOARD_BROWSE)
    }

    GrapeJSScreen openGrapeJSScreen() {
        sideMenu.openItem(Menus.GRAPE_JS_SCREEN)
    }

    PivotTableScreen openPivotTableScreen() {
        sideMenu.openItem(Menus.PIVOT_TABLE_SCREEN)
    }

    TenantBrowse openTenantBrowse() {
        sideMenu.openItem(Menus.TENANTS_BROWSE)
    }

    JMXConsoleScreen openJMXConsoleScreen() {
        sideMenu.openItem(Menus.JMX_CONSOLE_SCREEN)
    }

    EmailSendingScreen openEmailSendingScreen() {
        sideMenu.openItem(Menus.EMAIL_SENDING_SCREEN)
    }

    EmailHistoryScreen openEmailHistoryScreen() {
        sideMenu.openItem(Menus.EMAIL_HISTORY_SCREEN)
    }

    SearchPersonBrowse openSearchPersonBrowse() {
        sideMenu.openItem(Menus.SEARCH_PERSON_BROWSE)
    }

    RESTScreen openRESTScreen() {
        sideMenu.openItem(Menus.REST_SCREEN)
    }

    WebDAVDocumentBrowse openWebDAVDocumentBrowse() {
        sideMenu.openItem(Menus.WEBDAV_DOCUMENT_BROWSE)
    }

    NotificationBrowse openNotificationBrowse() {
        sideMenu.openItem(Menus.NOTIFICATION_BROWSE)
    }

    BusinessCalendarBrowse openBusinessCalendarBrowse() {
        sideMenu.openItem(Menus.BUSINESS_CALENDAR_BROWSE)
    }

    QuartzJobsBrowse openQuartzJobsBrowse() {
        sideMenu.openItem(Menus.QUARTZ_JOBS_BROWSE)
    }

    ApplicationSettingsScreen openApplicationSettingsScreen() {
        sideMenu.openItem(Menus.APPLICATION_SETTINGS_SCREEN)
    }
}
