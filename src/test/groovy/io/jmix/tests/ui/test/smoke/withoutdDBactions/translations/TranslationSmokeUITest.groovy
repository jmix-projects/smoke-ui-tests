package io.jmix.tests.ui.test.smoke.withoutdDBactions.translations

import com.codeborne.selenide.Selenide
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

import static io.jmix.masquerade.Conditions.VISIBLE
import static io.jmix.masquerade.Conditions.caption
import static io.jmix.masquerade.Selectors.$j

class TranslationSmokeUITest extends BaseUiTest implements UiHelper {

    @ParameterizedTest(name = "{index} Translations smoke test - {0}")
    @ValueSource(strings = ["Russian", "Dutch", "French", "Greek", "German", "Romanian", "Chinese (China)", "Central Kurdish", "English"])
    void checkTranslations(String language) {

        Selenide.open('/')

        loginAsAdminWithLanguage(language)
        $j(MainScreen).getSideMenu().openItem('bpm', 'bpm_StartProcessScreen')
        $j(Button.class, 'refreshBtn').shouldBe(VISIBLE)
                .shouldHave(caption(getTranslateAttributeValue(language, 'refreshBtn')))
        Selenide.sleep(2000)
        $j(MainScreen).logout()
    }

    @Override
    static void beforeAll() {
        maximizeWindowSize()
    }

    @Override
    static void logoutAfterAll() {
    }

    static String getTranslateAttributeValue(language, attribute) {

        def nums = []
        if (language == 'Russian') {
            nums = ['refreshBtn': 'Обновить']
        } else if (language == 'Dutch') {
            nums = ['refreshBtn': 'Verversen']
        } else if (language == 'French') {
            nums = ['refreshBtn': 'Rafraîchir']
        } else if (language == 'Greek') {
            nums = ['refreshBtn': 'Ανανέωση']
        } else if (language == 'German') {
            nums = ['refreshBtn': 'Aktualisieren']
        } else if (language == 'Romanian') {
            nums = ['refreshBtn': 'Actualizare']
        } else if (language == 'Chinese (China)') {
            nums = ['refreshBtn': '刷新']
        } else if (language == 'English') {
            nums = ['refreshBtn': 'Refresh']
        } else if (language == 'Central Kurdish') {
            nums = ['refreshBtn': 'بوژاندنەوە']
        }
        return nums[attribute]
    }
}