package io.jmix.tests.ui.test.smoke.crud.applicationsettings

import io.jmix.masquerade.component.TextField
import io.jmix.tests.ui.screen.administration.applicationsettings.ApplicationSettingsScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j
import static io.jmix.masquerade.Selectors.byJTestId

class ApplicationSettingsSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String APPLICATION_SETTINGS_CUSTOM_ENTITY = "Custom settings (sales_CustomSettings)"
    public static final String NOTIFICATION_TEXT_FIELD = "Hi! This is a test text"
    public static final String DEFAULT_GRADE_FIELD = "A"
    public static final String APP_SETTINGS_EMPTY_STRING = ""
    public static final String APP_SETTINGS_NOTIFICATION_TEXT_SAVED = "Custom settings saved successfully"
    public static final String NOTIFICATION_TEXT_FIELD_J_TEST_ID = "notificationText"
    public static final String DEFAULT_GRADE_FIELD_J_TEST_ID = "defaultGrade"
    public String notifTextFieldValue = ""
    public String defGradeFieldValue = ""

    @BeforeEach
    void openApplicationSettingsScreen() {
        $j(MainScreen).openApplicationSettingsScreen()
    }

    @Test
    @DisplayName("Сhecks default values and notification when save values")
    void checkDefaultValuesAndNotification() {
        notifTextFieldValue = getUniqueName(NOTIFICATION_TEXT_FIELD)
        defGradeFieldValue = getUniqueName(DEFAULT_GRADE_FIELD)
        $j(ApplicationSettingsScreen).with {
            selectApplicationSettingsEntity(APPLICATION_SETTINGS_CUSTOM_ENTITY)
            fillTextField($j(TextField, byJTestId(NOTIFICATION_TEXT_FIELD_J_TEST_ID)), notifTextFieldValue)
            fillTextField($j(TextField, byJTestId(DEFAULT_GRADE_FIELD_J_TEST_ID)), defGradeFieldValue)
            checkDefaultNotifText()
            checkDefaultDefGrade()
            clickButton(saveButtonId)
            checkNotificationCaption(APP_SETTINGS_NOTIFICATION_TEXT_SAVED)
            clickButton(closeButtonId)
        }
    }

    @Test
    @DisplayName("Fills and cleans values with page reopening")
    void fillAndCleanValuesWithPageReopening() {
        notifTextFieldValue = getUniqueName(NOTIFICATION_TEXT_FIELD)
        defGradeFieldValue = getUniqueName(DEFAULT_GRADE_FIELD)
        $j(ApplicationSettingsScreen).with {
            selectApplicationSettingsEntity(APPLICATION_SETTINGS_CUSTOM_ENTITY)
            fillTextField($j(TextField, byJTestId(NOTIFICATION_TEXT_FIELD_J_TEST_ID)), notifTextFieldValue)
            fillTextField($j(TextField, byJTestId(DEFAULT_GRADE_FIELD_J_TEST_ID)), defGradeFieldValue)
            clickButton(saveButtonId)
            clickButton(closeButtonId)
        }
        openApplicationSettingsScreen()
        $j(ApplicationSettingsScreen).with {
            selectApplicationSettingsEntity(APPLICATION_SETTINGS_CUSTOM_ENTITY)
            checkFilledTextField($j(TextField, byJTestId(NOTIFICATION_TEXT_FIELD_J_TEST_ID)), notifTextFieldValue)
            checkFilledTextField($j(TextField, byJTestId(DEFAULT_GRADE_FIELD_J_TEST_ID)), defGradeFieldValue)
            fillTextField($j(TextField, byJTestId(NOTIFICATION_TEXT_FIELD_J_TEST_ID)), APP_SETTINGS_EMPTY_STRING)
            fillTextField($j(TextField, byJTestId(DEFAULT_GRADE_FIELD_J_TEST_ID)), APP_SETTINGS_EMPTY_STRING)
            clickButton(saveButtonId)
            clickButton(closeButtonId)
        }
        openApplicationSettingsScreen()
        $j(ApplicationSettingsScreen).with {
            selectApplicationSettingsEntity(APPLICATION_SETTINGS_CUSTOM_ENTITY)
            checkFilledTextField($j(TextField, byJTestId(NOTIFICATION_TEXT_FIELD_J_TEST_ID)), APP_SETTINGS_EMPTY_STRING)
            checkFilledTextField($j(TextField, byJTestId(DEFAULT_GRADE_FIELD_J_TEST_ID)), APP_SETTINGS_EMPTY_STRING)
            clickButton(closeButtonId)
        }
    }
}

