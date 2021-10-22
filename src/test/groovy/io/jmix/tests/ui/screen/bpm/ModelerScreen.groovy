package io.jmix.tests.ui.screen.bpm

import com.codeborne.selenide.SelenideElement
import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField

import static com.codeborne.selenide.Selenide.$
import static io.jmix.masquerade.Conditions.VISIBLE
import static io.jmix.masquerade.Selectors.*

class ModelerScreen extends Composite<ModelerScreen> {

    @Wire
    TextField businessId

    @Wire
    TextField name

    @Wire
    Button deployBtn

    static void checkModelerIsDisplayed() {
        SelenideElement modeler = $(byJTestId("modeler"))
        modeler.shouldBe(VISIBLE)
    }

    static void closeModelerTab() {
        SelenideElement selenideElement = $(byChain(byJTestId("tab_bpm_BpmnModelerScreen"), byClassName("v-tabsheet-caption-close")))
        selenideElement.click()
    }

}
