package io.jmix.tests.ui.screen.administration.jmx

import com.codeborne.selenide.SelenideElement
import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button

import static com.codeborne.selenide.Selenide.$$
import static io.jmix.masquerade.Selectors.byJTestId

class BeanInspectScreen extends Composite<BeanInspectScreen> {

    @Wire
    Button closeBtn

    static void invokeEnqueueIndexAll() {
        SelenideElement invokeBtn = $$(byJTestId("invokeBtn")).get(2)
        invokeBtn.click()
    }

}
