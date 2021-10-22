package io.jmix.tests.ui.screen.administration.emailtemplates

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.PopupButton
import io.jmix.tests.ui.test.utils.TableActionsTrait

import static io.jmix.masquerade.Conditions.ENABLED

class EmailTemplateBrowse extends Composite<EmailTemplateBrowse> implements TableActionsTrait {

    @Wire
    PopupButton createBtn

    @Wire
    Button removeBtn

    void createFromDesignerEditor() {
        createBtn.shouldBe(ENABLED).click("From designer")
    }

    void createFromReportEditor() {
        createBtn.shouldBe(ENABLED).click("From report")
    }
}
