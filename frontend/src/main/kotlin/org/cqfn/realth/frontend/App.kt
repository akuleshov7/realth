/**
 * Main entrypoint for REALTH frontend
 */

package org.cqfn.realth.frontend

import org.cqfn.realth.frontend.components.Dashboard
import org.cqfn.realth.frontend.components.Footer
import org.cqfn.realth.frontend.components.sidebar.Sidebar

import react.dom.render

import kotlinx.browser.document
import kotlinx.html.id
import org.cqfn.realth.frontend.components.Topbar
import react.dom.div

@Suppress("EMPTY_BLOCK_STRUCTURE_ERROR")
fun main() {
    kotlinext.js.require("../scss/realth.scss")  // this is needed for webpack to include resource
    render(document.getElementById("wrapper")) {
        child(Sidebar::class) {}
        div("d-flex flex-column") {
            attrs.id = "content-wrapper"
            child(Topbar::class) {}
            div("container-fluid") {
                child(Dashboard::class) {}
            }
            child(Footer::class) {}
        }
    }

    setupPage()
    configureChartJs()
}
