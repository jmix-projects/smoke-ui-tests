package io.jmix.tests.ui.test.smoke.withoutdDBactions.charts

import com.codeborne.selenide.Selenide
import io.jmix.masquerade.component.SideMenu
import io.jmix.tests.ui.menu.Menus
import io.jmix.tests.ui.screen.charts.ChartScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static com.codeborne.selenide.Selenide.sleep
import static io.jmix.masquerade.Selectors.$j

class ChartsSmokeUITest extends BaseUiTest implements UiHelper {

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

    @Test
    @DisplayName("Charts smoke test")
    void checkCharts() {

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
            sleep(500)
        }

        $j(MainScreen).openChartScreen(Menus.XY_CHART_SCREEN)
        checkSelenideElementByClass("jmix-amcharts-chart")
        sleep(500)

        $j(MainScreen).openChartScreen(Menus.INCREMENTAL_UPDATE_CHART_SCREEN)
        checkSelenideElementByJtestId(INCREMENTAL_CHART_J_TEST_ID)
        Selenide.sleep(4000)
    }
}
