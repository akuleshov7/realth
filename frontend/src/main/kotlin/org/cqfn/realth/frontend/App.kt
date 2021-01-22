/**
 * Main entrypoint for REALTH frontend
 */

package org.cqfn.realth.frontend

import org.cqfn.realth.frontend.components.Dashboard
import org.cqfn.realth.frontend.components.Footer
import org.cqfn.realth.frontend.components.sidebar.Sidebar

import react.dom.render

import kotlinx.browser.document

@Suppress("EMPTY_BLOCK_STRUCTURE_ERROR")
fun main() {
    kotlinext.js.require("../scss/realth.scss")  // this is needed for webpack to include resource
    render(document.getElementById("sidebar-wrapper")) {
        child(Sidebar::class) {}
    }

    render(document.getElementById("dashboard-wrapper")) {
        child(Dashboard::class) {}
    }

    render(document.getElementById("footer")) {
        child(Footer::class) {}
    }

    setupPage()
    configureChartJs()
}
