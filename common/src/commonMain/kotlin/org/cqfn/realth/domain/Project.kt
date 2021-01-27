package org.cqfn.realth.domain

/**
 * @property name name of the project
 * @property user owner of the project
 * @property url URL to project repo page
 */
data class Project(
    val name: String,
    val user: String,
    val url: String)
