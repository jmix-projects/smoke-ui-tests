package io.jmix.tests.ui.test.smoke.crud.businesscalendars

import io.jmix.tests.ui.screen.administration.businesscalendars.browse.BusinessCalendarBrowse
import io.jmix.tests.ui.screen.administration.businesscalendars.editor.BusinessCalendarEditor
import io.jmix.tests.ui.screen.administration.businesscalendars.dialog.HolidayEditorDialog
import io.jmix.tests.ui.screen.administration.businesscalendars.editor.tab.HolidaysTab
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest

import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class BusinessCalendarsSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String BUSINESS_CALENDARS_TABLE_J_TEST_ID = "businessCalendarsTable"
    public static final String BUSINESS_CALENDAR_HOLIDAY_DESCRIPTION = "here is a description"
    public static final String BUSINESS_CALENDAR_TAB_HOLIDAYS = "holidaysTabId"
    public static final String BUSINESS_CALENDAR_TYPE_DAY_OF_WEEK = "Day of week"
    public static final String BUSINESS_CALENDAR_DAY_OF_WEEK_SATURDAY = "Saturday"
    public static final String BUSINESS_CALENDAR_DAY_OF_WEEK_SUNDAY = "Sunday"

    @BeforeEach
    void openBusinessCalendarBrowse() {
        $j(MainScreen).openBusinessCalendarBrowse()
    }

    @Test
    @DisplayName("Creates businessCalendar")
    void createBusinessCalendar() {

        $j(BusinessCalendarBrowse).with {
            clickButton(createBtn)
        }

        def codeCalendar = getUniqueName("code")
        def nameCalendar = getUniqueName("Calendar ")
        $j(BusinessCalendarEditor).with {
            fillTextField(nameField, nameCalendar)
            fillTextField(codeField, codeCalendar)
            tabSheetId.getTab(BUSINESS_CALENDAR_TAB_HOLIDAYS)
        }

        $j(HolidaysTab).with {
            clickButton(create)
        }

        $j(HolidayEditorDialog).with {
            selectDayOfWeekHolidayType(BUSINESS_CALENDAR_TYPE_DAY_OF_WEEK)
            dayOfWeekCheckboxGroup(BUSINESS_CALENDAR_DAY_OF_WEEK_SATURDAY)
            dayOfWeekCheckboxGroup(BUSINESS_CALENDAR_DAY_OF_WEEK_SUNDAY)
            fillTextField(descriptionField, BUSINESS_CALENDAR_HOLIDAY_DESCRIPTION)
            clickButton(ok)
        }

        $j(BusinessCalendarEditor).with {
            clickButton(ok)
        }

        $j(BusinessCalendarBrowse).with {
            checkRecordIsDisplayed(nameCalendar, BUSINESS_CALENDARS_TABLE_J_TEST_ID)
        }
    }
}

