package io.jmix.tests.ui.screen.administration.webdav

import com.codeborne.selenide.SelenideElement
import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.test.utils.TableActionsTrait
import org.testcontainers.shaded.org.apache.commons.io.FileUtils

import static com.codeborne.selenide.Selectors.byName
import static com.codeborne.selenide.Selenide.$

class WebDAVDocumentBrowse extends Composite<WebDAVDocumentBrowse> implements TableActionsTrait {

    @Wire
    Button manageVersionsBtn

    @Wire
    Button removeBtn

    @Wire
    Button lockBtn

    static void clickUploadButton(String filePath, String newName) {
        SelenideElement button = $(byName("files[]"))
        File file = new File(filePath)
        File newFile = new File(newName)
        FileUtils.copyFile(file, newFile)

        button.uploadFile(newFile)
    }

}
