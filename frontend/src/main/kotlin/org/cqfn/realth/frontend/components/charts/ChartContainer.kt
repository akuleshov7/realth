/**
 * Classes for representing charts from Chart.js in react component
 */

package org.cqfn.realth.frontend.components.charts

import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import react.RBuilder
import react.RComponent
import react.RProps
import react.RReadableRef
import react.RState
import react.createRef
import react.dom.canvas
import react.dom.div
import react.dom.h6

/**
 * [RProps] for a chart
 */
class ChartProps : RProps {
    /**
     * Header of a chart
     */
    lateinit var header: String
}

/**
 * A [RComponent] representing a canvas with chart from chart.js
 */
abstract class ChartContainer : RComponent<ChartProps, RState>() {
    private val canvasRef: RReadableRef<HTMLCanvasElement> = createRef()

    /**
     * Rendering context from a canvas, that will be used for chart rendering
     */
    protected lateinit var context2d: CanvasRenderingContext2D

    override fun RBuilder.render() {
        div("col-xl-4 col-lg-5") {
            div("card shadow mb-4") {
                div("card-header py-3 d-flex flex-row align-items-center justify-content-between") {
                    h6("m-0 font-weight-bold text-primary") {
                        +props.header
                    }
                }
                div("card-body") {
                    div("pt-4 pb-2") {
                        canvas {
                            ref = canvasRef
                        }
                    }
                }
            }
        }
    }

    @Suppress("TOO_LONG_FUNCTION")
    override fun componentDidMount() {
        context2d = canvasRef.current?.getContext("2d")
            ?.takeUnless { it == undefined }
            ?.let { it as CanvasRenderingContext2D }
            ?: error("Rendering context unavailable")
        createChart()
    }

    abstract fun createChart()
}
