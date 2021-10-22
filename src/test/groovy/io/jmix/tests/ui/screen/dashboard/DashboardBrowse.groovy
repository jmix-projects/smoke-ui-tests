package io.jmix.tests.ui.screen.dashboard

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.test.utils.TableActionsTrait

class DashboardBrowse extends Composite<DashboardBrowse> implements TableActionsTrait {

    @Wire
    Button createBtn

    @Wire
    Button removeBtn
}
