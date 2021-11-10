package io.jmix.tests.ui.screen.administration.webdav

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.test.utils.TableActionsTrait

class DocumentVersionDialog extends Composite<DocumentVersionDialog> implements TableActionsTrait {

    @Wire(path = "commitAndCloseBtn")
    Button ok
}
