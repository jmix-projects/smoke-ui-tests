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
                .shouldHave(caption(getTranslatedAttributeValue(language, 'refreshBtn')))
        $j(MainScreen).logout()
    }

    static String getTranslatedAttributeValue(language, attribute) {

        def nums = []

        switch (language) {
            case 'Russian': nums = ['refreshBtn': 'Обновить']; break
            case 'Dutch': nums = ['refreshBtn': 'Verversen']; break
            case 'French': nums = ['refreshBtn': 'Rafraîchir']; break
            case 'Greek': nums = ['refreshBtn': 'Ανανέωση']; break
            case 'German': nums = ['refreshBtn': 'Aktualisieren']; break
            case 'Romanian': nums = ['refreshBtn': 'Actualizare']; break
            case 'Chinese (China)': nums = ['refreshBtn': '刷新']; break
            case 'English': nums = ['refreshBtn': 'Refresh']; break
            case 'Central Kurdish': nums = ['refreshBtn': 'بوژاندنەوە']
        }
        return nums[attribute]
    }

    @Override
    static void beforeAll() {
        maximizeWindowSize()
    }

    @Override
    static void logoutAfterAll() {
    }
}