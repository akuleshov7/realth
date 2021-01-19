package org.cqfn.realth.frontend

import kotlinx.browser.document
import org.cqfn.realth.frontend.components.Dashboard
import org.cqfn.realth.frontend.components.sidebar.Sidebar
import react.dom.render

@Suppress("EMPTY_BLOCK_STRUCTURE_ERROR")
fun main() {
    render(document.getElementById("sidebar-wrapper")) {
        child(Sidebar::class) {}
    }

    render(document.getElementById("dashboard-wrapper")) {
        child(Dashboard::class) {}
    }

    setupPage()
}
