package io.jmix.tests.ui.screen.application.customer

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.ComboBox
import io.jmix.masquerade.component.TextField

class CustomerEditor extends Composite<CustomerEditor> {
    @Wire
    TextField nameField

    @Wire
    TextField emailField

    @Wire
    ComboBox gradeField

    @Wire(path = "commitAndCloseBtn")
    Button ok
}
