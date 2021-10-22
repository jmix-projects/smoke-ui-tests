package io.jmix.tests.ui.screen.addonscreen

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField

class EmailSendingScreen extends Composite<EmailSendingScreen> {

    @Wire
    Button sendEmail

    @Wire
    TextField subjectField
}
