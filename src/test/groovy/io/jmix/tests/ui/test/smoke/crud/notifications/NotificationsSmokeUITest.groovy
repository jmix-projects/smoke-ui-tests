package io.jmix.tests.ui.test.smoke.crud.notifications

import io.jmix.tests.ui.screen.administration.notifications.NotificationBrowse
import io.jmix.tests.ui.screen.administration.notifications.NotificationEditor
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest

import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class NotificationsSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String NOTIFICATION_J_TEST_ID = "inAppNotificationsTable"
    public static final String TEST_NOTIFICATION_BASE_RECIPIENT = "admin"
    public static final String TEST_NOTIFICATION_TYPE_INFO = "info"
    public static final String NOTIFICATION_CHANNELS_IN_APP = "In-app channel"
    public static final String NOTIFICATION_BODY = "Hi,QA!"

    @BeforeEach
    void openNotificationBrowse() {
        $j(MainScreen).openNotificationBrowse()
    }

    @Test
    @DisplayName("Create a notification")
    void createNotification() {
        $j(NotificationBrowse).with {
            clickButton(createBtn)
        }

        def subject = getUniqueName("AutoTest ")
        $j(NotificationEditor).with {
            fillTextField(subjectField, subject)
            selectType(TEST_NOTIFICATION_TYPE_INFO)
            selectRecipient(TEST_NOTIFICATION_BASE_RECIPIENT)
            chooseChannelsField(NOTIFICATION_CHANNELS_IN_APP)
            fillTextField(plainTextBodyField, NOTIFICATION_BODY)
            clickButton(okBtn)
        }

        $j(NotificationBrowse).with {
            checkRecordIsDisplayed(subject, NOTIFICATION_J_TEST_ID)
        }
    }
}