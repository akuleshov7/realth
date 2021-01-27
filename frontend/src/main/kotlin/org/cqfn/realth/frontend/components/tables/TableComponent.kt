/**
 * Utilities for react-tables
 */

@file:Suppress("FILE_NAME_MATCH_CLASS", "MatchingDeclarationName")

package org.cqfn.realth.frontend.components.tables

import react.RBuilder
import react.RComponent
import react.RProps
import react.dom.div
import react.dom.h6
import react.dom.table
import react.dom.tbody
import react.dom.th
import react.dom.thead
import react.dom.tr
import react.functionalComponent
import react.table.Column
import react.table.TableInstance
import react.table.useTable

import kotlinx.html.role

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
 *
 * @param data array of data of type [out D] that will be inserted into the table
 * @param columns columns as an array of [Column]
 * @return a functional react component
 */
@Suppress("TOO_LONG_FUNCTION")
fun <D : Any> RBuilder.tableComponent(data: Array<out D>, columns: Array<out Column<D, *>>) = functionalComponent<TableProps> { props ->
    val tableInstance: TableInstance<D> = useTable {
        this.columns = columns
        this.data = data
    }

    div("card shadow mb-4") {
        div("card-header py-3") {
            h6("m-0 font-weight-bold text-primary") {
                +props.tableHeader
            }
        }
        div("card-body") {
            div("table-responsive") {
                table("table table-bordered") {
                    tableInstance.getTableProps().let { tableProps ->
                        attrs.role = tableProps.role
                    }
                    attrs["width"] = "100%"
                    attrs["cellSpacing"] = "0"
                    thead {
                        tableInstance.headerGroups.map { headerGroup ->
                            tr {
                                headerGroup.headers.map { column ->
                                    th {
                                        +column.render("Header")
                                    }
                                }
                            }
                        }
                    }
                    tbody {
                        tableInstance.rows.map { row ->
                            tableInstance.prepareRow(row)
                            tr {
                                row.cells.map { cell ->
                                    child(cell.render("Cell"))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
