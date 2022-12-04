package com.github.dillonredding.credential

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataDisplayDescriptor(
    val title: DisplayMapping? = null,
    val subtitle: DisplayMapping? = null,
    val description: DisplayMapping? = null,
    val properties: DisplayMapping? = null
) {
    init {
        // properties must be a labeled display mapping
        require(properties == null || properties.isLabeled)
    }
}

sealed interface DisplayMapping {
    val label: String?

    val isLabeled: Boolean
        get() = label != null
}

@Serializable
@SerialName("path")
data class PathDisplayMapping(
    val path: List<String>,
    val schema: PathDisplayMappingSchema,
    val fallback: String? = null,
    override val label: String? = null
) : DisplayMapping

@Serializable
data class PathDisplayMappingSchema(val type: PathDisplayMappingSchemaType, val format: PathDisplayMappingSchemaFormat? = null) {
    init {
        // format can only be specified when type is STRING
        require(format == null || type == PathDisplayMappingSchemaType.STRING)
    }
}

@Serializable
enum class PathDisplayMappingSchemaType {
    @SerialName("boolean")
    BOOLEAN,
    @SerialName("number")
    NUMBER,
    @SerialName("integer")
    INTEGER,
    @SerialName("string")
    STRING
}

@Serializable
enum class PathDisplayMappingSchemaFormat {
    @SerialName("date-time")
    DATE_TIME,
    @SerialName("time")
    TIME,
    @SerialName("date")
    DATE,
    @SerialName("email")
    EMAIL,
    @SerialName("idn-email")
    IDN_EMAIL,
    @SerialName("hostname")
    HOSTNAME,
    @SerialName("idn-hostname")
    IDN_HOSTNAME,
    @SerialName("ipv4")
    IPV4,
    @SerialName("ipv6")
    IPV6,
    @SerialName("uri")
    URI,
    @SerialName("uri-reference")
    URI_REFERENCE,
    @SerialName("iri")
    IRI,
    @SerialName("iri-reference")
    IRI_REFERENCE
}

@Serializable
@SerialName("text")
data class TextDisplayMapping(val text: String, override val label: String? = null) : DisplayMapping
