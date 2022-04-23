package io.jmix.tests.ui.screen.addonscreen.email

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField

class EmailSendingScreen extends Composite<EmailSendingScreen> {

    @Wire
    Button sync

    @Wire
    Button async

    @Wire
    Button syncAttach

    @Wire
    Button syncMultiAttach

    @Wire
    Button send

    @Wire
    TextField subject
}
