package io.jmix.tests.ui.test.smoke.crud.multitenancy

import io.jmix.tests.ui.screen.administration.tenants.TenantBrowse
import io.jmix.tests.ui.screen.administration.tenants.TenantEditor
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import io.jmix.tests.ui.test.utils.helpers.MultitenancyHelper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static io.jmix.masquerade.Selectors.$j

class MultitenancySmokeUITest extends BaseUiTest implements MultitenancyHelper {
    public static final String TENANTS_TABLE_J_TEST_ID = "tenantsTable"
    public static final String TENANT_ID_BASE_CODE = "tenant"
    public static final String TENANT_BASE_NAME = "Tenant "

    @BeforeEach
    void openTenantBrowse() {
        $j(MainScreen).openTenantBrowse()
    }

    @Test
    @DisplayName("Create tenant")
    void createTenant() {
        def tenantIdStr = getUniqueName(TENANT_ID_BASE_CODE)
        def tenantNameStr = getUniqueName(TENANT_BASE_NAME)

        createTenant(tenantIdStr, tenantNameStr)

        checkTheTenantIsDisplayed(tenantIdStr)
    }


    @Test
    @DisplayName("Edit tenant")
    void editTenant() {
        def tenantIdStr = getUniqueName(TENANT_ID_BASE_CODE)
        def tenantNameStr = getUniqueName(TENANT_BASE_NAME)
        def tenantEditedNameStr = getUniqueName(TENANT_BASE_NAME)

        createTenant(tenantIdStr, tenantNameStr)

        selectRowAndClickButton(tenantIdStr, $j(TenantBrowse).editBtn)

        $j(TenantEditor).with {
            fillTextField(nameField, tenantEditedNameStr)
            clickButton(ok)
        }

        checkTheTenantIsDisplayed(tenantIdStr)
    }

    @Test
    @DisplayName("Remove tenant")
    void removeTenant() {
        def tenantIdStr = getUniqueName(TENANT_ID_BASE_CODE)
        def tenantNameStr = getUniqueName(TENANT_BASE_NAME)

        createTenant(tenantIdStr, tenantNameStr)

        selectRowAndClickButton(tenantIdStr, $j(TenantBrowse).removeBtn)

        clickYesInAConfirmationDialog()

        $j(TenantBrowse).with {
            checkRecordIsNotDisplayed(tenantIdStr, TENANTS_TABLE_J_TEST_ID)
        }
    }

}
