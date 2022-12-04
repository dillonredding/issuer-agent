package com.github.dillonredding.credential

import com.github.dillonredding.common.URISerializer
import kotlinx.serialization.Serializable
import java.net.URI

@Serializable
data class EntityStyles(
    val thumbnail: EntityImage? = null,
    val hero: EntityImage? = null,
    val background: EntityColor? = null,
    val text: EntityColor? = null
)

@Serializable
data class EntityColor(val color: String) {
    init {
        // color must be a HEX string color (e.g., #696969, #420)
        require(color.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$".toRegex()))
    }
}

@Serializable
data class EntityImage(
    @Serializable(with = URISerializer::class)
    val uri: URI,
    val alt: String? = null
)
