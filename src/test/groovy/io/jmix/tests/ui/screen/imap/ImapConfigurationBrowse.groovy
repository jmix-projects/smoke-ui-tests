package io.jmix.tests.ui.screen.imap

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.test.utils.traits.TableActionsTrait

class ImapConfigurationBrowse extends Composite<ImapConfigurationBrowse> implements TableActionsTrait {

    @Wire
    Button createBtn

}
