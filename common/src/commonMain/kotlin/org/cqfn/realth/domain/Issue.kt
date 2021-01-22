package org.cqfn.realth.domain

/**
 * @property name name of the issue
 * @property type type of the issue
 * @property description issue description
 */
data class Issue(
    val name: String,
    val type: IssueType,
    val description: String)
