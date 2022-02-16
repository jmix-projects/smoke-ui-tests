package io.jmix.tests.ui.test.smoke.externalservices.emailsending

import com.codeborne.selenide.Selenide
import io.jmix.tests.ui.screen.addonscreen.EmailSendingScreen
import io.jmix.tests.ui.screen.administration.emailhistory.EmailHistoryScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class EmailSendingSmokeUITest extends BaseUiTest implements UiHelper {

    public static final String SENT_STATUS = "Sent"
    public static final String SENDING_MESSAGE_TABLE_J_TEST_ID = "sendingMessageTable"

    @Test
    @DisplayName("Sends an email")
    void checkEmailSendingScreen() {
        $j(MainScreen).openEmailSendingScreen()

        def subject = getUniqueName("Email subject")
        $j(EmailSendingScreen).with {
            fillTextField(subjectField, subject)
            clickButton(sendEmail)
        }
        Selenide.sleep(4000)
        $j(MainScreen).openEmailHistoryScreen()
        $j(EmailHistoryScreen).with {
            checkRecordIsDisplayed(subject, SENDING_MESSAGE_TABLE_J_TEST_ID)
            checkRecordIsDisplayed(SENT_STATUS, SENDING_MESSAGE_TABLE_J_TEST_ID)
        }
    }
}
