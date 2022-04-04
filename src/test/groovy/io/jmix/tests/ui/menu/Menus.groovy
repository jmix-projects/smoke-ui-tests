/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 */

package io.jmix.tests.ui.menu

import io.jmix.masquerade.component.SideMenu
import io.jmix.tests.ui.screen.addonscreen.EmailSendingScreen
import io.jmix.tests.ui.screen.addonscreen.GrapeJSScreen
import io.jmix.tests.ui.screen.addonscreen.PivotTableScreen
import io.jmix.tests.ui.screen.addonscreen.RESTScreen
import io.jmix.tests.ui.screen.addonscreen.search.SearchPersonBrowse
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

final class Menus {
    public static final SideMenu.Menu<UserBrowse> USER_BROWSE =
            new SideMenu.Menu<>(UserBrowse, 'application', 'User.browse')

    public static final SideMenu.Menu<CustomerBrowse> CUSTOMER_BROWSE =
            new SideMenu.Menu<>(CustomerBrowse, 'application', 'sales_Customer.browse')

    public static final SideMenu.Menu<ModelerScreen> MODELER =
            new SideMenu.Menu<>(ModelerScreen, 'bpm', 'bpm_BpmnModelerScreen')

    public static final SideMenu.Menu<ProcessDefinitionBrowse> PROCESS_DEFINITION_BROWSE =
            new SideMenu.Menu<>(ProcessDefinitionBrowse, 'bpm', 'bpm_ProcessDefinition.browse')

    public static final SideMenu.Menu<ChartScreen> XY_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_BlankScreenXychart')

    public static final SideMenu.Menu<ChartScreen> SERIAL_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_BlankScreenSerialchart')

    public static final SideMenu.Menu<ChartScreen> PIE_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_BlankScreenPiechart')

    public static final SideMenu.Menu<ChartScreen> FUNNEL_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_BlankScreenFunnelchart')

    public static final SideMenu.Menu<ChartScreen> ANGULAR_GAUGE_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_BlankScreenAngularGaugechart')

    public static final SideMenu.Menu<ChartScreen> RADAR_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_BlankScreenRadarchart')

    public static final SideMenu.Menu<ChartScreen> STOCK_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_BlankScreenStockchart')

    public static final SideMenu.Menu<ChartScreen> GANTT_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_BlankScreenGanttchart')

    public static final SideMenu.Menu<ChartScreen> DIAGRAM_FROM_DATA_PROVIDER_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_Diagramfromdataprovider')

    public static final SideMenu.Menu<ChartScreen> GAUGE_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_BlankScreen')

    public static final SideMenu.Menu<ChartScreen> DIAGRAM_FROM_ENTITY_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_Diagramfromentity')

    public static final SideMenu.Menu<ChartScreen> DATABINDING_API_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_BlankScreenDatabindingapi')

    public static final SideMenu.Menu<ChartScreen> INCREMENTAL_UPDATE_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_Chartsincrementalupdating')

    public static final SideMenu.Menu<ChartScreen> DIAGRAM_FROM_JSON_CHART_SCREEN =
            new SideMenu.Menu<>(ChartScreen, 'charts', 'sales_Diagramjson')

    public static final SideMenu.Menu<MapScreen> GEO_POINT_BROWSE =
            new SideMenu.Menu<>(MapScreen, 'maps', 'sales_GeoPoint.browse')

    public static final SideMenu.Menu<MapScreen> GEO_POLYLINE_BROWSE =
            new SideMenu.Menu<>(MapScreen, 'maps', 'sales_GeoPolyline.browse')

    public static final SideMenu.Menu<MapScreen> GEO_POLYGON_BROWSE =
            new SideMenu.Menu<>(MapScreen, 'maps', 'sales_GeoPolygon.browse')

    public static final SideMenu.Menu<MapScreen> WMS_LAYER_BROWSE =
            new SideMenu.Menu<>(MapScreen, 'maps', 'sales_WMSLayer.browse')
    public static final SideMenu.Menu<MapScreen> IMAGE_LAYER_BROWSE =
            new SideMenu.Menu<>(MapScreen, 'maps', 'sales_ImageLayer.browse')

    public static final SideMenu.Menu<DynamicAttributeBrowse> DYNAMIC_ATTRIBUTES_BROWSE =
            new SideMenu.Menu<>(DynamicAttributeBrowse, 'administration', 'dynat_Category.browse')

    public static final SideMenu.Menu<EntityInspectorBrowse> ENTITY_INSPECTOR_BROWSE =
            new SideMenu.Menu<>(EntityInspectorBrowse, 'administration', 'entityInspector.browse')
    public static final SideMenu.Menu<EntityLogBrowse> ENTITY_LOG_BROWSE =
            new SideMenu.Menu<>(EntityLogBrowse, 'administration', 'entityLog.browse')

