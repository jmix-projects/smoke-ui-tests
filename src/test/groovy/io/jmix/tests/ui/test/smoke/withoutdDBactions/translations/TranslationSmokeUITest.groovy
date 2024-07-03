package io.jmix.tests.ui.test.smoke.withoutdDBactions.translations

import com.codeborne.selenide.Selenide
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

import static io.jmix.masquerade.Conditions.VISIBLE
import static io.jmix.masquerade.Conditions.caption
import static io.jmix.masquerade.Selectors.$j

class TranslationSmokeUITest extends BaseUiTest implements UiHelper {

    @ParameterizedTest(name = "{index} Translations smoke test - {0}")
    @ValueSource(strings = ["English", "Русский", "Français", "Nederlands", "итальянский", "Deutsch", "румынский", "Ελληνικά", "中文简体", "کوردی"])
    void checkTranslations(String language) {
        loginAsAdminWithLanguage(language)
        $j(MainScreen).getSideMenu().openItem('bpm', 'bpm_StartProcessScreen')
        $j(Button.class, 'refreshBtn').shouldBe(VISIBLE)
                .shouldHave(caption(getTranslatedAttributeValue(language, 'refreshBtn')))
    }

    static String getTranslatedAttributeValue(language, attribute) {

        def translated = []

        switch (language) {
            case 'English': translated = ['refreshBtn': 'Refresh']; break
            case 'Русский': translated = ['refreshBtn': 'Обновить']; break
            case 'Français': translated = ['refreshBtn': 'Rafraîchir']; break
            case 'Deutsch': translated = ['refreshBtn': 'Aktualisieren']; break
            case 'итальянский': translated = ['refreshBtn': 'Aggiorna']; break
            case 'Nederlands': translated = ['refreshBtn': 'Verversen']; break
            case 'румынский': translated = ['refreshBtn': 'Actualizare']; break
            case 'Ελληνικά': translated = ['refreshBtn': 'Ανανέωση']; break
            case '中文简体': translated = ['refreshBtn': '刷新']; break
            case 'کوردی': translated = ['refreshBtn': 'بوژاندنەوە']
        }
        return translated[attribute]
    }

    @Override
    static void beforeAll() {
        maximizeWindowSize()
        Selenide.open('/')
    }

    @AfterEach
    void logoutAfterEach() {
        $j(MainScreen).logout()
    }

    @Override
    static void logoutAfterAll() {
    }
}