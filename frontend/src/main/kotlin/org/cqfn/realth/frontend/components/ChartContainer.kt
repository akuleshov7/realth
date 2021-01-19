package org.cqfn.realth.frontend.components

import org.cqfn.realth.frontend.externals.Chart
import org.w3c.dom.HTMLCanvasElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RReadableRef
import react.RState
import react.createRef
import react.dom.canvas
import kotlin.js.json

class ChartContainer : RComponent<RProps, RState>() {
    private val canvasRef: RReadableRef<HTMLCanvasElement> = createRef()

    override fun RBuilder.render() {
        canvas {
            ref = canvasRef
        }
    }

    override fun componentDidMount() {
        val ctx = canvasRef.current?.getContext("2d")
            ?.takeUnless { it == undefined }
            ?: error("Context unavailable")
        val chart = Chart(ctx, json(
            "type" to "line",
            "data" to json(
                "labels" to arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"),
                "datasets" to arrayOf(
                    json(
                        "label" to "Commit frequency",
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
}
