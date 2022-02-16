package io.jmix.tests.ui.test.utils.helpers

import io.jmix.tests.ui.screen.administration.audit.EntityLogBrowse

import static io.jmix.masquerade.Selectors.$j

trait AuditHelper extends UiHelper {

    static void createAndSaveSetup(String entityFullName){
        $j(EntityLogBrowse).with {
            openSetupTab()
            createSetup()
            selectNameFromDropdown(entityFullName)
            setCheckbox(autoCheckBox, true)
            setCheckbox(manualCheckBox, true)
            setCheckbox(selectAllCheckBox, true)
            saveSetup()
        }
    }

    static void applySetupChanges(String entityNameWithPrefix){
        $j(EntityLogBrowse).with {
            selectSetupRecord(entityNameWithPrefix)
            applyChanges()
            checkAppliedChangesNotification()
        }
    }
}