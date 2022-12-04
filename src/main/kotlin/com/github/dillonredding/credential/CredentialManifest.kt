package com.github.dillonredding.credential

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CredentialManifest(
    val id: String,
    @SerialName("spec_version")
    val specVersion: String = "https://identity.foundation/credential-manifest/spec/v1.0.0/",
    val issuer: CredentialIssuer,
    @SerialName("output_descriptors")
    val outputDescriptors: List<OutputDescriptor> = emptyList(),
    val name: String? = null,
    val description: String? = null,
    val format: CredentialManifestFormat? = null,
    @SerialName("presentation_definition")
    val presentationDefinition: PresentationDefinition? = null
)

typealias CredentialManifestFormat = PresentationDefinitionFormat

@Serializable
data class CredentialManifestMessage(
    @SerialName("credential_manifest")
    val credentialManifest: CredentialManifest
)
