package io.jmix.tests.ui.screen.administration.emailhistory.dialog

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField

class EmailResendDialog extends Composite<EmailResendDialog> {

    @Wire
    TextField emailTextField

    @Wire
    TextField ccTextField

    @Wire
    TextField bccTextField

    @Wire(path = ["dialog_ResendMessage", "resendEmailBtn"])
    Button resendEmailBtn

}
