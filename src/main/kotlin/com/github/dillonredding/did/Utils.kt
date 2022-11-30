package com.github.dillonredding.did

import foundation.identity.did.DIDDocument
import foundation.identity.did.Service
import foundation.identity.did.VerificationMethod
import io.ipfs.multibase.Multibase
import java.net.URI

fun did(baseUri: String): URI = URI.create(baseUri).let {
    val id = if (it.port == -1) it.host else "${it.host}%3A${it.port}"
    URI.create("did:web:$id")
}

fun didDoc(did: URI, publicKey: ByteArray, didCommMessagingUri: URI): DIDDocument {
    val verificationMethod = VerificationMethod.builder()
        .id(URI.create("$did#public-key"))
        .type("Ed25519VerificationKey2020")
        .controller(did.toString())
        .publicKeyMultibase(Multibase.encode(Multibase.Base.Base58BTC, publicKey))
        .build()

    val service = Service.builder()
        .id(URI.create("$did#did-comm"))
        .type("DIDCommMessaging")
        .serviceEndpoint(didCommMessagingUri.toString())
        .build()

    return DIDDocument.builder()
        .id(did)
        .verificationMethod(verificationMethod)
        .service(service)
        .build()
}
