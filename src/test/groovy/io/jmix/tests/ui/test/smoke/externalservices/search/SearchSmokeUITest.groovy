package io.jmix.tests.ui.test.smoke.externalservices.search

import com.codeborne.selenide.Selenide
import io.jmix.tests.ui.screen.addonscreen.search.SearchPersonBrowse
import io.jmix.tests.ui.screen.administration.jmx.BeanInspectScreen
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.SearchHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class SearchSmokeUITest extends BaseUiTest implements SearchHelper {
    public static final String SEARCH_PERSON = "searchperson "

    @Test
    @DisplayName("Search smoke test")
    void checkSearch() {
        $j(MainScreen).openSearchPersonBrowse()
        def uniqueSearchNames = getSearchNames(SEARCH_PERSON)

        createSearchPerson(uniqueSearchNames[0], uniqueSearchNames[1])
        checkSearchPersonIsDisplayed(uniqueSearchNames[0])

        createSearchPerson(uniqueSearchNames[2], uniqueSearchNames[3])
        checkSearchPersonIsDisplayed(uniqueSearchNames[2])

        $j(MainScreen).openJMXConsoleScreen()

        openSearchJMXBeanScreen()

        invokeEnqueueIndexAllBeanMethod()

        $j(BeanInspectScreen).with {
            clickButton(closeBtn)
        }

        $j(MainScreen).openSearchPersonBrowse()
        $j(SearchPersonBrowse).with {
            fillSearchField(SEARCH_PERSON)
            clickSearchButton()
            Selenide.sleep(2000)
        }
    }

}
