package io.jmix.tests.ui.screen.administration.businesscalendars.editor.tab

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button

class HolidaysTab extends Composite<HolidaysTab> {

    @Wire
    Button create

    @Wire
    Button edit

    @Wire
    Button remove


}
