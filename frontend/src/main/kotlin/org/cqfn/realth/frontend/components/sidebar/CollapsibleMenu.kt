package org.cqfn.realth.frontend.components.sidebar

import kotlinx.html.id
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import react.dom.div
import react.dom.h6
import react.dom.i
import react.dom.li
import react.dom.span

class CollapsibleMenuProps: RProps {
    lateinit var header: String
    lateinit var headerFaClass: String
    lateinit var header2: String
    lateinit var items: List<Pair<String, String>>
    lateinit var collapsibleDivId: String
    lateinit var ariaLabeledBy: String
}

class CollapsibleMenu: RComponent<CollapsibleMenuProps, RState>() {
    override fun RBuilder.render() {
        li("nav-item") {
            a(href = "#", classes = "nav-link collapsed") {
                attrs {
                    attributes["data-toggle"] = "collapse"
                    attributes["data-target"] = "#${props.collapsibleDivId}"
                    attributes["aria-expanded"] = "true"
                    attributes["aria-controls"] = props.collapsibleDivId
                }
                i("fas fa-fw ${props.headerFaClass}") {}
                span { +props.header }
            }
            div("collapse") {
                attrs {
                    id = props.collapsibleDivId
                    attributes["aria-labelledby"] = props.ariaLabeledBy
                    attributes["data-parent"] ="#accordionSidebar"
                }
                div("bg-white py-2 collapse-inner rounded") {
                    h6("collapse-header") {
                        +props.header2
                    }
                    props.items.forEach { (link, name) ->
                        a(href = link, classes = "collapse-item") {
                            +name
                        }
                    }
                }
            }
        }
    }
}