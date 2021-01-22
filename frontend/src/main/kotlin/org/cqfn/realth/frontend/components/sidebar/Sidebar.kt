package org.cqfn.realth.frontend.components.sidebar

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import react.dom.button
import react.dom.div
import react.dom.hr
import react.dom.i
import react.dom.img
import react.dom.li
import react.dom.p
import react.dom.span
import react.dom.strong
import react.dom.ul

import kotlinx.html.id

/**
 * A [RComponent] for the left sidebar
 */
@Suppress("EMPTY_BLOCK_STRUCTURE_ERROR", "TOO_LONG_FUNCTION", "LongMethod")
class Sidebar : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        ul("navbar-nav bg-gradient-primary sidebar sidebar-dark accordion") {
            attrs.id = "accordionSidebar"

            // Sidebar - Brand
            a(href = "index.html", classes = "sidebar-brand d-flex align-items-center justify-content-center") {
                div("sidebar-brand-icon rotate-n-15") {
                    i("fas fa-tools") {}
                }
                div("sidebar-brand-text mx-3") {
                    +"REALTH"
                }
            }

            divider()

            // Nav Item - Dashboard
            li("nav-item active") {
                a(href = "index.html", classes = "nav-link") {
                    i("fas fa-fw fa-tachometer-alt") {}
                    span {
                        +"Dashboard"
                    }
                }
            }

            divider()

            sidebarHeading("Repositories")

            // Nav Item - Repositories collapsible menu
            child(CollapsibleMenu::class) {
                attrs {
                    header = "My repositories"
                    headerFaClass = "fa-cog"
                    header2 = "My repositories:"
                    collapsibleDivId = "collapseTwo"
                    ariaLabeledBy = "headingTwo"
                    items = listOf("buttons.html" to "realth", "cards.html" to "diktat")
                }
            }

            // Nav Item - Github overview collapsible menu
            child(CollapsibleMenu::class) {
                attrs {
                    header = "Github"
                    headerFaClass = "fa-github"
                    header2 = "Github overview:"
                    collapsibleDivId = "collapseUtilities"
                    ariaLabeledBy = "headingUtilities"
                    items = listOf("github.html" to "github")
                }
            }

            divider()

            sidebarHeading("Quality analysis")

            child(CollapsibleMenu::class) {
                attrs {
                    header = "Issues"
                    headerFaClass = "fa-search"
                    header2 = "Issues:"
                    collapsibleDivId = "collapsePages"
                    ariaLabeledBy = "headingPages"
                    items = listOf(
                        "types.html" to "Issue types",
                        "vcs.html" to "VCS",
                        "project-structure.html" to "Project structure"
                    )
                }
            }

            navItem("realth.html", "About us", "fas fa-fw fa-address-card")

            hr("sidebar-divider d-none d-md-block") {}

            // Sidebar Toggler (Sidebar)
            div("text-center d-none d-md-inline") {
                button(classes = "rounded-circle border-0") {
                    attrs.id = "sidebarToggle"
                }
            }

            // Sidebar Message
            div("sidebar-card") {
                img("", "img/undraw_rocket.svg", "sidebar-card-illustration mb-2") {}
                p("text-center mb-2") {
                    strong { +"REALTH" }
                    +" is an open-source project aiming to provide full quality analysis of any git repo"
                }
                a(href = "https://github.com/cqfn/realth", classes = "btn btn-success btn-sm") {
                    +"View on GitHub"
                }
            }
        }
    }

    /**
     * @param text header text
     * @return a [ReactElement]
     */
    private fun RBuilder.sidebarHeading(text: String) = div("sidebar-heading") {
        +text
    }

    /**
     * @param href a link
     * @param text link text
     * @param iconClasses link icons from font-awesome
     * @return a [ReactElement]
     */
    private fun RBuilder.navItem(href: String,
                                 text: String,
                                 iconClasses: String) = li("nav-item") {
        a(href, classes = "nav-link") {
            i(iconClasses) {}
            span { +text }
        }
    }

    /**
     * @return [ReactElement] for a divider
     */
    private fun RBuilder.divider() = hr("sidebar-divider") {}
}
