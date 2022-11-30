package com.github.dillonredding.did

import com.github.dillonredding.common.randomUUIDString
import kotlinx.serialization.Serializable

@Serializable
data class DIDCommMessageAttachment(
    val id: String = randomUUIDString()
)
