package io.jmix.tests.ui.screen.addonscreen.localfilestorage

import com.codeborne.selenide.Selenide
import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.FileUploadField
import io.jmix.tests.ui.test.utils.traits.TableActionsTrait
import org.testcontainers.shaded.org.apache.commons.io.FileUtils

import static com.codeborne.selenide.Selectors.byXpath
import static io.jmix.masquerade.Selectors.$j

class EntityWithFileEditor extends Composite<EntityWithFileEditor> implements TableActionsTrait {

    @Wire
    FileUploadField fileField

    @Wire(path = "commitAndCloseBtn")
    Button okBtn

    @Wire
    Button closeBtn

    static void generateAndUploadNewFile(String filePath, String newFilePath) {
        File file = new File(filePath)
        File newFile = new File(newFilePath)
        FileUtils.copyFile(file, newFile)

        $j(FileUploadField, byXpath(".//div[contains(@class, 'jmix-fileupload v-widget')]"))
                .upload(newFile)
        Selenide.sleep(400)
    }
}
