package io.jmix.tests.ui.test.smoke.crud.localfilestorage

import io.jmix.tests.ui.screen.addonscreen.localfilestorage.EntityWithFileEditor
import io.jmix.tests.ui.screen.addonscreen.localfilestorage.EntityWithFileScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.LocalFileStorageHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class LocalFileStorageSmokeUITest extends BaseUiTest implements LocalFileStorageHelper {

    public static final String LOCAL_FS_TABLE_J_TEST_ID = "entityWithFilesTable"
    public static final String FILENAME = "helloworld.txt"

    @BeforeEach
    void openEmailTemplateBrowse() {
        $j(MainScreen).openEntityWithFileScreen()
    }

    @Test
    @DisplayName("Uploads file to browser")
    void checkFileUploading() {
        def fileName = getUniqueName(FILENAME)

        $j(EntityWithFileScreen).with {
            clickButton(createBtn)
        }

        $j(EntityWithFileEditor).with {
            uploadFileToDocumentBrowser(fileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileScreen).with {
            checkRecordIsDisplayed(fileName, LOCAL_FS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Edits uploaded file")
    void editFileUploading() {
        def fileName = getUniqueName(FILENAME)
        def editedFileName = getUniqueName(FILENAME)

        $j(EntityWithFileScreen).with {
            clickButton(createBtn)
        }

        $j(EntityWithFileEditor).with {
            uploadFileToDocumentBrowser(fileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileScreen).with {
            selectRowInTableByText(fileName, LOCAL_FS_TABLE_J_TEST_ID)
            clickButton(editBtn)
        }

        $j(EntityWithFileEditor).with {
            uploadFileToDocumentBrowser(editedFileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileScreen).with {
            checkRecordIsDisplayed(editedFileName, LOCAL_FS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Removes uploaded file")
    void removeFileUploading() {
        def fileName = getUniqueName(FILENAME)

        $j(EntityWithFileScreen).with {
            clickButton(createBtn)
        }

        $j(EntityWithFileEditor).with {
            uploadFileToDocumentBrowser(fileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileScreen).with {
            checkRecordIsDisplayed(fileName, LOCAL_FS_TABLE_J_TEST_ID)
        }

        $j(EntityWithFileScreen).with {
            selectRowInTableByText(fileName, LOCAL_FS_TABLE_J_TEST_ID)
            clickButton(removeBtn)
            clickYesInAConfirmationDialog()
            checkRecordIsNotDisplayed(fileName, LOCAL_FS_TABLE_J_TEST_ID)
        }
    }
}

