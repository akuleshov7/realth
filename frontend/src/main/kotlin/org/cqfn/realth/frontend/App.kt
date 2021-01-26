/**
 * Main entrypoint for REALTH frontend
 */

package org.cqfn.realth.frontend

import org.cqfn.realth.frontend.components.Dashboard
import org.cqfn.realth.frontend.components.FallbackView
import org.cqfn.realth.frontend.components.Footer
import org.cqfn.realth.frontend.components.ProjectProps
import org.cqfn.realth.frontend.components.Topbar
import org.cqfn.realth.frontend.components.sidebar.Sidebar

import react.dom.div
import react.dom.render
import react.router.dom.hashRouter
import react.router.dom.route
import react.router.dom.switch

import kotlinx.browser.document
import kotlinx.html.id

@Suppress("EMPTY_BLOCK_STRUCTURE_ERROR")
fun main() {
    kotlinext.js.require("../scss/realth.scss")  // this is needed for webpack to include resource
    render(document.getElementById("wrapper")) {
        child(Sidebar::class) {}
        div("d-flex flex-column") {
            attrs.id = "content-wrapper"
            child(Topbar::class) {}
            div("container-fluid") {
                hashRouter {
                    switch {
                        route<ProjectProps>("/:organization/:project") { props ->
                            child(Dashboard::class) {
                                attrs {
                                    project = props.match.params.project
                                    organization = props.match.params.organization
                                    projectRepoUrl = "https://github.com/$organization/$project"
                                }
                            }
                        }
                        route("*", FallbackView::class)
                    }
                }
            }
            child(Footer::class) {}
        }
    }

    setupPage()
    configureChartJs()
}
