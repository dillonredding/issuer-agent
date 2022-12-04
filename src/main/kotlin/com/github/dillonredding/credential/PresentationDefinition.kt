package com.github.dillonredding.credential

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class PresentationDefinition(
    val id: String,
    @SerialName("input_descriptors")
    val inputDescriptors: List<InputDescriptor>,
    val name: String? = null,
    val purpose: String? = null,
    val format: PresentationDefinitionFormat,
    val frame: JsonObject? = null,
    @SerialName("submission_requirements")
    val submissionRequirements: List<SubmissionRequirement>
)


