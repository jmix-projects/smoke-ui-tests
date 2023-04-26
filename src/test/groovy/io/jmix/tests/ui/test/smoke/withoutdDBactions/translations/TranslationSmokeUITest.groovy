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
    @ValueSource(strings = ["English", "Russian", "French", "German", "Italian", "Dutch", "Romanian", "Greek", "Chinese (China)", "Central Kurdish"])
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
            case 'Russian': translated = ['refreshBtn': 'Обновить']; break
            case 'French': translated = ['refreshBtn': 'Rafraîchir']; break
            case 'German': translated = ['refreshBtn': 'Aktualisieren']; break
            case 'Italian': translated = ['refreshBtn': 'Aggiorna']; break
            case 'Dutch': translated = ['refreshBtn': 'Verversen']; break
            case 'Romanian': translated = ['refreshBtn': 'Actualizare']; break
            case 'Greek': translated = ['refreshBtn': 'Ανανέωση']; break
            case 'Chinese (China)': translated = ['refreshBtn': '刷新']; break
            case 'Central Kurdish': translated = ['refreshBtn': 'بوژاندنەوە']
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