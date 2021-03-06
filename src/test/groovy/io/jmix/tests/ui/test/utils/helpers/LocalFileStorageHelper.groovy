package io.jmix.tests.ui.test.utils.helpers

import io.jmix.tests.ui.screen.addonscreen.localfilestorage.EntityWithFileEditor

import static io.jmix.masquerade.Selectors.$j

trait LocalFileStorageHelper extends UiHelper {

    public static final String RESOURCES_PATH = "src/main/resources/"

        static void generateAndUploadFileToEditor(String sourceFileName, String fileName)
            {$j(EntityWithFileEditor).with {
                generateAndUploadNewFile(RESOURCES_PATH + sourceFileName, RESOURCES_PATH + fileName)
            }
        }
    }