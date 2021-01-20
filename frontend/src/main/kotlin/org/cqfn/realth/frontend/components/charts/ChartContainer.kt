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

class ChartProps : RProps {
    lateinit var header: String
}

/**
 * A [RComponent] representing a canvas with chart from chart.js
 */
abstract class ChartContainer : RComponent<ChartProps, RState>() {
    private val canvasRef: RReadableRef<HTMLCanvasElement> = createRef()
    protected lateinit var context2D: CanvasRenderingContext2D

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
        context2D = canvasRef.current?.getContext("2d")
            ?.takeUnless { it == undefined }
            ?.let { it as CanvasRenderingContext2D }
            ?: error("Rendering context unavailable")
    }
}
