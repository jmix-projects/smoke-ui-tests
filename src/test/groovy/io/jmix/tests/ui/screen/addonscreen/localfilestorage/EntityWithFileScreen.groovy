package io.jmix.tests.ui.screen.addonscreen.localfilestorage

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.masquerade.component.FileUploadField
import io.jmix.tests.ui.test.utils.traits.TableActionsTrait

class EntityWithFileScreen extends Composite<EntityWithFileScreen> implements TableActionsTrait {

    @Wire
    FileUploadField fileMultiUpload

    @Wire
    Button createBtn

    @Wire
    Button editBtn

    @Wire
    Button removeBtn

    }

