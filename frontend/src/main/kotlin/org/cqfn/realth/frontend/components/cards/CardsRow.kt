package org.cqfn.realth.frontend.components.cards

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

/**
 * A [RComponent] representing a row of cards
 */
class CardsRow : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        child(Card::class) {
            attrs {
                header = "Commit Messages"
                value = "Consistent"
                faIcon = "fa-history"
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
                header = "Number of tests"
                value = "0"
                faIcon = "fa-vials"
                leftBorderColor = "danger"
            }
        }
    }
}
