package com.github.dillonredding.did

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class DIDCommMessageAttachment(
    val id: String,
    val data: DIDCommMessageAttachmentData,
    val description: String? = null,
    val filename: String? = null,
    @SerialName("media_type")
    val mediaType: String? = null,
    val format: String? = null,
    @SerialName("lastmod_time")
    val lastModTime: Long? = null,
    @SerialName("byte_count")
    val byteCount: Int? = null
)

@Serializable
data class DIDCommMessageAttachmentData(
    val json: JsonObject
)
