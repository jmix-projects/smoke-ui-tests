package io.jmix.tests.ui.test.smoke.crud.bpm

import io.jmix.tests.ui.screen.bpm.ModelerScreen
import io.jmix.tests.ui.screen.bpm.ProcessDefinitionBrowse
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Conditions.DISABLED
import static io.jmix.masquerade.Conditions.ENABLED
import static io.jmix.masquerade.Conditions.value
import static io.jmix.masquerade.Selectors.$j

@Disabled
class BPMSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String PROCESS_BASE_ID = "process"
    public static final String PROCESS_BASE_NAME = "Process"
    public static final String PROCESS_DEPLOYED_NOTIFICATION_TEXT = "Process deployed"

    def createAndDeployProcess(String processId, String processName) {
        $j(ModelerScreen).with {
            checkModelerIsDisplayed()
            fillTextField(businessId, processId)
            fillTextField(name, processName)
            clickButton(deployBtn)
        }

        clickYesInAConfirmationDialog()
    }

    @BeforeEach
    void openModelerScreen() {
        $j(MainScreen).openModelerScreen()
    }

    @Test
    @DisplayName("Create and deploy a process")
    void createBPMProcess() {
        def processName = getUniqueName(PROCESS_BASE_NAME)
        def processId = getUniqueName(PROCESS_BASE_ID)

        createAndDeployProcess(processId, processName)

        checkNotificationCaption(PROCESS_DEPLOYED_NOTIFICATION_TEXT)

        $j(MainScreen).openProcessDefinitionBrowse()

        $j(ProcessDefinitionBrowse).with {
            checkRecordIsDisplayed(processId)
        }
    }

    @Test
    @DisplayName("Edit a deployed process")
    void editBPMProcess() {
        def processName = getUniqueName(PROCESS_BASE_NAME)
        def processId = getUniqueName(PROCESS_BASE_ID)
        def processEditedId = getUniqueName(PROCESS_BASE_ID)

        createAndDeployProcess(processId, processName)
        checkNotificationCaption(PROCESS_DEPLOYED_NOTIFICATION_TEXT)

        $j(MainScreen).openProcessDefinitionBrowse()

        $j(ProcessDefinitionBrowse).with {
            checkRecordIsDisplayed(processId)
            checkButtons(DISABLED)
            selectRowInTableByText(processId)
            checkButtons(ENABLED)
            clickButton(openInModelerBtn)
        }

        $j(ModelerScreen).with {
            checkModelerIsDisplayed()
            businessId.shouldHave(value(processId))
            name.shouldHave(value(processName))
            fillTextField(businessId, processEditedId)
            clickButton(deployBtn)
        }

        clickYesInAConfirmationDialog()
        checkNotificationCaption(PROCESS_DEPLOYED_NOTIFICATION_TEXT)

        $j(MainScreen).openProcessDefinitionBrowse()

        $j(ProcessDefinitionBrowse).with {
            checkRecordIsDisplayed(processEditedId)
            checkRecordIsNotDisplayed(processId)
        }
    }
}
