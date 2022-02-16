package io.jmix.tests.ui.screen.dashboard

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField

class DashboardEditor extends Composite<DashboardEditor> {

    @Wire
    TextField title

    @Wire
    TextField code

    @Wire(path = "okBtn")
    Button ok

}
