package com.github.dillonredding.credential

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class InputDescriptor(
    val id: String,
    val constraints: InputDescriptorConstraints,
    val name: String? = null,
    val purpose: String? = null,
    val format: InputDescriptorFormat? = null,
    val group: List<String>? = null
)

typealias InputDescriptorFormat = PresentationDefinitionFormat

@Serializable
data class InputDescriptorConstraints(
    @SerialName("limit_disclosure")
    val limitDisclosure: LimitDisclosure? = null,
    val statuses: StatusDirectives? = null,
    val fields: List<Field>? = null,
    @SerialName("subject_is_issuer")
    val subjectIsIssuer: LimitDisclosure? = null,
    @SerialName("is_holder")
    val isHolder: List<FieldDirective>? = null,
    @SerialName("same_subject")
    val sameSubject: List<FieldDirective>? = null
)

@Serializable
data class Field(
    @SerialName("path")
    val paths: List<String>,
    val id: String? = null,
    val optional: Boolean = false,
    val purpose: String? = null,
    val name: String? = null,
    @SerialName("intent_to_retain")
    val intentToRetain: Boolean = false,
    val filter: JsonSchema? = null,
    val predicate: LimitDisclosure? = null
) {
    init {
        // if a predicate is included, the filter must also be specified
        require(predicate == null || filter != null)
    }
}

typealias JsonSchema = JsonObject

@Serializable
data class FieldDirective(
    @SerialName("field_id")
    val fieldIds: List<String>? = null,
    val directive: LimitDisclosure? = null
)

@Serializable
enum class LimitDisclosure {
    @SerialName("required")
    REQUIRED,
    @SerialName("preferred")
    PREFERRED
}

@Serializable
data class StatusDirectives(
    val active: StatusDirective? = null,
    val suspended: StatusDirective? = null,
    val revoked: StatusDirective? = null
)

@Serializable
data class StatusDirective(
    val directive: Directive? = null,
    val type: List<String>? = null
) {
    init {
        require(type == null || type.isNotEmpty())
    }

    @Serializable
    enum class Directive {
        @SerialName("required")
        REQUIRED,
        @SerialName("allowed")
        ALLOWED,
        @SerialName("disallowed")
        DISALLOWED
    }
}
