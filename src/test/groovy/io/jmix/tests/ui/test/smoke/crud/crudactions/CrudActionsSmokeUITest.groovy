package io.jmix.tests.ui.test.smoke.crud.crudactions

import io.jmix.tests.ui.screen.application.customer.CustomerBrowse
import io.jmix.tests.ui.screen.application.customer.CustomerEditor
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.CrudActionsHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class CrudActionsSmokeUITest extends BaseUiTest implements CrudActionsHelper {
    public static final String CUSTOMER_ENTITY_NAME = "Customer"
    public static final String CUSTOMERS_TABLE_J_TEST_ID = "customersTable"
    public static final String HILDRED_ELLIS_CUSTOMER_NAME = "Hildred Ellis"
    public static final String SIDNEY_CHANDLER_CUSTOMER_NAME = "Sidney Chandler"
    public static final String EMAIL_BASE_STRING = "email@com."

    @BeforeEach
    void openCustomerBrowse() {
        $j(MainScreen).openCustomerBrowse()
    }

    @Test
    @DisplayName("Create action test")
    void checkCreateAction() {
        def customerName = getUniqueName(CUSTOMER_ENTITY_NAME)
        def customerEmail = getUniqueName(EMAIL_BASE_STRING)

        createCustomer(customerName, customerEmail)
        $j(CustomerBrowse).with {
            checkRecordIsDisplayed(customerName, CUSTOMERS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Edit action test")
    void checkEditAction() {
        def customerEditedName = getUniqueName(CUSTOMER_ENTITY_NAME)

        $j(CustomerBrowse).with {
            selectRowInTableByText(HILDRED_ELLIS_CUSTOMER_NAME, CUSTOMERS_TABLE_J_TEST_ID)
            clickButton(editBtn)
        }
        $j(CustomerEditor).with {
            fillTextField(nameField, customerEditedName)
            clickButton(ok)
        }
        $j(CustomerBrowse).with {
            checkRecordIsDisplayed(customerEditedName, CUSTOMERS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Remove action test")
    void checkRemoveAction() {
        $j(CustomerBrowse).with {
            selectRowInTableByText(SIDNEY_CHANDLER_CUSTOMER_NAME, CUSTOMERS_TABLE_J_TEST_ID)
            clickButton(removeBtn)
        }

        clickYesInAConfirmationDialog()
        $j(CustomerBrowse).with {
            checkRecordIsNotDisplayed(SIDNEY_CHANDLER_CUSTOMER_NAME, CUSTOMERS_TABLE_J_TEST_ID)
        }
    }
}
