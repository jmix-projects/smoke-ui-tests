package io.jmix.tests.ui.screen.administration.jmx

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField
import io.jmix.tests.ui.test.utils.TableActionsTrait

class JMXConsoleScreen extends Composite<JMXConsoleScreen> implements TableActionsTrait {
    @Wire
    TextField objectNameField

    @Wire
    Button refresh

    @Wire
    Button inspect
}
