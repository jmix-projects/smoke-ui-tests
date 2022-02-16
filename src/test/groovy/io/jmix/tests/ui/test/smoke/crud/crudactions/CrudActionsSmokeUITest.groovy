package io.jmix.tests.ui.test.smoke.crud.crudactions

import io.jmix.tests.ui.screen.application.customer.CustomerBrowse
import io.jmix.tests.ui.screen.application.customer.CustomerEditor
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.UiHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class CrudActionsSmokeUITest extends BaseUiTest implements UiHelper {
    public static final String CUSTOMER_ENTITY_NAME = "Customer"
    public static final String CUSTOMERS_TABLE_J_TEST_ID = "customersTable"
    public static final String HILDRED_ELLIS_CUSTOMER_NAME = "Hildred Ellis"
    public static final String SIDNEY_CHANDLER_CUSTOMER_NAME = "Sidney Chandler"
    public static final String EMAIL_BASE_STRING = "email@com."
    public static final String SILVER_GRADE = "CustomerGrade.SILVER"

    @BeforeEach
    void openCustomerBrowse() {
        $j(MainScreen).openCustomerBrowse()
    }

    @Test
    @DisplayName("Create action test")
    void checkCreateAction() {

        $j(CustomerBrowse).with {
            clickButton(createBtn)
        }

        def customerName = getUniqueName(CUSTOMER_ENTITY_NAME)
        def customerEmail = getUniqueName(EMAIL_BASE_STRING)

        $j(CustomerEditor).with {
            fillTextField(nameField, customerName)
            fillTextField(emailField, customerEmail)
            selectCustomerGrade(SILVER_GRADE)
            clickButton(ok)
        }
        $j(CustomerBrowse).with {
            checkRecordIsDisplayed(customerName, CUSTOMERS_TABLE_J_TEST_ID)
        }
    }

    @Test
    @DisplayName("Edit action test")
    void checkEditAction() {
//        $j(MainScreen).openCustomerBrowse()

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
//        $j(MainScreen).openCustomerBrowse()

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
