package io.jmix.tests.ui.test.utils.helpers

import com.codeborne.selenide.Selenide
import io.jmix.tests.ui.screen.administration.webdav.WebDAVDocumentBrowse

import static io.jmix.masquerade.Selectors.$j

trait WebDAVHelper extends UiHelper {

    public static final String RESOURCES_PATH = "src/main/resources/"
    public static final String FILE_PATH = "src/main/resources/helloworld.txt"

    static void uploadFileToDocumentBrowser(String fileName) {
        def fileNamePath = RESOURCES_PATH + fileName
        $j(WebDAVDocumentBrowse).with {
            Selenide.sleep(1000)
            uploadNewDocument(FILE_PATH, fileNamePath)
        }
    }

}