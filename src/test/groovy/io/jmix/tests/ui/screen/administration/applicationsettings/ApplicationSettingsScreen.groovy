package io.jmix.tests.ui.screen.administration.applicationsettings

import com.codeborne.selenide.Condition
import io.jmix.masquerade.Conditions
import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.ComboBox
import io.jmix.masquerade.component.TextField

import static io.jmix.masquerade.Conditions.value
import static io.jmix.masquerade.Selectors.$j
import static com.codeborne.selenide.Selectors.byCssSelector
import static io.jmix.masquerade.Selectors.byJTestId
import static io.jmix.masquerade.Selectors.withText

class ApplicationSettingsScreen extends Composite<ApplicationSettingsScreen> {

    @Wire
    ComboBox entitiesLookup

    @Wire
    Button saveButtonId

    @Wire
    Button closeButtonId

    void selectApplicationSettingsEntity(String type) {
        entitiesLookup.openOptionsPopup().select(withText(type))
    }

    static void checkDefaultNotifText() {
        TextField disabledFieldNotifText = $j(TextField, byCssSelector("input.v-textfield-readonly[j-test-id='notificationText']"))
        disabledFieldNotifText.shouldBe(Conditions.VISIBLE, Conditions.READONLY)
    }

    static void checkDefaultDefGrade() {
        TextField disableFieldDefGrade = $j(TextField, byCssSelector("input.v-textfield-readonly[j-test-id='defaultGrade']"))
        disableFieldDefGrade.shouldBe(Conditions.VISIBLE, Conditions.READONLY)
                            .shouldHave(value("B"))
    }

}
