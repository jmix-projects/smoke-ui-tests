package io.jmix.tests.ui.test.smoke.crud.emailtemplates

import io.jmix.tests.ui.screen.administration.emailtemplates.EmailTemplateBrowse
import io.jmix.tests.ui.screen.administration.emailtemplates.EmailTemplateEditor

import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.EmailTemplatesHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class EmailTemplatesSmokeUITest extends BaseUiTest implements EmailTemplatesHelper {
    public static final String EMAIL_TEMPLATES_TABLE_J_TEST_ID = "emailTemplatesTable"
    public static final String TEST_TEMPLATE_BASE_NAME = "Test template"
    public static final String TEST_TEMPLATE_BASE_CODE = "testtemplate"

    @BeforeEach
    void openEmailTemplateBrowse() {
        $j(MainScreen).openEmailTemplateBrowse()
    }

    @Test
    @DisplayName("Creates an email template")
    void createEmailTemplate() {
        def tempname = getUniqueName(TEST_TEMPLATE_BASE_NAME)
        def tempcode = getUniqueName(TEST_TEMPLATE_BASE_CODE)

        createTemplate(tempname, tempcode)
        $j(EmailTemplateBrowse).with {
            checkRecordIsDisplayed(tempname, EMAIL_TEMPLATES_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Edits an email template")
    void editEmailTemplate() {
        def tempname = getUniqueName(TEST_TEMPLATE_BASE_NAME)
        def tempEditedName = getUniqueName(TEST_TEMPLATE_BASE_NAME)
        def tempcode = getUniqueName(TEST_TEMPLATE_BASE_CODE)

        createTemplate(tempname, tempcode)
        selectRowAndClickButton(tempname, $j(EmailTemplateBrowse).editBtn)
        $j(EmailTemplateEditor).with {
            fillTextField(name, tempEditedName)
            clickButton(ok)
        }
        $j(EmailTemplateBrowse).with {
            checkRecordIsDisplayed(tempEditedName, EMAIL_TEMPLATES_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Removes an email template")
    void removeEmailTemplate() {
        def tempname = getUniqueName(TEST_TEMPLATE_BASE_NAME)
        def tempcode = getUniqueName(TEST_TEMPLATE_BASE_CODE)

        createTemplate(tempname, tempcode)
        selectRowAndClickButton(tempname, $j(EmailTemplateBrowse).removeBtn)
        clickYesInAConfirmationDialog()
        $j(EmailTemplateBrowse).with {
            checkRecordIsNotDisplayed(tempname, EMAIL_TEMPLATES_TABLE_J_TEST_ID)
        }
    }
}
