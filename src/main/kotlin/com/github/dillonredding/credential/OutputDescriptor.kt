package com.github.dillonredding.credential

import kotlinx.serialization.Serializable

@Serializable
data class OutputDescriptor(
    val id: String,
    val schema: String,
    val name: String? = null,
    val description: String? = null,
    val styles: EntityStyles? = null,
    val display: DataDisplayDescriptor? = null
)
