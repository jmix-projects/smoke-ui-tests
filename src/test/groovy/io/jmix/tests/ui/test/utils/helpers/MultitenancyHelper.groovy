package io.jmix.tests.ui.test.utils.helpers

import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.screen.administration.tenants.TenantBrowse
import io.jmix.tests.ui.screen.administration.tenants.TenantEditor

import static io.jmix.masquerade.Selectors.$j

trait MultitenancyHelper extends UiHelper {
    public static final String TENANTS_TABLE_J_TEST_ID = "tenantsTable"

    def createTenant(tenantIdStr, tenantNameStr) {
        $j(TenantBrowse).with {
            clickButton(createBtn)
        }

        $j(TenantEditor).with {
            fillTextField(tenantIdField, tenantIdStr)
            fillTextField(nameField, tenantNameStr)
            clickButton(ok)
        }
    }

    def checkTheTenantIsDisplayed(tenantIdStr) {
        $j(TenantBrowse).with {
            checkRecordIsDisplayed(tenantIdStr, TENANTS_TABLE_J_TEST_ID)
        }
    }

    def selectRowAndClickButton(String tenantIdStr, Button button) {
        $j(TenantBrowse).with {
            checkRecordIsDisplayed(tenantIdStr, TENANTS_TABLE_J_TEST_ID)
            selectRowInTableByText(tenantIdStr, TENANTS_TABLE_J_TEST_ID)
            clickButton(button)
        }
    }
}