package io.jmix.tests.ui.test.smoke.crud.bpm

import io.jmix.tests.ui.screen.system.dialog.UnsavedChangesBPMDialog
import io.jmix.tests.ui.screen.bpm.BPMNModelDraftsBrowser
import io.jmix.tests.ui.screen.bpm.ModelerScreen
import io.jmix.tests.ui.screen.bpm.ProcessDefinitionBrowse
import io.jmix.tests.ui.screen.bpm.ProcessDefinitionEditor
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Conditions.*
import static io.jmix.masquerade.Selectors.$j

class BPMSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String PROCESS_BASE_ID = "process"
    public static final String PROCESS_BASE_NAME = "Process"
    public static final String PROCESS_DEPLOYED_NOTIFICATION_TEXT = "Process deployed"
    public static final String DRAFT_NAME = "Draft Test"
    public static final String CONTENT_STORAGES_TABLE_J_TEST_ID = "contentStoragesTable"

    @BeforeEach
    void openModelerScreen() {
        $j(MainScreen).openModelerScreen()
    }

    @Test
    @DisplayName("Create and deploy a process")
    void createBPMProcess() {
        createAndDeployProcess()

        checkNotificationCaption(PROCESS_DEPLOYED_NOTIFICATION_TEXT)

        $j(MainScreen).openProcessDefinitionBrowse()

        $j(ProcessDefinitionBrowse).with {
            checkRecordIsDisplayed(PROCESS_BASE_ID)

            selectRowInTableByText(PROCESS_BASE_NAME)
            clickButton(startProcessBtn)
            clickButton(startProcessBtnDialog)
        }
        discardUnsavedChanges()
        closeTab()
    }

    @Test
    @DisplayName("Edit a deployed process")
    void editBPMProcess() {
        createAndDeployProcess()
        checkNotificationCaption(PROCESS_DEPLOYED_NOTIFICATION_TEXT)

        $j(MainScreen).openProcessDefinitionBrowse()

        $j(ProcessDefinitionBrowse).with {
            checkRecordIsDisplayed(PROCESS_BASE_ID)
            checkButtons(DISABLED)
            selectRowInTableByText(PROCESS_BASE_ID)
            checkButtons(ENABLED)
            clickButton(openInModelerBtn)
        }
        discardUnsavedChanges()

        $j(ModelerScreen).with {
            checkModelerIsDisplayed()
            businessId.shouldHave(value(PROCESS_BASE_ID))
            name.shouldHave(value(PROCESS_BASE_NAME))
            fillTextField(businessId, PROCESS_BASE_ID)
            clickButton(saveDraftBtn)
            fillTextField(nameField, DRAFT_NAME)
            clickButton(ok)
            clickButton(openDraftBtn)

            $j(BPMNModelDraftsBrowser).with {
                checkRecordIsDisplayed(DRAFT_NAME, CONTENT_STORAGES_TABLE_J_TEST_ID)
                selectRowInTableByText(DRAFT_NAME, CONTENT_STORAGES_TABLE_J_TEST_ID)
                clickButton(lookupSelectAction)
            }

            clickButton(deployBtn)
        }

        clickYesInAConfirmationDialog()
        checkNotificationCaption(PROCESS_DEPLOYED_NOTIFICATION_TEXT)

        $j(MainScreen).openProcessDefinitionBrowse()

        $j(ProcessDefinitionBrowse).with {
            checkRecordIsDisplayed(PROCESS_BASE_ID)
        }
        discardUnsavedChanges()
        closeTab()
        discardUnsavedChanges()
    }

    @Test
    @DisplayName("Remove a deployed process")
    void removeBPMProcess() {
        createAndDeployProcess()

        $j(MainScreen).openProcessDefinitionBrowse()

        $j(ProcessDefinitionBrowse).with {
            selectRowInTableByText(PROCESS_BASE_ID)
            clickButton(viewDetailsBtn)
        }

        $j(ProcessDefinitionEditor).with {
            clickButton(deleteDeploymentBtn)
            clickYesInAConfirmationDialog()
        }
    }

    def createAndDeployProcess() {
        $j(ModelerScreen).with {
            checkModelerIsDisplayed()
            clickButton(deployBtn)
        }

        clickYesInAConfirmationDialog()
    }

    static def discardUnsavedChanges() {
        $j(ProcessDefinitionBrowse).with {
            closeTab()
            sleep(200)
            $j(UnsavedChangesBPMDialog).with {
                if (doNotSave.is(VISIBLE)) {
                    doNotSave.click()
                }
            }
        }
    }
}
