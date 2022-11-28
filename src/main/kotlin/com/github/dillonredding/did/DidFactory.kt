package com.github.dillonredding.did

import java.net.URI

fun did(baseUri: String): URI = URI.create(baseUri).let {
    val id = if (it.port == -1) it.host else "${it.host}%3A${it.port}"
    URI.create("did:web:$id")
}
