package org.cqfn.realth.frontend.components.cards

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

/**
 * A [RComponent] representing a row of cards
 */
class CardsRow : RComponent<RProps, RState>() {
    @Suppress("TOO_LONG_FUNCTION")
    override fun RBuilder.render() {
        child(Card::class) {
            attrs {
                header = "Number of tests"
                value = "150"
                faIcon = "fa-vials"
                leftBorderColor = "primary"
            }
        }

        child(Card::class) {
            attrs {
                header = "Static Analysis"
                value = "Enabled"
                faIcon = "fa-chart-pie"
                leftBorderColor = "info"
            }
        }

        child(Card::class) {
            attrs {
                header = "Project Structure"
                value = "Good"
                faIcon = "fa-project-diagram"
                leftBorderColor = "success"
            }
        }

        child(Card::class) {
            attrs {
                header = "Quality index"
                value = "9"
                faIcon = "fa-thumbs-up"
                leftBorderColor = "success"
            }
        }

        child(Card::class) {
            attrs {
                header = "New issues"
                value = "3"
                faIcon = "fa-exclamation-triangle"
                leftBorderColor = "danger"
            }
        }

        child(Card::class) {
            attrs {
                header = "Github ranking"
                value = "376"
                faIcon = "fa-list"
                leftBorderColor = "primary"
            }
        }
    }
}
