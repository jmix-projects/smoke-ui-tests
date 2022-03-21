package io.jmix.tests.ui.test.utils.helpers

import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.WebDriverRunner
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.CheckBox
import io.jmix.masquerade.component.ComboBox
import io.jmix.masquerade.component.Notification
import io.jmix.masquerade.component.TextField
import io.jmix.tests.ui.screen.system.dialog.ConfirmationDialog
import org.openqa.selenium.Dimension

import static com.codeborne.selenide.Selenide.$
import static io.jmix.masquerade.Conditions.*
import static io.jmix.masquerade.Selectors.*

trait UiHelper {

    /**
     * Clicks defined button
     * @param button - defined button
     */
    static void clickButton(Button button) {
        button.shouldBe(VISIBLE)
                .shouldBe(ENABLED)
                .click()
    }

    /**
     * Checks Notification's appearing and caption with description
     * @param capt - expected caption
     * @param descr - expected description
     */
    static void checkNotification(String capt, String descr) {
        $j(Notification).shouldBe(VISIBLE)
                .shouldHave(caption(capt))
                .shouldHave(description(descr))
        $j(Notification).clickToClose()

    }

    /**
     * Checks Notification's appearing and caption
     * @param capt - expected caption
     */
    static void checkNotification(String capt) {
        $j(Notification).shouldBe(VISIBLE)
                .shouldHave(caption(capt))
        $j(Notification).clickToClose()
    }

    /**
     * Generates string from 4 random symbols
     * @return generated string
     */
    static String getGeneratedString() {
        int leftLimit = 97
        int rightLimit = 122
        int targetStringLength = 4
        Random random = new Random()

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
        return generatedString
    }

    /**
     * Selects param in defined Combobox by string value
     * @param comboBox
     * @param strValue
     */
    static void selectValueInComboBox(ComboBox comboBox, String strValue) {
        comboBox.setFilter(strValue)
                .getOptionsPopup()
                .select(strValue)
                .shouldHave(value(strValue))
    }

    /**
     * Fills defined field
     * @param field - defined field
     * @param value - defined value
     */
    static void fillTextField(TextField field, String value) {
        field.shouldBe(VISIBLE)
                .shouldBe(EDITABLE)
                .setValue(value)
    }

    static void checkSelenideElementByJtestId(String jTestId) {
        SelenideElement chart = $(byJTestId(jTestId))
        chart.shouldBe(VISIBLE)
    }

    static void checkSelenideElementByClass(String className) {
        SelenideElement chart = $(byClassName(className))
        chart.shouldBe(VISIBLE)
    }

    static void closeTab() {
        SelenideElement selenideElement = $(byClassName("v-tabsheet-caption-close"))
        selenideElement.click()
    }

    static String getString(ArrayList<String> strings) {
        StringBuilder builder = new StringBuilder(strings.get(0))
        for (int i = 1; i < strings.size(); i++) {
            builder.append(", ")
            builder.append(strings.get(i))
        }
        return builder.toString()
    }

    static String getUniqueName(String baseString) {
        return baseString + getGeneratedString()
    }
    static String getBaseName(String baseString) {
        return baseString
    }

    def clickYesInAConfirmationDialog() {
        $j(ConfirmationDialog).with {
            clickButton(yes)
        }
    }

    static void setCheckbox(CheckBox checkbox, boolean value) {
        checkbox
                .shouldBe(VISIBLE)
                .setChecked(value)
        value ? checkbox.shouldBe(CHECKED) : checkbox.shouldNotBe(CHECKED)
    }

}