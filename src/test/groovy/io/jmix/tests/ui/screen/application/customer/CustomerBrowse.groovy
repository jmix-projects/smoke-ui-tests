package io.jmix.tests.ui.screen.application.customer

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.test.utils.TableActionsTrait

class CustomerBrowse extends Composite<CustomerBrowse> implements TableActionsTrait {
    @Wire
    Button createBtn

    @Wire
    Button editBtn

    @Wire
    Button removeBtn

    @Wire
    Button excelExport

    @Wire
    Button jsonExport
}
