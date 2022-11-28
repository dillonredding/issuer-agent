package com.github.dillonredding.plugins

import com.github.dillonredding.did.did
import com.github.dillonredding.did.doc.didDoc
import com.github.dillonredding.vc.Invitation
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*
import java.net.URI

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }

    val publicKey = environment.config.property("keys.public").getString()
    val privateKey = environment.config.property("keys.private").getString()

    val baseUri = environment.config.property("baseUri").getString()
    val did = did(baseUri)

    routing {
        get("/invitation") {
            call.respond(Invitation(from = did.toString()))
        }

        get("/.well-known/did.json") {
            val didDoc = didDoc(did, publicKey.decodeBase64Bytes(), URI.create("$baseUri/credential-issuance"))
            call.respond(didDoc.toJson())
        }

        post("/credential-issuance") {
            call.respondText("Work in progress", status = HttpStatusCode.Accepted)
        }
    }
}
