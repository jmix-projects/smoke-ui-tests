package io.jmix.tests.ui.screen.addonscreen.search

import com.codeborne.selenide.SelenideElement
import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.TextField
import io.jmix.tests.ui.test.utils.traits.TableActionsTrait

import static com.codeborne.selenide.Selenide.$
import static io.jmix.masquerade.Conditions.ENABLED
import static io.jmix.masquerade.Conditions.VISIBLE
import static io.jmix.masquerade.Selectors.*

class SearchPersonBrowse extends Composite<SearchPersonBrowse> implements TableActionsTrait {

    @Wire
    Button createBtn

    static void fillSearchField(String value) {
        $j(TextField, byClassName("v-textfield"))
                .shouldBe(ENABLED)
                .shouldBe(VISIBLE)
                .setValue(value)
    }

    static void clickSearchButton() {
        $j(Button, byClassName("v-button-jmix-pickerfield-button")).click()
    }

    static void closeResultTab() {
        SelenideElement selenideElement = $(byChain(byJTestId("tab_search_SearchResults.screen"), byClassName("v-tabsheet-caption-close")))
        selenideElement.click()
    }
}
