package org.cqfn.realth.frontend.components.cards

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.i

class CardProps : RProps {
    lateinit var header: String
    lateinit var value: String
    lateinit var faIcon: String
    var leftBorderColor: String = "primary"
}

class Card : RComponent<CardProps, RState>() {
    override fun RBuilder.render() {
        div("col-xl-3 col-md-6 mb-4") {
            div("card border-left-${props.leftBorderColor} shadow h-100 py-2") {
                div("card-body") {
                    div("row no-gutters align-items-center") {
                        div("col mr-2") {
                            div("text-xs font-weight-bold text-primary text-uppercase mb-1") {
                                +props.header
                            }
                            div("h5 mb-0 font-weight-bold text-gray-800") {
                                +props.value
                            }
                        }
                        div("col-auto") {
                            i("fas ${props.faIcon} fa-2x text-gray-300") {}
                        }
                    }
                }
            }
        }
    }
}
