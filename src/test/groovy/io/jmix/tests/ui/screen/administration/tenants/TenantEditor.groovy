package io.jmix.tests.ui.screen.administration.tenants

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField

class TenantEditor extends Composite<TenantEditor> {

    @Wire
    TextField tenantIdField

    @Wire
    TextField nameField

    @Wire(path = "commitAndCloseBtn")
    Button ok

}
