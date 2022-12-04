package com.github.dillonredding.did

import com.github.dillonredding.common.randomUUIDString
import com.github.dillonredding.common.toURI
import com.github.dillonredding.credential.CredentialIssuer
import com.github.dillonredding.credential.CredentialManifest
import com.github.dillonredding.credential.CredentialManifestMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement

fun handleMessage(message: DIDCommMessage): DIDCommMessage =
    when (message.type) {
        DIDCommMessage.Types.PROPOSE_CREDENTIAL -> handleProposal(message)
        DIDCommMessage.Types.REQUEST_CREDENTIAL -> handleRequest(message)
        DIDCommMessage.Types.ACK -> handleAck(message)
        else -> handleUnknown(message)
    }

private fun handleProposal(message: DIDCommMessage) =
    DIDCommMessage(
        id = randomUUIDString(),
        type = DIDCommMessage.Types.OFFER_CREDENTIAL,
        threadId = message.id,
        from = "did:web:example.com".toURI(), // TODO: get actual DID
        to = message.from?.let { listOf(it) } ?: emptyList(),
        attachments = listOf(
            DIDCommMessageAttachment(
                id = randomUUIDString(),
                mediaType = "application/json",
                format = "dif/credential-manifest/manifest@v1.0",
                data = DIDCommMessageAttachmentData(
                    json = Json.encodeToJsonElement(
                        CredentialManifestMessage(
                            CredentialManifest(
                                id = randomUUIDString(),
                                name = "Fake ID Credential Manifest",
                                issuer = CredentialIssuer(
                                    id = "did:web:example.com".toURI(), // TODO: get actual DID
                                    name = "Fake ID Authority"
                                )
                            )
                        )
                    ) as JsonObject
                )
            )
        )
    )

private fun handleRequest(message: DIDCommMessage) =
    DIDCommMessage(
        id = randomUUIDString(),
        type = DIDCommMessage.Types.ISSUE_CREDENTIAL,
        threadId = message.threadId ?: message.id,
        from = "did:web:example.com".toURI(), // TODO: get actual DID
        to = message.from?.let { listOf(it) } ?: emptyList()
        // TODO: create VerifiableCredential from CredentialApplication
    )

private fun handleAck(message: DIDCommMessage) =
    DIDCommMessage(
        id = randomUUIDString(),
        type = DIDCommMessage.Types.ACK,
        threadId = message.threadId ?: message.id,
        from = "did:web:example.com".toURI(), // TODO: get actual DID
        to = message.from?.let { listOf(it) } ?: emptyList()
    )

private fun handleUnknown(message: DIDCommMessage): DIDCommMessage {
    TODO("cannot handle unknown message type ${message.type}")
}