/**
 * Classes to use datatables in kotlin-react
 */

package org.cqfn.realth.frontend.components.tables

import org.cqfn.realth.domain.Issue
import org.cqfn.realth.domain.IssueType

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h6
import react.dom.table
import react.dom.tbody
import react.dom.td
import react.dom.th
import react.dom.thead
import react.dom.tr

import kotlinx.html.id

/**
 * [RProps] of a data table
 */
class TableProps : RProps {
    /**
     * Table header
     */
    lateinit var tableHeader: String
}

/**
 * A [RComponent] for a data table
 */
class TableComponent : RComponent<TableProps, RState>() {
    @Suppress("TOO_LONG_FUNCTION")
    override fun RBuilder.render() {
        val issues = listOf(
            Issue("Inconsistent commit messages", IssueType.VCS, "Commit messages do not follow a specific format"),
            Issue("Committer name/email", IssueType.VCS, "There are committers with same email and different names"),
            Issue("Generated code committed", IssueType.VCS, "Generated code is committed to VCS"),
        )

        div("card shadow mb-4") {
            div("card-header py-3") {
                h6("m-0 font-weight-bold text-primary") {
                    +props.tableHeader
                }
            }
            div("card-body") {
                div("table-responsive") {
                    table("table table-bordered") {
                        attrs.id = "issuesTable"
                        attrs["width"] = "100%"
                        attrs["cellSpacing"] = "0"
                        thead {
                            tr {
                                th { +"#" }
                                th { +"Issue" }
                                th { +"Issue Type" }
                                th { +"Description" }
                            }
                        }
                        tbody {
                            issues.forEachIndexed { index, issue ->
                                tr {
                                    td { +index }
                                    td { +issue.name }
                                    td { +"${issue.type}" }
                                    td { +issue.description }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
