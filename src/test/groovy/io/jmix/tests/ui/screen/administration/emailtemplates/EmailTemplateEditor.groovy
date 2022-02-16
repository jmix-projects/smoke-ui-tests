package io.jmix.tests.ui.screen.administration.emailtemplates

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField
import io.jmix.tests.ui.test.utils.traits.TableActionsTrait

class EmailTemplateEditor extends Composite<EmailTemplateEditor> implements TableActionsTrait {

    @Wire
    TextField name

    @Wire
    TextField code

    @Wire
    TextField subject

    @Wire
    TextField from

    @Wire
    TextField to

    @Wire
    TextField cc

    @Wire
    TextField bcc

    @Wire(path = "windowCommitAndClose")
    Button ok

}
