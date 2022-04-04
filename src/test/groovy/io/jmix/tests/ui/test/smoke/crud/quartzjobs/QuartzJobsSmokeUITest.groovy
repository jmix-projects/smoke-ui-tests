package io.jmix.tests.ui.test.smoke.crud.quartzjobs

import io.jmix.tests.ui.screen.administration.quartzjobs.browse.QuartzJobsBrowse
import io.jmix.tests.ui.screen.administration.quartzjobs.editor.JobEditor
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class  QuartzJobsSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String QUARTZ_JOBS_TABLE_J_TEST_ID = "jobModelsTable"
    public static final String QUARTZ_JOBS_DESCRIPTION = "this is the description"
    public static final String QUARTZ_CUSTOM_JOB = "com.company.samplesales.app.CustomJob"
    public static final String QUARTZ_EXECUTE_JOB_NOTIFICATION_TEXT = " triggered for execution"

    @BeforeEach
    void openQuartzJobsBrowse() {
        $j(MainScreen).openQuartzJobsBrowse()
    }

    @Test
    @DisplayName("Creates and executes a job")
    void createAndExecuteQuartzJob() {
        $j(QuartzJobsBrowse).with {
            clickButton(createBtn)
        }

        def nameJob = getUniqueName("AutoJob ")
        $j(JobEditor).with {
            fillTextField(jobNameField, nameJob)
            selectCustomJob(QUARTZ_CUSTOM_JOB)
            fillTextField(jobDescriptionField, QUARTZ_JOBS_DESCRIPTION)
            clickButton(ok)
        }

        def notification = "Job " + nameJob + QUARTZ_EXECUTE_JOB_NOTIFICATION_TEXT
        $j(QuartzJobsBrowse).with {
            selectRowInTableByText(nameJob, QUARTZ_JOBS_TABLE_J_TEST_ID)
            clickButton(executeNowBtn)
            checkNotificationDescription(notification)
        }
    }
}
