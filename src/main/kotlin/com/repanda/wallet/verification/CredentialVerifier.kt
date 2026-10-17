package com.repanda.wallet.verification

import com.repanda.wallet.domain.PresentationRequest
import org.springframework.stereotype.Component

@Component
class CredentialVerifier {
    fun verify(presentation: PresentationRequest): Boolean {
        // Mock verification: succeed when a credential map exists and a holderId is present
        if (presentation.holderId.isNullOrBlank()) return false
        if (presentation.credential == null) return false
        // In phase 2 this should perform cryptographic checks
        return true
    }
}
