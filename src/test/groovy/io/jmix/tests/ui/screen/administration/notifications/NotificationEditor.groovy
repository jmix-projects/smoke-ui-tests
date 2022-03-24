package io.jmix.tests.ui.screen.administration.notifications

import com.codeborne.selenide.SelenideElement
import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.ComboBox
import io.jmix.masquerade.component.OptionsGroup
import io.jmix.masquerade.component.TextField

import static com.codeborne.selenide.Selenide.$
import static io.jmix.masquerade.Selectors.*

class NotificationEditor extends Composite<NotificationEditor> {

    @Wire
    TextField subjectField

    @Wire
    ComboBox typeField

    @Wire
    OptionsGroup channelsField

    @Wire
    TextField plainTextBodyField

    @Wire(path = "okButton")
    Button okBtn

    void selectType(String type) {
        typeField.openOptionsPopup().select(withText(type))
    }

    static void selectRecipient(String recipient) {
        $j(TextField, byChain(byJTestId("recipientsField"), byClassName("v-textfield"))).setValue(recipient)
        SelenideElement element = $(byClassName("jmix-suggestionfield-item"))
        element.click()
    }

    void chooseChannelsField(String type) {
        channelsField.select(type)
    }

}

