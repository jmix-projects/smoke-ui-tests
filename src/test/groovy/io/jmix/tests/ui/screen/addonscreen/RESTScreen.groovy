package io.jmix.tests.ui.screen.addonscreen

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.TextField

class RESTScreen extends Composite<RESTScreen> {

    @Wire
    TextField restField
}
