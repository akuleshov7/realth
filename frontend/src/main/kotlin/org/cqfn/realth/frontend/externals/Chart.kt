/**
 * External declarations for Chart.js
 */

package org.cqfn.realth.frontend.externals

import org.w3c.dom.RenderingContext
import kotlin.js.Json

/**
 * A class for Chart
 */
@JsModule("chart.js")
@JsNonModule
external class Chart(context: RenderingContext, config: Json)
