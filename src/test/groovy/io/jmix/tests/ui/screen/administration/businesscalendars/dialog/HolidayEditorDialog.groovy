package io.jmix.tests.ui.screen.administration.businesscalendars.dialog

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.ComboBox
import io.jmix.masquerade.component.OptionsGroup
import io.jmix.masquerade.component.TextField

import static io.jmix.masquerade.Selectors.withText

class HolidayEditorDialog extends Composite<HolidayEditorDialog> {

    @Wire
    ComboBox holidayTypeComboBox

    @Wire
    OptionsGroup dayOfWeekCheckboxGroup

    @Wire
    TextField descriptionField

    @Wire(path = ["dialog_buscal_HolidayModel.edit", "commitAndCloseBtn"])
    Button ok

    void selectDayOfWeekHolidayType(String type) {
        holidayTypeComboBox.openOptionsPopup().select(withText(type))
    }

    void dayOfWeekCheckboxGroup(String type) {
        dayOfWeekCheckboxGroup.select(type)
    }
}
