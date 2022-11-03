package io.jmix.tests.ui.screen.administration.emailhistory

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField
import io.jmix.tests.ui.test.utils.traits.TableActionsTrait

class EmailHistoryBrowse extends Composite<EmailHistoryBrowse> implements TableActionsTrait {
    @Wire
    TextField datepart

    @Wire
    Button downloadAttachmentBtn

    @Wire(path = ["fg", "attemptsMade"])
    TextField attemptsMade

    @Wire
    Button resendEmailBtn
}
