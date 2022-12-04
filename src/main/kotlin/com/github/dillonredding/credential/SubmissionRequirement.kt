package com.github.dillonredding.credential

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed interface SubmissionRequirement {
    val rule: SubmissionRequirementRule
    val name: String?
    val purpose: String?
    val count: UInt?
    val min: UInt?
    val max: UInt?
}

@Serializable
data class SubmissionRequirementFrom(
    override val rule: SubmissionRequirementRule,
    override val name: String?,
    override val purpose: String?,
    override val count: UInt?,
    override val min: UInt?,
    override val max: UInt?,
    val from: String
) : SubmissionRequirement {
    init {
        require(count == null || count > 0u)
    }
}

@Serializable
data class SubmissionRequirementFromNested(
    override val rule: SubmissionRequirementRule,
    override val name: String?,
    override val purpose: String?,
    override val count: UInt?,
    override val min: UInt?,
    override val max: UInt?,
    @SerialName("from_nested")
    val fromNested: List<SubmissionRequirement>? = null
) : SubmissionRequirement {
    init {
        require(count == null || count > 0u)
    }
}

@Serializable
enum class SubmissionRequirementRule {
    @SerialName("all")
    ALL,
    @SerialName("pick")
    PICK
}
