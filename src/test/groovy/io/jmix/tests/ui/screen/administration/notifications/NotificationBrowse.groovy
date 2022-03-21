package io.jmix.tests.ui.screen.administration.notifications

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.test.utils.traits.TableActionsTrait

class NotificationBrowse extends Composite<NotificationBrowse> implements TableActionsTrait {

    @Wire
    Button createBtn

    @Wire
    Button editBtn

    @Wire
    Button removeBtn

}
