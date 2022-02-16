package io.jmix.tests.ui.test.smoke.withoutdDBactions.datatools

import io.jmix.tests.ui.screen.administration.datatools.EntityInspectorBrowse
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class DatatoolsSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String SALES_USER_TABLE_J_TEST_ID = "sales_UserTable"
    public static final String SHOW_ALL_RECORDS_MODE = "Show all records"
    public static final String USER_ENTITY_NAME = "User"
    public static final String USER_ENTITY_FULL_STRING = "User (sales_User)"
    public static final String ADMIN = "admin"

    @Test
    @DisplayName("Checks displaying Datatools screen")
    void checkDatatoolsTest() {
        $j(MainScreen).openEntityInspectorBrowse()

        $j(EntityInspectorBrowse).with {
            findEntityByFilter(USER_ENTITY_NAME, USER_ENTITY_FULL_STRING)
            selectShowMode(SHOW_ALL_RECORDS_MODE)
            checkRecordIsDisplayed(ADMIN, SALES_USER_TABLE_J_TEST_ID)
        }
    }

}
