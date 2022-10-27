package io.jmix.tests.ui.test.smoke.crud.localfilestorage

import io.jmix.tests.ui.screen.addonscreen.localfilestorage.EntityWithFileEditor
import io.jmix.tests.ui.screen.addonscreen.localfilestorage.EntityWithFileScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.LocalFileStorageHelper
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

import static io.jmix.masquerade.Selectors.$j

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LocalFileStorageSmokeUITest extends BaseUiTest implements LocalFileStorageHelper {

    public static final String LOCAL_FS_TABLE_J_TEST_ID = "entityWithFilesTable"
    public static final String CREATE_FILE_NAME = "LocalfsDocCreate.txt"
    public static final String EDIT_FILE_NAME = "LocalfsDocEdit.txt"
    public static final String DELETE_FILE_NAME = "LocalfsDocDelete.txt"
    public localFilesArray = []

    @BeforeEach
    void openEmailTemplateBrowse() {
        $j(MainScreen).openEntityWithFileScreen()
    }

    @AfterAll
    void afterAll() {
        $j(MainScreen).openEntityWithFileScreen()
        localFilesArray.forEach(localFile -> {
            removeAllLocalFiles(localFile as String, LOCAL_FS_TABLE_J_TEST_ID)
        })
    }

    @Test
    @DisplayName("Uploads file to browser")
    void checkFileUploading() {
        def fileName = getUniqueName(CREATE_FILE_NAME)
        localFilesArray.add(fileName)

        $j(EntityWithFileScreen).with {
            clickButton(createBtn)
        }

        $j(EntityWithFileEditor).with {
            generateAndUploadFileToEditor(CREATE_FILE_NAME, fileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileScreen).with {
            checkRecordIsDisplayed(fileName, LOCAL_FS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Edits uploaded file")
    void editFileUploading() {
        def fileName = getUniqueName(CREATE_FILE_NAME)
        def editedFileName = getUniqueName(EDIT_FILE_NAME)
        localFilesArray.add(editedFileName)

        $j(EntityWithFileScreen).with {
            clickButton(createBtn)
        }

        $j(EntityWithFileEditor).with {
            generateAndUploadFileToEditor(CREATE_FILE_NAME, fileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileScreen).with {
            selectRowInTableByText(fileName, LOCAL_FS_TABLE_J_TEST_ID)
            clickButton(editBtn)
        }

        $j(EntityWithFileEditor).with {
            generateAndUploadFileToEditor(EDIT_FILE_NAME, editedFileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileScreen).with {
            checkRecordIsDisplayed(editedFileName, LOCAL_FS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Removes uploaded file")
    void removeFileUploading() {
        def deleteFileName = getUniqueName(DELETE_FILE_NAME)

        $j(EntityWithFileScreen).with {
            clickButton(createBtn)
        }

        $j(EntityWithFileEditor).with {
            generateAndUploadFileToEditor(DELETE_FILE_NAME, deleteFileName)
            clickButton(okBtn)
        }

        $j(EntityWithFileScreen).with {
            checkRecordIsDisplayed(deleteFileName, LOCAL_FS_TABLE_J_TEST_ID)
        }

        $j(EntityWithFileScreen).with {
            selectRowInTableByText(deleteFileName, LOCAL_FS_TABLE_J_TEST_ID)
            clickButton(removeBtn)
            clickYesInAConfirmationDialog()
            checkRecordIsNotDisplayed(deleteFileName, LOCAL_FS_TABLE_J_TEST_ID)
        }
    }
}

