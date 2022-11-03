package io.jmix.tests.ui.test.smoke.crud.webdav

import com.codeborne.selenide.Selenide
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.screen.administration.webdav.DocumentVersionDialog
import io.jmix.tests.ui.screen.administration.webdav.WebDAVDocumentBrowse
import io.jmix.tests.ui.screen.system.dialog.ConfirmationDialog
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.WebDAVHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Conditions.ENABLED
import static io.jmix.masquerade.Conditions.VISIBLE
import static io.jmix.masquerade.Conditions.caption
import static io.jmix.masquerade.Selectors.$j

class WebDAVSmokeUITest extends BaseUiTest implements WebDAVHelper {

    public static final String WEBDAV_DOCUMENTS_TABLE_J_TEST_ID = "webdavDocumentsTable"
    public static final String WEBDAV_DOCUMENT_VERSIONS_TABLE_J_TEST_ID = "webdavDocumentVersionsTable"
    public static final String FILENAME = "helloworld.txt"
    public static final String SHOW_VERSION_BTN_J_TEST_ID = "showVersion"
    public static final String DOCUMENT_IS_NOT_LOCKED_NOTIFICATION_CAPTION = "The document is not locked"

    @BeforeEach
    void beforeEachTest() {
        $j(MainScreen).openWebDAVDocumentBrowse()
    }

    @Test
    @DisplayName("Upload file to document browser")
    void checkFileUploading() {
        def fileName = getUniqueName(FILENAME)
        uploadFileToDocumentBrowser(fileName)

        $j(WebDAVDocumentBrowse).with {
            checkRecordIsDisplayed(fileName, WEBDAV_DOCUMENTS_TABLE_J_TEST_ID)
            $j(Button, SHOW_VERSION_BTN_J_TEST_ID)
                    .shouldBe(VISIBLE, ENABLED)
                    .shouldHave(caption("v1"))
        }
    }

    @Test
    @DisplayName("Check document version dialog window")
    void checksDocumentVersionDialog() {
        def fileName = getUniqueName(FILENAME)
        uploadFileToDocumentBrowser(fileName)

        $j(WebDAVDocumentBrowse).with {
            selectRowInTableByText(fileName, WEBDAV_DOCUMENTS_TABLE_J_TEST_ID)
            clickButton(manageVersionsBtn)
        }

        $j(DocumentVersionDialog).with {
            checkRecordIsDisplayed(fileName, WEBDAV_DOCUMENT_VERSIONS_TABLE_J_TEST_ID)
            clickButton(ok)
        }
    }

    @Test
    @DisplayName("Remove non-locked file")
    void removeNotLockedFile() {
        def fileName = getUniqueName(FILENAME)
        uploadFileToDocumentBrowser(fileName)

        $j(WebDAVDocumentBrowse).with {
            selectRowInTableByText(fileName, WEBDAV_DOCUMENTS_TABLE_J_TEST_ID)
            clickButton(removeBtn)
            Selenide.sleep(1000)
        }
        $j(ConfirmationDialog).with {
            confirmChanges()
        }

        checkNotificationCaption(DOCUMENT_IS_NOT_LOCKED_NOTIFICATION_CAPTION)
    }

    @Test
    @DisplayName("Remove locked file")
    void removeLockedFile() {
        def fileName = getUniqueName(FILENAME)
        uploadFileToDocumentBrowser(fileName)

        $j(WebDAVDocumentBrowse).with {
            selectRowInTableByText(fileName, WEBDAV_DOCUMENTS_TABLE_J_TEST_ID)
            clickButton(lockBtn)
            clickButton(removeBtn)
            Selenide.sleep(1000)
        }
        $j(ConfirmationDialog).with {
            confirmChanges()
        }
        $j(WebDAVDocumentBrowse).with {
            checkRecordIsNotDisplayed(fileName, WEBDAV_DOCUMENTS_TABLE_J_TEST_ID)
        }
    }

}

