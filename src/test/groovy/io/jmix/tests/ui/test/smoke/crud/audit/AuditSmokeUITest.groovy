package io.jmix.tests.ui.test.smoke.crud.audit

import io.jmix.tests.ui.screen.administration.audit.EntityLogBrowse
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.AuditHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class AuditSmokeUITest extends BaseUiTest implements AuditHelper {
    public static final String CUSTOMER_ENTITY_NAME_WITH_PREFIX = "sales_Customer"
    public static final String DYN_ATTR_ENTITY_NAME_WITH_PREFIX = "sales_DynamicAttrPerson"
    public static final String ENTITY_LOG_TABLE_J_TEST_ID = "loggedEntityTable"
    public static final String CUSTOMER_ENTITY_FULL_NAME = "Customer (sales_Customer)"
    public static final String DYN_ATTR_ENTITY_FULL_NAME = "Dynamic attr person (sales_DynamicAttrPerson)"

    @BeforeEach
    void openEntityLogBrowse() {
        $j(MainScreen).openEntityLogBrowse()
    }

    @Test
    @DisplayName("Creates and removes setup for simple Customer entity")
    void createAndRemoveSetupForEntity() {
        createAndSaveSetup(CUSTOMER_ENTITY_FULL_NAME)
        applySetupChanges(CUSTOMER_ENTITY_NAME_WITH_PREFIX)
        $j(EntityLogBrowse).with {
            selectSetupRecord(CUSTOMER_ENTITY_NAME_WITH_PREFIX)
            clickButton(remove)
            clickYesInAConfirmationDialog()
        }
        $j(EntityLogBrowse).with {
            checkRecordIsNotDisplayed(CUSTOMER_ENTITY_NAME_WITH_PREFIX, ENTITY_LOG_TABLE_J_TEST_ID)
            applyChanges()
            checkAppliedChangesNotification()
        }
    }

    @Test
    @DisplayName("Creates and removes setup for entity with dynamic attributes")
    void createAndRemoveSetupForEntityWithDynAttr() {
        createAndSaveSetup(DYN_ATTR_ENTITY_FULL_NAME)
        applySetupChanges(DYN_ATTR_ENTITY_NAME_WITH_PREFIX)
        $j(EntityLogBrowse).with {
            selectSetupRecord(DYN_ATTR_ENTITY_NAME_WITH_PREFIX)
            clickButton(remove)
        }
        clickYesInAConfirmationDialog()
        $j(EntityLogBrowse).with {
            checkRecordIsNotDisplayed(DYN_ATTR_ENTITY_NAME_WITH_PREFIX, ENTITY_LOG_TABLE_J_TEST_ID)
            applyChanges()
            checkAppliedChangesNotification()
        }
    }
}
