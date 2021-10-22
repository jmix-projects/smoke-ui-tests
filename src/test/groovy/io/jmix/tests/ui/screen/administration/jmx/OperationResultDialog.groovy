package io.jmix.tests.ui.screen.administration.jmx

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button

class OperationResultDialog extends Composite<OperationResultDialog> {

    @Wire(path = ["dialog_ui_MBeanOperationResultScreen", "closeBtn"])
    Button closeBtn
}
