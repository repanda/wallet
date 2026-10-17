package com.repanda.wallet.domain

import java.util.*

data class VerificationRequest(
    val purpose: String?,
    val requestedClaims: List<String>?
)


data class VerificationResponse(
    val verificationId: UUID,
    val status: VerificationStatus,
    val requestUrl: String?
)


data class PresentationRequest(
    val holderId: String?,
    val credential: Map<String, Any>?,
    val proof: Map<String, Any>?
)
