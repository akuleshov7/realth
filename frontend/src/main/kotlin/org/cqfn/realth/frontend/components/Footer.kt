package org.cqfn.realth.frontend.components

import generated.REALTH_VERSION
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.span
import react.dom.br

/**
 * todo: style is not attached for some reason
 */
class Footer : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
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
