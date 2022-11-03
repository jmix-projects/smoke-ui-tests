package io.jmix.tests.ui.test.utils.helpers

import io.jmix.tests.ui.screen.application.customer.CustomerBrowse
import io.jmix.tests.ui.screen.application.customer.CustomerEditor

import static io.jmix.masquerade.Selectors.$j

trait CrudActionsHelper extends UiHelper {
    public static final String SILVER_GRADE = "CustomerGrade.SILVER"

    static void createCustomer(String customerName, String customerEmail) {
        $j(CustomerBrowse).with {
            clickButton(createBtn)
        }

        $j(CustomerEditor).with {
            fillTextField(nameField, customerName)
            fillTextField(emailField, customerEmail)
            selectCustomerGrade(SILVER_GRADE)
            clickButton(ok)
        }
    }
}