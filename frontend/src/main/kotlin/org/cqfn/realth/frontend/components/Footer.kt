package org.cqfn.realth.frontend.components

import generated.REALTH_VERSION
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.br
import react.dom.div
import react.dom.footer
import react.dom.span

/**
 * todo: style is not attached for some reason
 */
@Suppress("ForbiddenComment")
class Footer : RComponent<RProps, RState>() {
    @Suppress("EMPTY_BLOCK_STRUCTURE_ERROR")
    override fun RBuilder.render() {
        footer("sticky-footer bg-white") {
            div("container my-auto") {
                div("copyright text-center my-auto") {
                    span {
                        +"Copyright &copy REALTH 2020"
                        br {}
                        +"Version $REALTH_VERSION"
                    }
                }
            }
        }
    }
}
