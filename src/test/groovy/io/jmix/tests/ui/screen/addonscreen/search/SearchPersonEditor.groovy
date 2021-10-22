package io.jmix.tests.ui.screen.addonscreen.search

import com.codeborne.selenide.SelenideElement
import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField

import static com.codeborne.selenide.Selenide.$
import static io.jmix.masquerade.Selectors.*

class SearchPersonEditor extends Composite<SearchPersonEditor> {
    @Wire
    TextField nameField

    @Wire
    TextField descriptionField

    @Wire(path = "commitAndCloseBtn")
    Button ok

}
