package io.jmix.tests.ui.screen.administration.quartzjobs.editor

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.ComboBox
import io.jmix.masquerade.component.TextField

import static io.jmix.masquerade.Selectors.withText

class JobEditor extends Composite<JobEditor> {

    @Wire
    TextField jobNameField

    @Wire
    ComboBox jobClassField

    @Wire
    TextField jobDescriptionField

    @Wire(path = "commitAndCloseBtn")
    Button ok

    void selectCustomJob(String type) {
        jobClassField.openOptionsPopup().select(withText(type))
    }
}
