package io.jmix.tests.ui.test.user


import io.jmix.tests.ui.menu.Menus
import io.jmix.tests.ui.screen.application.user.UserBrowse
import io.jmix.tests.ui.screen.application.user.UserEditor
import io.jmix.tests.ui.screen.system.main.MainScreen
import io.jmix.tests.ui.test.BaseUiTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import static com.codeborne.selenide.Condition.visible
import static io.jmix.masquerade.Selectors.$j
import static io.jmix.masquerade.Selectors.withText

class UserUiTest extends BaseUiTest {

    @Test
    @DisplayName("Creates new user")
    void createNewUser() {
        loginAsAdmin()

        $j(MainScreen).with {
            sideMenu.openItem(Menus.USER_BROWSE)
                    .createUser()
        }

        $j(UserEditor).with {
            usernameField.setValue('newUsername')
            passwordField.setValue('qO4Hn6o')
            confirmPasswordField.setValue('qO4Hn6o')
            commitAndCloseBtn.click()
        }

        $j(UserBrowse).usersTable
                .getCell(withText('newUsername'))
                .shouldBe(visible)
    }
}
