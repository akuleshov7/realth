/**
 * Logic for page initialization that hasn't been converted to React
 */

package org.cqfn.realth.frontend

import js.externals.jquery.`$`
import kotlin.js.json

/**
 * Enables sidebar collapsing
 */
@Suppress("ForbiddenComment")
fun setupPage() {
    `$`("document").ready {
        // Toggle the side navigation
        `$`("#sidebarToggle, #sidebarToggleTop").get().forEach {
            // todo: on("click", handler) via jQuery doesn't work somehow
            it.onclick = {
                `$`("body").toggleClass<Boolean>("sidebar-toggled")
                `$`(".sidebar").toggleClass<Boolean>("toggled")
                if (`$`(".sidebar").hasClass("toggled")) {
                    `$`(".sidebar .collapse").addClass("hide")
                }
            }
        }
    }
}

/**
 * Additional configuration for Chart.js. Sets default fonts and colors.
 */
fun configureChartJs() {
    eval("Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,\"Segoe UI\",Roboto,\"Helvetica Neue\",Arial,sans-serif';")
    eval("Chart.defaults.global.defaultFontColor = '#858796';")
}

/**
 * Launch datatables plugin via jQuery
 */
fun setupDataTables() {
    `$`("document").ready {
        val dataTable = `$`("#example").asDynamic().dataTable(
            json(
                "processing" to true,
                "serverSide" to true,
                "ajax" to "scripts/server_processing.php",
                "deferLoading" to 57
            )
        )
    }
}
