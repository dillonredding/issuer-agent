package com.github.dillonredding.credential

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PresentationDefinitionFormat(
    @SerialName("jwt")
    val jsonWebToken: JsonWebTokenPresentationDefinitionFormat? = null,
    @SerialName("jwt_vc")
    val jsonWebTokenVerifiableCredential: JsonWebTokenPresentationDefinitionFormat? = null,
    @SerialName("jwt_vp")
    val jsonWebTokenVerifiablePresentation: JsonWebTokenPresentationDefinitionFormat? = null,
    @SerialName("ldp")
    val linkedDataProof: LinkedDataProofPresentationDefinitionFormat? = null,
    @SerialName("ldp_vc")
    val linkedDataProofVerifiableCredential: LinkedDataProofPresentationDefinitionFormat? = null,
    @SerialName("ldp_vp")
    val linkedDataProofVerifiablePresentation: LinkedDataProofPresentationDefinitionFormat? = null
) {
    init {
        require(listOf(
            jsonWebToken,
            jsonWebTokenVerifiableCredential,
            jsonWebTokenVerifiablePresentation,
            linkedDataProof,
            linkedDataProofVerifiableCredential,
            linkedDataProofVerifiablePresentation
        ).any { it != null })
    }
}

@Serializable
data class JsonWebTokenPresentationDefinitionFormat(@SerialName("alg") val algorithms: List<String>) {
    init {
        require(algorithms.isNotEmpty())
    }
}

@Serializable
data class LinkedDataProofPresentationDefinitionFormat(@SerialName("proof_type") val proofTypes: List<String>) {
    init {
        require(proofTypes.isNotEmpty())
    }
}