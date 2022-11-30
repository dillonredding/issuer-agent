package com.github.dillonredding.did

import com.github.dillonredding.common.toURI

fun handleMessage(message: DIDCommMessage): DIDCommMessage =
    when (message.type) {
        DIDCommMessage.Types.PROPOSE_CREDENTIAL -> handleProposal(message)
        DIDCommMessage.Types.REQUEST_CREDENTIAL -> handleRequest(message)
        DIDCommMessage.Types.ACK -> handleAck(message)
        else -> handleUnknown(message)
    }

private fun handleProposal(message: DIDCommMessage) =
    DIDCommMessage(
        type = DIDCommMessage.Types.OFFER_CREDENTIAL,
        threadId = message.id,
        from = "did:web:example.com".toURI(), // TODO: get actual DID
        to = message.from?.let { listOf(it) } ?: emptyList()
        // TODO: create & "attach" CredentialManifest
    )

private fun handleRequest(message: DIDCommMessage): DIDCommMessage {
    return DIDCommMessage(
        type = DIDCommMessage.Types.ISSUE_CREDENTIAL,
        threadId = message.threadId ?: message.id,
        from = "did:web:example.com".toURI(), // TODO: get actual DID
        to = message.from?.let { listOf(it) } ?: emptyList()
        // TODO: create VerifiableCredential from CredentialApplication
    )
}

private fun handleAck(message: DIDCommMessage): DIDCommMessage {
    return DIDCommMessage(
        type = DIDCommMessage.Types.ACK,
        threadId = message.threadId ?: message.id,
        from = "did:web:example.com".toURI(), // TODO: get actual DID
        to = message.from?.let { listOf(it) } ?: emptyList()
    )
}

private fun handleUnknown(message: DIDCommMessage): DIDCommMessage {
    TODO("cannot handle unknown message type ${message.type}")
}