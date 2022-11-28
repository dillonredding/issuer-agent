package com.github.dillonredding.vc

import kotlinx.serialization.Serializable
import java.util.UUID.randomUUID

@Serializable
class InvitationBody {
    val goalCode = "streamlined-vc"
    val accept = listOf("didcomm/v2")
}

@Serializable
data class Invitation(
    val from: String,
    val id: String = randomUUID().toString(),
    val body: InvitationBody = InvitationBody()
) {
    val type = "https://didcomm.org/out-of-band/2.0/invitation"
}
