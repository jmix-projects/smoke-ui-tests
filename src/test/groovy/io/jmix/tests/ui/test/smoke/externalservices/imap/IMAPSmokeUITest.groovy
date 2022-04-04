package io.jmix.tests.ui.test.smoke.externalservices.imap

import io.jmix.tests.ui.screen.imap.ImapConfigurationBrowse
import io.jmix.tests.ui.screen.imap.ImapConfigurationEditor
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Conditions.EDITABLE
import static io.jmix.masquerade.Conditions.REQUIRED
import static io.jmix.masquerade.Conditions.VISIBLE
import static io.jmix.masquerade.Selectors.$j

class IMAPSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String IMAP_CONFIG_TABLE_J_TEST_ID = "mailBoxesTable"
    public static final String IMAP_CONFIGURATION_NAME = "Haulmont test"
    public static final String IMAP_HOST = "host"
    public static final String IMAP_PORT = "port"
    public static final String IMAP_USERNAME = "username"
    public static final String IMAP_PASSWORD = "password"
    public static final String IMAP_SUCCESS_CONNECT_NOTIFICATION_TEXT = "Connection is successfully established"

    @Test
    @DisplayName("Creates IMAP configuration")
    void createImapConfigTest() {
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
            checkNotificationCaption(IMAP_SUCCESS_CONNECT_NOTIFICATION_TEXT)
            jmixFlagTextField.shouldBe(VISIBLE, EDITABLE, REQUIRED)
            fillTextField(jmixFlagTextField, "test")
            clickButton(ok)
        }

        $j(ImapConfigurationBrowse).with {
            checkRecordIsDisplayed(IMAP_CONFIGURATION_NAME, IMAP_CONFIG_TABLE_J_TEST_ID)
        }
    }
}
