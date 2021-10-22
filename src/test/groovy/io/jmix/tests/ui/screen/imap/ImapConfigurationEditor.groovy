package io.jmix.tests.ui.screen.imap

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.ComboBox
import io.jmix.masquerade.component.TextField

class ImapConfigurationEditor extends Composite<ImapConfigurationEditor> {

    @Wire
    TextField name

    @Wire
    TextField host

    @Wire
    TextField port

    @Wire
    ComboBox secureMode

    @Wire
    TextField username

    @Wire
    TextField password

    @Wire
    TextField jmixFlagTextField

    @Wire
    Button checkConnectionBtn

    @Wire(path = "windowCommitAndClose")
    Button ok

    void selectSSLTLSecureConnection() {
        secureMode.openOptionsPopup().select("SSL/TLS")
    }
}
