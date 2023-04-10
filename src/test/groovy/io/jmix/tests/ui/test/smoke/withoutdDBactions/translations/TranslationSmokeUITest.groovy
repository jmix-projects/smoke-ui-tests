package io.jmix.tests.ui.test.smoke.withoutdDBactions.translations

import com.codeborne.selenide.Selenide
import io.jmix.tests.ui.screen.system.login.LoginScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class TranslationSmokeUITest extends BaseUiTest implements UiHelper {

    def DEFAULT_LOCAL = "English"
    def LANGUAGES = ["Russian", "Dutch", "French", "Greek", "German", "Romanian", "Chinese (China)", "کوردی"] as ArrayList

    @Test
    @DisplayName("Translations smoke test")
    void checkTranslations() {
        $j(MainScreen).logout()
        LANGUAGES.each { String language ->

            if (language == "کوردی") println('======= ' + 'Kurdish ckb ' + language + ' =======')
            //  else if (language == "中文简体") println('======= ' + 'Chinese zh_CN ' + language + ' =======')
            else println('======= ' + language + ' =======')

            loginAsAdminLanguage(language)
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
        $j(LoginScreen).loginWithLocale(DEFAULT_LOCAL)
    }
}
