package io.jmix.tests.ui.screen.bpm

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.test.utils.traits.TableActionsTrait

class BPMNModelDraftsBrowser extends Composite<BPMNModelDraftsBrowser> implements TableActionsTrait {

    @Wire
    Button lookupSelectAction

}