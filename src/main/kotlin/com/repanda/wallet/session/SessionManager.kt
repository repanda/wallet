package com.repanda.wallet.session

import com.repanda.wallet.domain.VerificationSession
import org.springframework.stereotype.Component
import java.util.*

@Component
class SessionManager {
    fun createSession(purpose: String, requestedClaims: List<String>): VerificationSession {
        val session = VerificationSession(
            purpose = purpose,
            requestedClaims = requestedClaims.toMutableList()
        )
        session.requestUrl = "http://localhost:8080/mock-wallet/requests/${session.id}"
        return session
    }
}
