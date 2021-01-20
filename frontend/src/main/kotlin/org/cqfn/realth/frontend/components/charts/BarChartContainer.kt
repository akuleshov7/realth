package org.cqfn.realth.frontend.components.charts

import org.cqfn.realth.frontend.externals.Chart
import kotlin.js.json

class BarChartContainer : ChartContainer() {
    override fun componentDidMount() {
        super.componentDidMount()
        Chart(
            context2D,
            json(
                "type" to "horizontalBar",
                "data" to json(
                    "labels" to arrayOf("Mixed committer name/email", "Large module", "Generated code committed"),
                    "datasets" to arrayOf(
                        json(
                            "data" to arrayOf(23, 2, 42)
                        )
                    )
                )
            )
        )
    }
}