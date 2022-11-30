package com.github.dillonredding.did

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

typealias DIDCommMessageBody = JsonObject

object DIDCommMessageBodies {
    val EMPTY = JsonObject(emptyMap())
    val INVITATION = JsonObject(
        mapOf(
            "goal_code" to JsonPrimitive("streamlined-vc"),
            "accept" to JsonArray(listOf(JsonPrimitive("didcomm/v2")))
        )
    )
}
