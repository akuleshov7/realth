/**
 * Methods to use a RComponent for a collection of projects
 */

package org.cqfn.realth.frontend.components

import org.cqfn.realth.domain.Project
import org.cqfn.realth.frontend.components.tables.tableComponent
import react.RBuilder
import react.RComponent
import react.dom.td
import react.table.columns

/**
 * A [RComponent] for a dashboard with projects table
 *
 * @return a functional react component
 */
internal fun RBuilder.collectionView() = tableComponent(
    data = arrayOf(
        Project("diKTat", "cqfn", "https://github.com/cqfn/diktat"),
        Project("realth", "cqfn", "https://github.com/cqfn/realth")
    ),
    columns = columns {
        column(id = "projectName", header = "Project name") {
            td {
                +it.value.name
            }
        }
        column(id = "user", header = "Owner") {
            td {
                +it.value.user
            }
        }
        column(id = "projectUrl", header = "Project url") {
            td {
                +it.value.url
            }
        }
        column(id = "projectQualityIndex", header = "Quality") {
            td {
                +"${it.value.qualityIndex ?: "N/A"}"
            }
        }
    }
)
