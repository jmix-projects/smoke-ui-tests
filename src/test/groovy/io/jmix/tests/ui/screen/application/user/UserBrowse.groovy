package io.jmix.tests.ui.screen.application.user

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.PopupButton
import io.jmix.masquerade.component.Table
import io.jmix.tests.ui.screen.system.dialog.ConfirmationDialog
import io.jmix.tests.ui.test.utils.TableActionsTrait

import static io.jmix.masquerade.Conditions.VISIBLE
import static io.jmix.masquerade.Selectors.*

class UserBrowse extends Composite<UserBrowse> implements TableActionsTrait {

    @Wire
    Button createBtn
    @Wire
    Button editBtn
    @Wire
    Button removeBtn
    @Wire
    Button refreshBtn
    @Wire
    Table usersTable
    @Wire
    Button showRoleAssignmentsBtn
    @Wire
    PopupButton additionalActionsBtn

    @Wire
    Button lookupSelectAction

    UserEditor createUser() {
        createBtn.click()
        new UserEditor()
    }

    UserEditor editUser(String username) {
        usersTable.getCell(withText(username))
                .click()
        editBtn.click()
        new UserEditor()
    }

    void removeUser(String username) {
        usersTable.getCell(withText(username))
                .click()

        removeBtn.click()

        $j(ConfirmationDialog)
                .yes
                .click()
    }

    void checkRecordIsDisplayed(String username) {
        usersTable.shouldBe(VISIBLE)
                .getRow(byText(username))
                .shouldBe(VISIBLE)
    }

    void checkRecordIsNotDisplayed(String username) {
        usersTable.shouldBe(VISIBLE)
                .getRow(byText(username))
                .shouldNotBe(VISIBLE)
    }

    void select() {
        lookupSelectAction
                .shouldBe(VISIBLE)
                .click()
    }
}
