package io.jmix.tests.ui.test.utils.helpers

import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.screen.administration.emailtemplates.EmailTemplateBrowse
import io.jmix.tests.ui.screen.administration.emailtemplates.EmailTemplateEditor

import static io.jmix.masquerade.Selectors.$j

trait EmailTemplatesHelper extends UiHelper {

    void createTemplate(String tempname, tempcode){
        $j(EmailTemplateBrowse).with {
            createFromDesignerEditor()
        }

        $j(EmailTemplateEditor).with {

            fillTextField(name, tempname)
            fillTextField(code, tempcode)

            fillTextField(subject, "test subject")
            fillTextField(from, "1@1.test")
            fillTextField(to, "2@2.test")
            fillTextField(cc, "3@3.test")
            fillTextField(bcc, "4@4.test")
            clickButton(ok)
        }
    }

    def selectRowAndClickButton(String name, Button button) {
        $j(EmailTemplateBrowse).with {
            checkRecordIsDisplayed(name, EMAIL_TEMPLATES_TABLE_J_TEST_ID)
            selectRowInTableByText(name, EMAIL_TEMPLATES_TABLE_J_TEST_ID)
            clickButton(button)
        }
    }
}