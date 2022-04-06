package io.jmix.tests.ui.test.smoke.crud.applicationsettings

import io.jmix.masquerade.component.TextField
import io.jmix.tests.ui.screen.administration.applicationsettings.ApplicationSettingsScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

import static io.jmix.masquerade.Selectors.$j
import static io.jmix.masquerade.Selectors.byJTestId

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationSettingsSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String APPLICATION_SETTINGS_CUSTOM_ENTITY = "Custom settings (sales_CustomSettings)"
    public static final String FIELD_NOTIFICATION_TEXT = "картошка"
    public static final String FIELD_DEFAULT_GRADE = "testik1"
    public static final String APPLICATION_SETTINGS_EMPTY_STRING = ""
    public static final String APPLICATION_SETTINGS_NOTIFICATION = "Custom settings saved successfully"
    public static final String NOTIFICATION_TEXT_FIELD_J_TEST_ID = "notificationText"
    public static final String DEFAULT_GRADE_FIELD_J_TEST_ID = "defaultGrade"
    public String notifText = ""
    public String defGrade = ""

    @BeforeEach
    void openApplicationSettingsScreen() {
        $j(MainScreen).openApplicationSettingsScreen()
    }

    @Test
    @Order(1)
    @DisplayName("Selects an entity and fills in the current value fields")
    void fillsInTheCurrentValueFields() {
         notifText = getUniqueName(FIELD_NOTIFICATION_TEXT)
         defGrade = getUniqueName(FIELD_DEFAULT_GRADE)
        $j(ApplicationSettingsScreen).with {
            selectApplicationSettingsEntity(APPLICATION_SETTINGS_CUSTOM_ENTITY)
            fillTextField($j(TextField, byJTestId(NOTIFICATION_TEXT_FIELD_J_TEST_ID)), notifText)
            fillTextField($j(TextField, byJTestId(DEFAULT_GRADE_FIELD_J_TEST_ID)), defGrade)
            checkDefaultNotifText()
            checkDefaultDefGrade()
            clickButton(saveButtonId)
            checkNotificationCaption(APPLICATION_SETTINGS_NOTIFICATION)
        }
    }

    @Test
    @Order(2)
    @DisplayName("Removes current values")
    void removesCurrentValues() {
        $j(ApplicationSettingsScreen).with {
            selectApplicationSettingsEntity(APPLICATION_SETTINGS_CUSTOM_ENTITY)
            checkFilledTextField($j(TextField, byJTestId(NOTIFICATION_TEXT_FIELD_J_TEST_ID)), notifText)
            checkFilledTextField($j(TextField, byJTestId(DEFAULT_GRADE_FIELD_J_TEST_ID)), defGrade)
            fillTextField($j(TextField, byJTestId(NOTIFICATION_TEXT_FIELD_J_TEST_ID)), APPLICATION_SETTINGS_EMPTY_STRING)
            fillTextField($j(TextField, byJTestId(DEFAULT_GRADE_FIELD_J_TEST_ID)), APPLICATION_SETTINGS_EMPTY_STRING)
            clickButton(saveButtonId)
            checkNotificationCaption(APPLICATION_SETTINGS_NOTIFICATION)
        }
    }
}

