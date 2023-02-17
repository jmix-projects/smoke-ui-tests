package io.jmix.tests.ui.screen.system.dialog

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button

class JsonExportModeOptionDialog extends Composite<JsonExportModeOptionDialog> {

    @Wire(path = "optionDialog_ExportMode.CURRENT_PAGE")
    Button currentPage

    @Wire(path = "optionDialog_cancel")
    Button cancel
}
