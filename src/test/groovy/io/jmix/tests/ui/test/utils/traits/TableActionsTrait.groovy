package io.jmix.tests.ui.test.utils.traits

import io.jmix.masquerade.component.Table

import static io.jmix.masquerade.Conditions.VISIBLE
import static io.jmix.masquerade.Selectors.*

trait TableActionsTrait {

    static void selectRowInTableByText(String s, String path) {
        $j(Table, path).shouldBe(VISIBLE)
                .selectRow(byText(s))
                .click()
    }

    static void checkRecordIsDisplayed(String s, String path) {
        $j(Table, path).shouldBe(VISIBLE)
                .getRow(byText(s))
                .shouldBe(VISIBLE)
    }

    static void checkByCellsRecordIsDisplayed(String cell1, String cell2, String path) {
        $j(Table, path).shouldBe(VISIBLE)
                .getRow(byCells(cell1, cell2))
                .shouldBe(VISIBLE)
    }

    static void checkRecordCount(String cell1, String cell2, String path, int number) {
        int rowCount = $j(Table, path).shouldBe(VISIBLE)
                .getRows(byCells(cell1, cell2))
                .size()
        assert rowCount == number
    }

    static void checkRecordIsNotDisplayed(String s, String path) {
        $j(Table, path).shouldBe(VISIBLE)
                .getRow(byText(s))
                .shouldNotBe(VISIBLE)
    }
}