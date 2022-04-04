package io.jmix.tests.ui.screen.administration.quartzjobs.browse

import io.jmix.masquerade.Wire
import io.jmix.masquerade.base.Composite
import io.jmix.masquerade.component.Button
import io.jmix.tests.ui.test.utils.traits.TableActionsTrait

class QuartzJobsBrowse extends Composite<QuartzJobsBrowse> implements TableActionsTrait {

    @Wire
    Button createBtn

    @Wire
    Button executeNowBtn
}
