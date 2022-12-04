package com.github.dillonredding.credential

import com.github.dillonredding.common.URISerializer
import kotlinx.serialization.Serializable
import java.net.URI

@Serializable
data class CredentialIssuer(
    @Serializable(with = URISerializer::class)
    val id: URI,
    val name: String? = null,
    val styles: EntityStyles? = null
)