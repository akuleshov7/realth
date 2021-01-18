package org.cqfn.realth.frontend.components.sidebar

import kotlinx.html.id
import kotlinx.html.js.onClickFunction
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import react.dom.button
import react.dom.div
import react.dom.h6
import react.dom.hr
import react.dom.i
import react.dom.img
import react.dom.li
import react.dom.p
import react.dom.span
import react.dom.strong
import react.dom.sup
import react.dom.ul

class Sidebar : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        ul("navbar-nav bg-gradient-primary sidebar sidebar-dark accordion") {
            attrs.id = "accordionSidebar"

            // Sidebar - Brand
            a(href = "index.html", classes = "sidebar-brand d-flex align-items-center justify-content-center") {
                div("sidebar-brand-icon rotate-n-15") {
                    i("fas fa-laugh-wink") {}
                }
                div("sidebar-brand-text mx-3") {
                    +"SB Admin "
                    sup { +"2" }
                }
            }

            child(Divider::class) {}

            // Nav Item - Dashboard
            li("nav-item active") {
                a(href="index.html", classes = "nav-link") {
                    i("fas fa-fw fa-tachometer-alt") {}
                    span {
                        +"Dashboard"
                    }
                }
            }

            child(Divider::class) {}

            sidebarHeading("Interface")

            // Nav Item - Pages collapsible menu
            child(CollapsibleMenu::class) {
                attrs {
                    header = "Components"
                    headerFaClass = "fa-cog"
                    header2 = "Custom Components:"
                    collapsibleDivId = "collapseTwo"
                    ariaLabeledBy = "headingTwo"
                    items = listOf("buttons.html" to "buttons", "cards.html" to "cards")
                }
            }

            // Nav Item - Utilities collapsible menu
            child(CollapsibleMenu::class) {
                attrs {
                    header = "Utilities"
                    headerFaClass = "fa-wrench"
                    header2 = "Custom Utilities:"
                    collapsibleDivId = "collapseUtilities"
                    ariaLabeledBy = "headingUtilities"
                    items = listOf(
                        "utilities-color.html" to "Colors",
                        "utilities-border.html" to "Borders",
                        "utilities-animation.html" to "Animations",
                        "utilities-other.html" to "Other",
                    )
                }
            }

            child(Divider::class) {}

            sidebarHeading("Addons")

            // Nav Item - Pages Collapse Menu
            child(CollapsibleMenu::class) {
                attrs {
                    header = "Pages"
                    headerFaClass = "fa-folder"
                    header2 = "Login Screens:"
                    collapsibleDivId = "collapsePages"
                    ariaLabeledBy = "headingPages"
                    items = listOf(
                        "login.html" to "Login",
                        "register.html" to "Register",
                        "forgot-password.html" to "Forgot Password"
                    )
                    // todo: support multiple headers in collapsed area
                    // Other Pages:
                    // <a class="collapse-item" href="404.html">404 Page</a>
                    // <a class="collapse-item" href="blank.html">Blank Page</a>
                }
            }

            navItem("charts.html", "Charts", "fas fa-fw fa-chart-area")
            navItem("tables.html", "Tables", "fas fa-fw fa-table")

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
                    strong { +"SB Admin Pro" }
                    +" is packed with premium features, components, and more!"
                }
                a(href = "https://startbootstrap.com/theme/sb-admin-pro", classes = "btn btn-success btn-sm") {
                    +"Upgrade to Pro!"
                }
            }
        }
    }
}

class Divider: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        hr("sidebar-divider") {}
    }
}

// todo function or RComponent?
fun RBuilder.sidebarHeading(text: String) = div("sidebar-heading") {
    +text
}

fun RBuilder.navItem(href: String,
                     text: String,
                     iconClasses: String) = li("nav-item") {
    a(href, classes = "nav-link") {
        i(iconClasses) {}
        span { +text }
    }
}