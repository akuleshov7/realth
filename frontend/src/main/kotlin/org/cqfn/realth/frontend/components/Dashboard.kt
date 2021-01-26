/**
 * Components for the project dashboard
 */

package org.cqfn.realth.frontend.components

import org.cqfn.realth.domain.Issue
import org.cqfn.realth.domain.IssueType
import org.cqfn.realth.frontend.components.cards.CardsRow
import org.cqfn.realth.frontend.components.charts.BarChartContainer
import org.cqfn.realth.frontend.components.charts.PieChartContainer
import org.cqfn.realth.frontend.components.tables.tableComponent

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.child
import react.dom.a
import react.dom.div
import react.dom.h1
import react.dom.i
import react.dom.td
import react.table.columns

/**
 * A [RProps] for project dashboard
 */
class ProjectProps : RProps {
    /**
     * Currently active project
     */
    lateinit var project: String

    /**
     * Organization (or user) on github, where this project belongs to
     */
    lateinit var organization: String

    /**
     * A URL of a currently active project's repo
     */
    lateinit var projectRepoUrl: String
}

/**
 * [RComponent] for the project dashboard
 */
@Suppress("EMPTY_BLOCK_STRUCTURE_ERROR")
class Dashboard : RComponent<ProjectProps, RState>() {
    override fun RBuilder.render() {
        // Page Heading
        div("d-sm-flex align-items-center justify-content-between mb-4") {
            h1("h3 mb-0 text-gray-800") {
                +"Dashboard for ${props.project} (${props.projectRepoUrl})"
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
            child(PieChartContainer::class) {
                attrs {
                    header = "Issue summary"
                }
            }
            child(BarChartContainer::class) {
                attrs {
                    header = "Top issues"
                }
            }
        }

        child(tableComponent(
            data = arrayOf(
                Issue("Inconsistent commit messages", IssueType.VCS, "Commit messages do not follow a specific format"),
                Issue("Committer name/email", IssueType.VCS, "There are committers with same email and different names"),
                Issue("Generated code committed", IssueType.VCS, "Generated code is committed to VCS"),
            ),
            columns = columns {
                column(id = "index", header = "#") {
                    td {
                        +"${it.row.index}"
                    }
                }
                column(id = "issueName", header = "Name") {
                    td {
                        +it.value.name
                    }
                }
                column(id = "issueType", header = "Type") {
                    td {
                        +"${it.value.type}"
                    }
                }
                column(id = "issueDescription", header = "Description") {
                    td {
                        +it.value.description
                    }
                }
            }
        )) {
            attrs {
                tableHeader = "Issues"
            }
        }
    }
}
