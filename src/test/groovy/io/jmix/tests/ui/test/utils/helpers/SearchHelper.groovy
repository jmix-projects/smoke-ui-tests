package io.jmix.tests.ui.test.utils.helpers

import io.jmix.tests.ui.screen.addonscreen.search.SearchPersonBrowse
import io.jmix.tests.ui.screen.addonscreen.search.SearchPersonEditor
import io.jmix.tests.ui.screen.administration.jmx.BeanInspectScreen
import io.jmix.tests.ui.screen.administration.jmx.JMXConsoleScreen
import io.jmix.tests.ui.screen.administration.jmx.OperationResultDialog
import io.jmix.tests.ui.test.utils.helpers.UiHelper

import static io.jmix.masquerade.Selectors.$j
import static io.jmix.masquerade.Selectors.$j
import static io.jmix.masquerade.Selectors.$j
import static io.jmix.masquerade.Selectors.$j
import static io.jmix.masquerade.Selectors.$j
import static io.jmix.masquerade.Selectors.$j

trait SearchHelper extends UiHelper {
    public static final String SEARCH_PERSON_TABLE_J_TEST_ID = "searchPersonsTable"
    public static final String M_BEANS_TABLE_J_TEST_ID = "mbeansTable"
    public static final String BEAN_NAME = "jmix.search:type=EntityIndexing"

    String[] getSearchNames(String s) {
        def arr = new String[4]
        for (int i=0; i< 4; i++){
            arr[i] = getUniqueName(s)
        }
        return arr
    }

    void createSearchPerson(String name, String description){
        $j(SearchPersonBrowse).with {
            clickButton(createBtn)
        }

        $j(SearchPersonEditor).with {
            fillTextField(nameField, name)
            fillTextField(descriptionField,description)
            clickButton(ok)
        }
    }

    void checkSearchPersonIsDisplayed(String name){
        $j(SearchPersonBrowse).with {
            checkRecordIsDisplayed(name, SEARCH_PERSON_TABLE_J_TEST_ID)
        }
    }

    def openSearchJMXBeanScreen() {
        $j(JMXConsoleScreen).with {
            fillTextField(objectNameField, "search")
            clickButton(refresh)
            selectRowInTableByText(BEAN_NAME, M_BEANS_TABLE_J_TEST_ID)
            clickButton(inspect)
        }
    }

    def invokeEnqueueIndexAllBeanMethod() {
        $j(BeanInspectScreen).with {
            invokeEnqueueIndexAll()
        }

        $j(OperationResultDialog).with {
            clickButton(closeBtn)
        }
    }
}