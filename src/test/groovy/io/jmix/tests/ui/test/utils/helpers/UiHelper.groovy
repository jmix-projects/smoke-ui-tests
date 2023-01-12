package io.jmix.tests.ui.test.utils.helpers

import com.codeborne.selenide.Condition
import com.codeborne.selenide.SelenideElement
import io.jmix.masquerade.component.*
import io.jmix.tests.ui.screen.system.dialog.ConfirmationDialog

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
    static void checkNotificationCaptionAndDescription(String capt, String descr) {
        $j(Notification).shouldBe(VISIBLE)
                .shouldHave(caption(capt))
                .shouldHave(description(descr))
        $j(Notification).clickToClose()

    }

    /**
     * Checks Notification's appearing and caption
     * @param capt - expected caption
     */
    static void checkNotificationCaption(String capt) {
        $j(Notification).shouldBe(VISIBLE)
                .shouldHave(captionContains(capt))
        $j(Notification).clickToClose()
    }

    /**
     * Checks Notification's appearing and description
     * @param descr - expected description
     */
    static void checkNotificationDescription(String descr) {
        $j(Notification).shouldBe(VISIBLE)
                .shouldHave(description(descr))
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
     * Fills defined field
     * @param field - defined field
     * @param value - defined value
     */
    static void fillTextField(TextField field, String value) {
        field.shouldBe(VISIBLE)
                .shouldBe(EDITABLE)
                .setValue(value)
    }

    /**
     * Check value string in defined TextField
     * @param textField - defined field
     * @param str - expected value stirng
     */
    static void checkFilledTextField(TextField textField, String str) {
        textField.shouldHave(Condition.value(str))
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