package org.cqfn.realth.domain

/**
 * @property name name of the project
 * @property user owner of the project
 * @property url URL to project repo page
 * @property qualityIndex index of project quality, can be unset if not yet calculated
 */
data class Project(
    val name: String,
    val user: String,
    val url: String,
    val qualityIndex: Int? = null,
)