    public static final SideMenu.Menu<UserSessionBrowse> USER_SESSION_BROWSE =
            new SideMenu.Menu<>(UserSessionBrowse, 'administration', 'userSessions.browse')

    public static final SideMenu.Menu<EntityInspectorBrowse> ENTITY_INSPECTOR =
            new SideMenu.Menu<>(EntityInspectorBrowse, 'administration', 'entityInspector.browse')

    public static final SideMenu.Menu<RoleBrowse> RESOURCE_ROLE_BROWSE =
            new SideMenu.Menu<>(RoleBrowse, 'administration', 'sec_ResourceRoleModel.browse')

    public static final SideMenu.Menu<RoleBrowse> ROW_LEVEL_ROLE_BROWSE =
            new SideMenu.Menu<>(RoleBrowse, 'administration', 'sec_RowLevelRoleModel.browse')

    public static final SideMenu.Menu<EmailTemplateBrowse> EMAIL_TEMPLATE_BROWSE =
            new SideMenu.Menu<>(EmailTemplateBrowse, 'administration', 'emltmp_EmailTemplate.browse')

    public static final SideMenu.Menu<TenantBrowse> TENANTS_BROWSE =
            new SideMenu.Menu<>(TenantBrowse, 'administration', 'mten_Tenant.browse')

    public static final SideMenu.Menu<JMXConsoleScreen> JMX_CONSOLE_SCREEN =
            new SideMenu.Menu<>(JMXConsoleScreen, 'administration', 'ui_JmxConsoleScreen')

    public static final SideMenu.Menu<ReportBrowse> REPORTS_BROWSE =
            new SideMenu.Menu<>(ReportBrowse, 'reports', 'report_Report.browse')

    public static final SideMenu.Menu<ImapConfigurationBrowse> IMAP_CONFIGURATION_BROWSE =
            new SideMenu.Menu<>(ImapConfigurationBrowse, 'imap-component', 'imap_MailBox.browse')

    public static final SideMenu.Menu<ImapMessageBrowse> IMAP_MESSAGE_BROWSE =
            new SideMenu.Menu<>(ImapMessageBrowse, 'imap-component', 'imap_Message.browse')

    public static final SideMenu.Menu<DashboardBrowse> DASHBOARD_BROWSE =
            new SideMenu.Menu<>(DashboardBrowse, 'dashboard', 'dshbrd_PersistentDashboard.browse')

    public static final SideMenu.Menu<GrapeJSScreen> GRAPE_JS_SCREEN =
            new SideMenu.Menu<>(GrapeJSScreen, 'addonsScreens', 'sales_Grapesjs')

    public static final SideMenu.Menu<PivotTableScreen> PIVOT_TABLE_SCREEN =
            new SideMenu.Menu<>(PivotTableScreen, 'addonsScreens', 'sales_Creatingpivottable')


    public static final SideMenu.Menu<EmailSendingScreen> EMAIL_SENDING_SCREEN =
            new SideMenu.Menu<>(EmailSendingScreen, 'addonsScreens', 'sales_EmailSending')

    public static final SideMenu.Menu<SearchPersonBrowse> SEARCH_PERSON_BROWSE =
            new SideMenu.Menu<>(SearchPersonBrowse, 'addonsScreens', 'sales_SearchPerson.browse')

    public static final SideMenu.Menu<RESTScreen> REST_SCREEN =
            new SideMenu.Menu<>(RESTScreen, 'addonsScreens', 'sales_RestScreen')

    public static final SideMenu.Menu<EmailHistoryScreen> EMAIL_HISTORY_SCREEN =
            new SideMenu.Menu<>(EmailHistoryScreen, 'administration', 'email_SendingMessage.browse')

    public static final SideMenu.Menu<WebDAVDocumentBrowse> WEBDAV_DOCUMENT_BROWSE =
            new SideMenu.Menu<>(WebDAVDocumentBrowse, 'administration', 'webdav_WebdavDocument.browse')

    public static final SideMenu.Menu<NotificationBrowse> NOTIFICATION_BROWSE =
            new SideMenu.Menu<>(NotificationBrowse, 'administration', 'ntf_InAppNotification.browse')

    public static final SideMenu.Menu<BusinessCalendarBrowse> BUSINESS_CALENDAR_BROWSE =
            new SideMenu.Menu<>(BusinessCalendarBrowse, 'administration', 'buscal_BusinessCalendarModel.browse')

    public static final SideMenu.Menu<QuartzJobsBrowse> QUARTZ_JOBS_BROWSE =
            new SideMenu.Menu<>(QuartzJobsBrowse, 'administration', 'quartz_JobModel.browse')

}
