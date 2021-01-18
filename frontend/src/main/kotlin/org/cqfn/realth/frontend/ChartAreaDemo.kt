package org.cqfn.realth.frontend

import kotlinx.browser.document
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.RenderingContext
import kotlin.js.Json
import kotlin.js.json

@JsModule("chart.js")
@JsNonModule
@JsName("Chart")
external class Chart(context: RenderingContext, config: Json)

fun addLineChart() {
    val ctx = (document.getElementById("myAreaChart") as HTMLCanvasElement).getContext("2d")
        ?: error("will not create chart, ctx is null")
    val chart = Chart(ctx, json(
        "type" to "line",
        "data" to json(
            "labels" to arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"),
            "datasets" to arrayOf(
                json(
                    "label" to "Earnings",
                    "lineTension" to 0.3,
                    "backgroundColor" to "rgba(78, 115, 223, 0.05)",
                    "borderColor" to "rgba(78, 115, 223, 1)",
                    "pointRadius" to 3,
                    "pointBackgroundColor" to "rgba(78, 115, 223, 1)",
                    "pointBorderColor" to "rgba(78, 115, 223, 1)",
                    "pointHoverRadius" to 3,
                    "pointHoverBackgroundColor" to "rgba(78, 115, 223, 1)",
                    "pointHoverBorderColor" to "rgba(78, 115, 223, 1)",
                    "pointHitRadius" to 10,
                    "pointBorderWidth" to 2,
                    "data" to arrayOf(0, 10000, 5000, 15000, 10000, 20000, 15000, 25000, 20000, 30000, 25000, 40000),
                )
            )
        ),
        "options" to json(
            "scales" to json(
                "yAxes" to arrayOf(
                    json(
                        "ticks" to json(
                            "beginAtZero" to true
                        )
                    )
                )
            )
        )
    )
    )
}