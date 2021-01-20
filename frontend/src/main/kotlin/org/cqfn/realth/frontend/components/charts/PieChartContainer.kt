package org.cqfn.realth.frontend.components.charts

import org.cqfn.realth.frontend.externals.Chart
import kotlin.js.json

class PieChartContainer : ChartContainer() {
    override fun componentDidMount() {
        super.componentDidMount()
        Chart(
            context2D,
            json(
                "type" to "doughnut",
                "data" to json(
                    "labels" to arrayOf("VCS", "Tests", "Project structure"),
                    "datasets" to arrayOf(
                        json(
                            "data" to arrayOf(13, 12, 4),
                            "backgroundColor" to arrayOf("#4e73df", "#1cc88a", "#36b9cc"),
                            "hoverBackgroundColor" to arrayOf("#2e59d9", "#17a673", "#2c9faf"),
                            "hoverBorderColor" to "rgba(234, 236, 244, 1)"
                        )
                    )
                )
            )
        )
    }
}
