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
    public static final String QUARTZ_JOB_DESCRIPTION = "This is the Quartz job description"
    public static final String QUARTZ_CUSTOM_JOB = "com.company.samplesales.app.CustomJob"
    public static final String QUARTZ_CUSTOM_JOB_FOR_EDIT = "com.company.samplesales.app.CustomJobForEdit"
    public static final String QUARTZ_EXECUTE_JOB_NOTIFICATION_TEXT = " triggered for execution"
    public static final String QUARTZ_DELETE_JOB_NOTIFICATION_TEXT = " deleted"

    @BeforeEach
    void openQuartzJobsBrowse() {
        $j(MainScreen).openQuartzJobsBrowse()
    }

    @Test
    @DisplayName("Create and execute Quartz job")
    void createAndExecuteQuartzJob() {
        $j(QuartzJobsBrowse).with {
            clickButton(createBtn)
        }
        def jobName = getUniqueName("JobForCreating ")
        $j(JobEditor).with {
            fillTextField(jobNameField, jobName)
            selectCustomJob(QUARTZ_CUSTOM_JOB)
            fillTextField(jobDescriptionField, QUARTZ_JOB_DESCRIPTION)
            clickButton(ok)
        }
        def notificationTextAboutExecution = "Job " + jobName + QUARTZ_EXECUTE_JOB_NOTIFICATION_TEXT
        $j(QuartzJobsBrowse).with {
            selectRowInTableByText(jobName, QUARTZ_JOBS_TABLE_J_TEST_ID)
            clickButton(executeNowBtn)
            checkNotificationDescription(notificationTextAboutExecution)
        }
    }

    @Test
    @DisplayName("Edit and execute Quartz job")
    void editAndExecuteQuartzJob() {
        $j(QuartzJobsBrowse).with {
            clickButton(createBtn)
        }
        def jobName = getUniqueName("JobForEditing ")
        $j(JobEditor).with {
            fillTextField(jobNameField, jobName)
            selectCustomJob(QUARTZ_CUSTOM_JOB)
            fillTextField(jobDescriptionField, QUARTZ_JOB_DESCRIPTION)
            clickButton(ok)
        }
        $j(QuartzJobsBrowse).with {
            selectRowInTableByText(jobName, QUARTZ_JOBS_TABLE_J_TEST_ID)
            clickButton(editBtn)
        }
        def editedJobName = getUniqueName("JobForEditing - edited ")
        $j(JobEditor).with {
            fillTextField(jobNameField, editedJobName)
            selectCustomJob(QUARTZ_CUSTOM_JOB_FOR_EDIT)
            fillTextField(jobDescriptionField, QUARTZ_JOB_DESCRIPTION + getUniqueName("edited "))
            clickButton(ok)
        }
        def notificationTextAboutExecution = "Job " + editedJobName + QUARTZ_EXECUTE_JOB_NOTIFICATION_TEXT
        $j(QuartzJobsBrowse).with {
            selectRowInTableByText(editedJobName, QUARTZ_JOBS_TABLE_J_TEST_ID)
            clickButton(executeNowBtn)
            checkNotificationDescription(notificationTextAboutExecution)
        }
    }

    @Test
    @DisplayName("Remove Quartz job")
    void removeQuartzJob() {
        $j(QuartzJobsBrowse).with {
            clickButton(createBtn)
        }
        def jobName = getUniqueName("JobForDeleting ")
        $j(JobEditor).with {
            fillTextField(jobNameField, jobName)
            selectCustomJob(QUARTZ_CUSTOM_JOB)
            fillTextField(jobDescriptionField, QUARTZ_JOB_DESCRIPTION)
            clickButton(ok)
        }
        def notificationTextAboutDeleting = "Job " + jobName + QUARTZ_DELETE_JOB_NOTIFICATION_TEXT
        $j(QuartzJobsBrowse).with {
            selectRowInTableByText(jobName, QUARTZ_JOBS_TABLE_J_TEST_ID)
            clickButton(removeBtn)
            clickButton(optionDialog_yes)
            checkNotificationDescription(notificationTextAboutDeleting)
        }
    }
}