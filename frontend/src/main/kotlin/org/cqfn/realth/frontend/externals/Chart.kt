package org.cqfn.realth.frontend.externals

import org.w3c.dom.RenderingContext
import kotlin.js.Json

@JsModule("chart.js")
@JsNonModule
@JsName("Chart")
external class Chart(context: RenderingContext, config: Json)
