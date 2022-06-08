package io.jmix.tests.ui.test.smoke.externalservices.emailsending

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import io.jmix.tests.ui.screen.addonscreen.email.EmailSendingScreen
import io.jmix.tests.ui.screen.administration.emailhistory.EmailHistoryScreen
import io.jmix.tests.ui.screen.administration.emailhistory.dialog.EmailAttachmentDownloadDialog
import io.jmix.tests.ui.screen.administration.emailhistory.dialog.EmailResendDialog
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder

import static com.codeborne.selenide.Selenide.$
import static io.jmix.masquerade.Selectors.$j
import static io.jmix.masquerade.Selectors.byClassName

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmailSendingSmokeUITest extends BaseUiTest implements UiHelper {

    public static final String SENT_STATUS = "Sent"
    public static final String QUEUE_STATUS = 'Queue'

    public static final String SENDING_MESSAGE_TABLE_J_TEST_ID = "sendingMessageTable"
    public static final String ATTACHMENTS_TABLE_J_TEST_ID = "table"
    public static final String TEST_SYNC_BASE_STRING = 'TestSync'
    public static final String TEST_ASYNC_BASE_STRING = 'TestAsync'
    public static final String EMPTY_DOWNLOAD_STRING = 'EmptyDownload'
    public static final String ONE_ATTACHMENT_STRING = 'DownloadAttach'
    public static final String MULTI_ATTACHMENTS_STRING = 'DownloadMultiAttach'
    public static final String EMAIL_ATTACH_PNG = "emailAttach.png;"
    public static final String TEST_PNG = "test.png"
    public static final String TEST_BCC_EMAIL_ADDRESS = "username@haulmont.com"
    public static final String TEST_CC_EMAIL_ADDRESS = "username@haulmont.com"
    public static final String TEST_TO_EMAIL_ADDRESS = "username@haulmont.com"
    public static final String MULTI_ATTACHMENT_STRING = 'emailAttach.png;test.png;'

    @Test
    @Order(1)
    @DisplayName(value = "Downloads attachments from sent emails")
    void downloadAttachment() {
        $j(MainScreen).openEmailSendingScreen()
        $j(EmailSendingScreen) with {
            fillTextField(subject, EMPTY_DOWNLOAD_STRING)
            clickButton(sync)

            fillTextField(subject, ONE_ATTACHMENT_STRING)
            clickButton(syncAttach)

            fillTextField(subject, MULTI_ATTACHMENTS_STRING)
            clickButton(syncMultiAttach)
        }
        $j(MainScreen).openEmailHistoryScreen()
        $j(EmailHistoryScreen) with {
            Selenide.sleep(2000)

            selectRowInTableByText(EMPTY_DOWNLOAD_STRING, SENDING_MESSAGE_TABLE_J_TEST_ID)
            clickButton(downloadAttachmentBtn)
            checkNotificationCaption("Message has no attachments")

            checkByCellsRecordIsDisplayed(ONE_ATTACHMENT_STRING, EMAIL_ATTACH_PNG, SENDING_MESSAGE_TABLE_J_TEST_ID)
            selectRowInTableByText(ONE_ATTACHMENT_STRING, SENDING_MESSAGE_TABLE_J_TEST_ID)
            clickButton(downloadAttachmentBtn)

            checkByCellsRecordIsDisplayed(MULTI_ATTACHMENTS_STRING, MULTI_ATTACHMENT_STRING, SENDING_MESSAGE_TABLE_J_TEST_ID)
            selectRowInTableByText(MULTI_ATTACHMENTS_STRING, SENDING_MESSAGE_TABLE_J_TEST_ID)
            clickButton(downloadAttachmentBtn)

            $j(EmailAttachmentDownloadDialog).with {
                selectRowInTableByText(TEST_PNG, ATTACHMENTS_TABLE_J_TEST_ID)
                clickButton(select)
            }
        }
    }

    @Test
    @Order(2)
    @DisplayName(value = "Sends synchronized email without attachments")
    void syncManualEmailSending() {
        $j(MainScreen).openEmailSendingScreen()
        def emailSubject = getUniqueName(TEST_SYNC_BASE_STRING)
        $j(EmailSendingScreen).with {
            fillTextField(subject, emailSubject)
            clickButton(sync)
            Selenide.sleep(10000)
        }

        $j(MainScreen).openEmailHistoryScreen()

        $j(EmailHistoryScreen).with {
            checkByCellsRecordIsDisplayed(emailSubject, SENT_STATUS, SENDING_MESSAGE_TABLE_J_TEST_ID)
            checkFilledTextField(datepart, "")
        }
    }

    @Test
    @Order(3)
    @DisplayName(value = "Resends an email")
    void resendEmail() {
        $j(MainScreen).openEmailSendingScreen()

        def emailSubject = getUniqueName(TEST_SYNC_BASE_STRING)

        $j(EmailSendingScreen).with {
            fillTextField(subject, emailSubject)
            clickButton(sync)
            Selenide.sleep(4000)
        }
        $j(MainScreen).openEmailHistoryScreen()

        $j(EmailHistoryScreen).with {
            checkByCellsRecordIsDisplayed(emailSubject, SENT_STATUS, SENDING_MESSAGE_TABLE_J_TEST_ID)
            selectRowInTableByText(emailSubject, SENDING_MESSAGE_TABLE_J_TEST_ID)
            clickButton(resendEmailBtn)
            $j(EmailResendDialog).with {
                checkFilledTextField(emailTextField, TEST_TO_EMAIL_ADDRESS)
                checkFilledTextField(ccTextField, TEST_CC_EMAIL_ADDRESS)
                checkFilledTextField(bccTextField, TEST_BCC_EMAIL_ADDRESS)
                clickButton(resendEmailBtn)
            }
            checkNotificationCaptionAndDescription("Sent!", "Message was successfully resent")
            Selenide.sleep(3000)
            clickRefreshFilterButton()
            checkRecordCount(emailSubject, SENT_STATUS, SENDING_MESSAGE_TABLE_J_TEST_ID, 2)
        }
    }

    private static void clickRefreshFilterButton() {
        SelenideElement filterButton = $(byClassName("v-button-friendly"))
        filterButton.click()
    }

    @Test
    @Order(4)
    @DisplayName(value = "Sends asynchronized email without attachments with manual running")
    void asyncManualEmailSending() {
        $j(MainScreen).openEmailSendingScreen()
        def emailSubject = getUniqueName(TEST_ASYNC_BASE_STRING)

        $j(EmailSendingScreen) with {
            fillTextField(subject, emailSubject)
            clickButton(async)
        }

        $j(MainScreen).openEmailHistoryScreen()

        $j(EmailHistoryScreen).with {
            checkByCellsRecordIsDisplayed(emailSubject, QUEUE_STATUS, SENDING_MESSAGE_TABLE_J_TEST_ID)
            selectRowInTableByText(emailSubject, SENDING_MESSAGE_TABLE_J_TEST_ID)
            checkFilledTextField(attemptsMade, '0')
        }

        $j(MainScreen).openEmailSendingScreen()

        $j(EmailSendingScreen).with {
            clickButton(send)
            Selenide.sleep(10000)
        }
        $j(MainScreen).openEmailHistoryScreen()

        $j(EmailHistoryScreen).with {
            checkByCellsRecordIsDisplayed(emailSubject, SENT_STATUS, SENDING_MESSAGE_TABLE_J_TEST_ID)
        }
    }

    @Test
    @Order(5)
    @DisplayName(value = "Sends asynchronized email without attachments with scheduler")
    void asyncScheduleEmailSending() {
        $j(MainScreen).openEmailSendingScreen()
        def emailSubject = getUniqueName(TEST_ASYNC_BASE_STRING)

        $j(EmailSendingScreen).with {
            fillTextField(subject, emailSubject)
            clickButton(async)
        }

        $j(MainScreen).openEmailHistoryScreen()

        $j(EmailHistoryScreen).with {
            checkByCellsRecordIsDisplayed(emailSubject, QUEUE_STATUS, SENDING_MESSAGE_TABLE_J_TEST_ID)
            selectRowInTableByText(emailSubject, SENDING_MESSAGE_TABLE_J_TEST_ID)
            checkFilledTextField(attemptsMade, '0')
            Selenide.sleep(80000)
            clickRefreshFilterButton()

            checkByCellsRecordIsDisplayed(emailSubject, SENT_STATUS, SENDING_MESSAGE_TABLE_J_TEST_ID)
            selectRowInTableByText(emailSubject, SENDING_MESSAGE_TABLE_J_TEST_ID)
            checkFilledTextField(attemptsMade, '1')
        }
    }
}
