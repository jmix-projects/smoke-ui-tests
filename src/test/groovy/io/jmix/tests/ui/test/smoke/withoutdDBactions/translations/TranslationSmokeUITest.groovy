package io.jmix.tests.ui.test.smoke.withoutdDBactions.translations

import com.codeborne.selenide.Selenide
import io.jmix.tests.ui.screen.system.login.LoginScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

import static io.jmix.masquerade.Selectors.$j

class TranslationSmokeUITest extends BaseUiTest implements UiHelper {

    def DEFAULT_LOCALE = "English"

    @ParameterizedTest(name = "{index} Translations smoke test - {0}")
    @ValueSource(strings = ["Russian", "Dutch", "French", "Greek", "German", "Romanian", "Chinese (China)", "Central Kurdish", "English"])
    void checkTranslations(String language) {
        $j(MainScreen).logout()
        if (language != DEFAULT_LOCALE) {

            loginAsAdminWithLanguage(language)
            maximizeWindowSize()

            $j(MainScreen).openReportsBrowse()
            Selenide.sleep(2000)
            $j(MainScreen).openResourceRoleBrowse()
            Selenide.sleep(2000)
            $j(MainScreen).openModelerScreen()
            Selenide.sleep(2000)
            $j(MainScreen).openImapConfigurationBrowse()
            $j(MainScreen).logout()
        }
        $j(LoginScreen).loginWithLocale(DEFAULT_LOCALE)
    }
}
