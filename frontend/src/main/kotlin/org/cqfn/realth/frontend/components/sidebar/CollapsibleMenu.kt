/**
 * Components for collapsible menus in a sidebar
 */

package org.cqfn.realth.frontend.components.sidebar

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

import kotlinx.html.id

/**
 * [RProps] for a collapsible menu
 */
class CollapsibleMenuProps : RProps {
    /**
     * Header of a collapsible menu
     */
    lateinit var header: String

    /**
     * An icon from font-awesome to be displayed near the header
     */
    lateinit var headerFaClass: String

    /**
     * Header inside collapsed part
     */
    lateinit var header2: String

    /**
     * Items inside collapsed part
     */
    @Suppress("TYPE_ALIAS")
    lateinit var items: List<Pair<String, String>>

    /**
     * ID to connect collapsible link and content. TODO may be not needed? Generate IDs inside logic
     */
    lateinit var collapsibleDivId: String

    /**
     * Something for bootstrap. TODO same as for [collapsibleDivId]
     */
    lateinit var ariaLabeledBy: String
}

/**
 * [RComponent] for a collapsible menu
 */
@Suppress("EMPTY_BLOCK_STRUCTURE_ERROR")
class CollapsibleMenu : RComponent<CollapsibleMenuProps, RState>() {
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
                    attributes["data-parent"] = "#accordionSidebar"
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
