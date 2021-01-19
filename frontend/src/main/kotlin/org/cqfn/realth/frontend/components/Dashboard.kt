/**
 * Components for the project dashboard
 */

package org.cqfn.realth.frontend.components

import org.cqfn.realth.frontend.components.cards.CardsRow
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import react.dom.div
import react.dom.h1
import react.dom.i

/**
 * A [RState] for project dashboard
 */
class DashboardState : RState {
    /**
     * Currently active project
     */
    lateinit var currentProject: String
}

/**
 * [RComponent] for the project dashboard
 */
@Suppress("EMPTY_BLOCK_STRUCTURE_ERROR")
class Dashboard : RComponent<RProps, DashboardState>() {
    init {
        state.currentProject = "REALTH"
    }

    override fun RBuilder.render() {
        // Page Heading
        div("d-sm-flex align-items-center justify-content-between mb-4") {
            h1("h3 mb-0 text-gray-800") {
                +"Dashboard for ${state.currentProject}"
            }
            a("#", classes = "d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm") {
                i("fas fa-download fa-sm text-white-50") {
                    +"Generate report"
                }
            }
        }

        div("row") {
            child(CardsRow::class) {}
        }

        div("row") {
            child(ChartContainer::class) {}
        }
    }
}
