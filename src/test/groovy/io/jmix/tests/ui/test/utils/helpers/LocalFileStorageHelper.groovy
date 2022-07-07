package io.jmix.tests.ui.test.utils.helpers

import io.jmix.tests.ui.screen.addonscreen.localfilestorage.EntityWithFileEditor

import static io.jmix.masquerade.Selectors.$j

trait LocalFileStorageHelper extends UiHelper {

    public static final String RESOURCES_PATH = "src/main/resources/"
    public static final String FILE_PATH = "src/main/resources/helloworld.txt"

    static void uploadFileToDocumentBrowser(String fileName) {
        def fileNamePath = RESOURCES_PATH + fileName
        $j(EntityWithFileEditor).with {
            uploadNewDocument(FILE_PATH, fileNamePath)
        }
    }

}