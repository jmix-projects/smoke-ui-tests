package io.jmix.tests.ui.screen.bpm

import com.codeborne.selenide.Condition
import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.DataGrid

import static io.jmix.masquerade.Conditions.VISIBLE
import static io.jmix.masquerade.Selectors.byText

class ProcessDefinitionBrowse extends Composite<ProcessDefinitionBrowse> {

    @Wire
    Button openInModelerBtn

    @Wire
    Button startProcessBtn

    @Wire
    Button viewDetailsBtn

    @Wire
    DataGrid processDefinitionsTable

    void selectRowInTableByText(String s) {
        processDefinitionsTable.shouldBe(VISIBLE)
                .selectRow(byText(s))
                .contextClick()
    }

    void checkRecordIsDisplayed(String s) {
        processDefinitionsTable.shouldBe(VISIBLE)
                .getRow(byText(s))
                .shouldBe(VISIBLE)
    }

    void checkButtons(Condition condition) {
        openInModelerBtn.shouldBe(condition)
        startProcessBtn.shouldBe(condition)
        viewDetailsBtn.shouldBe(condition)
    }
}
