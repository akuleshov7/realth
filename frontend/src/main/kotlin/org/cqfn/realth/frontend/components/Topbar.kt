package org.cqfn.realth.frontend.components

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button
import react.dom.div
import react.dom.form
import react.dom.i
import react.dom.input
import react.dom.nav

import kotlinx.html.ButtonType
import kotlinx.html.InputType
import kotlinx.html.id

/**
 * A [RComponent] for top bar
 */
@Suppress("EMPTY_BLOCK_STRUCTURE_ERROR")
class Topbar : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        nav("navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow") {
            // Sidebar Toggle (Topbar)
            button(classes = "btn btn-link d-md-none rounded-circle mr-3") {
                attrs.id = "sidebarToggleTop"
                i("fa fa-bars") {}
            }

            // Topbar Search
            form(classes = "d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search") {
                div("input-group") {
                    input(type = InputType.text, classes = "form-control bg-light border-0 small") {
                        attrs.placeholder = "Enter git repo URL..."
                        attrs["aria-label"] = "Search"
                        attrs["aria-describedby"] = "basic-addon2"
                    }
                    div("input-group-append") {
                        button(type = ButtonType.button, classes = "btn btn-primary") {
                            i("fas fa-folder-open fa-sm") {}
                        }
                    }
                }
            }
        }
    }
}
