package io.jmix.tests.ui.screen.administration.emailhistory.dialog

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button

class EmailAttachmentDownloadDialog extends Composite<EmailAttachmentDownloadDialog> {

    @Wire(path = "lookupSelectAction")
    Button select
}
