package org.cqfn.realth.frontend

import js.externals.jquery.`$`

fun setupPage() {
    `$`("document").ready {
        // Toggle the side navigation
        `$`("#sidebarToggle, #sidebarToggleTop").get().forEach {
            // todo: on("click", handler) via jQuery doesn't work somehow
            it.onclick = {
                `$`("body").toggleClass<Boolean>("sidebar-toggled")
                `$`(".sidebar").toggleClass<Boolean>("toggled")
                if (`$`(".sidebar").hasClass("toggled")) {
                    `$`(".sidebar .collapse").addClass("hide")
                }
            }
        }
    }
}
