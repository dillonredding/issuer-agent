@file:UseSerializers(URISerializer::class)

package com.github.dillonredding.did

import com.github.dillonredding.common.URISerializer
import com.github.dillonredding.common.randomUUIDString
import com.github.dillonredding.common.toURI
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonObject
import java.net.URI

@Serializable
data class DIDCommMessage(
    @Serializable(with = URISerializer::class)
    val type: URI,
    val id: String,
    val body: DIDCommMessageBody = DIDCommMessageBodies.EMPTY,
    val from: URI? = null,
    val to: List<URI> = emptyList(),
    @SerialName("thid")
    val threadId: String? = null,
    @SerialName("pthid")
    val parentThreadId: String? = null,
    val attachments: List<DIDCommMessageAttachment> = emptyList(),
    @SerialName("created_time")
    val createdTime: Long? = null,
    @SerialName("expires_time")
    val expiresTime: Long? = null,
) {
    object Types {
        val ACK = "https://didcomm.org/issue-credential/3.0/ack".toURI()
        val INVITATION = "https://didcomm.org/out-of-band/2.0/invitation".toURI()
        val ISSUE_CREDENTIAL = "https://didcomm.org/issue-credential/3.0/issue-credential".toURI()
        val OFFER_CREDENTIAL = "https://didcomm.org/issue-credential/3.0/offer-credential".toURI()
        val PROPOSE_CREDENTIAL = "https://didcomm.org/issue-credential/3.0/propose-credential".toURI()
        val REQUEST_CREDENTIAL = "https://didcomm.org/issue-credential/3.0/request-credential".toURI()
    }
}
