package com.repanda.wallet.protocol

import com.repanda.wallet.domain.VerificationSession
import org.springframework.stereotype.Component

@Component
class MockWalletAdapter : WalletProtocolAdapter {
    override fun createRequest(session: VerificationSession): String {
        // In a real implementation this would build a protocol-specific request
        return "http://localhost:8080/mock-wallet/requests/${session.id}"
    }
}
