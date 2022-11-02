package io.jmix.tests.ui.test.smoke.crud.localfilestorage

import com.codeborne.selenide.Selenide
import io.jmix.tests.ui.screen.addonscreen.localfilestorage.EntityWithFileEditor
import io.jmix.tests.ui.screen.addonscreen.localfilestorage.EntityWithFileBrowse
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.LocalFileStorageHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class LocalFileStorageSmokeUITest extends BaseUiTest implements LocalFileStorageHelper {

    public static final String LOCAL_FS_TABLE_J_TEST_ID = "entityWithFilesTable"
    public static final String CREATE_FILE_NAME = "LocalfsDocCreate.txt"
    public static final String EDIT_FILE_NAME = "LocalfsDocEdit.txt"
    public static final String DELETE_FILE_NAME = "LocalfsDocDelete.txt"

    @BeforeEach
    void openEmailTemplateBrowse() {
        $j(MainScreen).openEntityWithFileBrowse()
    }

    @Test
    @DisplayName("Create file to browser")
    void createFileUploading() {
        def fileName = getUniqueName(CREATE_FILE_NAME)

        $j(EntityWithFileBrowse).with {
            clickButton(createBtn)
            Selenide.sleep(1000)
        }

        $j(EntityWithFileEditor).with {
            generateAndUploadFileToEditor(CREATE_FILE_NAME, fileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileBrowse).with {
            checkRecordIsDisplayed(fileName, LOCAL_FS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Edits uploaded file")
    void editFileUploading() {
        def fileName = getUniqueName(CREATE_FILE_NAME)
        def editedFileName = getUniqueName(EDIT_FILE_NAME)

        $j(EntityWithFileBrowse).with {
            clickButton(createBtn)
            Selenide.sleep(1000)
        }

        $j(EntityWithFileEditor).with {
            generateAndUploadFileToEditor(CREATE_FILE_NAME, fileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileBrowse).with {
            selectRowInTableByText(fileName, LOCAL_FS_TABLE_J_TEST_ID)
            clickButton(editBtn)
        }

        $j(EntityWithFileEditor).with {
            generateAndUploadFileToEditor(EDIT_FILE_NAME, editedFileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileBrowse).with {
            checkRecordIsDisplayed(editedFileName, LOCAL_FS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Removes uploaded file")
    void removeFileUploading() {
        def deleteFileName = getUniqueName(DELETE_FILE_NAME)

        $j(EntityWithFileBrowse).with {
            clickButton(createBtn)
        }

        $j(EntityWithFileEditor).with {
            generateAndUploadFileToEditor(DELETE_FILE_NAME, deleteFileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileBrowse).with {
            checkRecordIsDisplayed(deleteFileName, LOCAL_FS_TABLE_J_TEST_ID)
        }

        $j(EntityWithFileBrowse).with {
            selectRowInTableByText(deleteFileName, LOCAL_FS_TABLE_J_TEST_ID)
            clickButton(removeBtn)
            clickYesInAConfirmationDialog()
            checkRecordIsNotDisplayed(deleteFileName, LOCAL_FS_TABLE_J_TEST_ID)
        }
    }
}

