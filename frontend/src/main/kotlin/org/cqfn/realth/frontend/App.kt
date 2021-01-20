/**
 * Main entrypoint for REALTH frontend
 */

package org.cqfn.realth.frontend

import org.cqfn.realth.frontend.components.Dashboard
import org.cqfn.realth.frontend.components.sidebar.Sidebar

import react.dom.render

import kotlinx.browser.document
import org.cqfn.realth.frontend.components.Footer

@Suppress("EMPTY_BLOCK_STRUCTURE_ERROR")
fun main() {
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
