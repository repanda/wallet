package com.repanda.wallet.application

import com.repanda.wallet.domain.*
import com.repanda.wallet.persistence.VerificationRepository
import com.repanda.wallet.protocol.MockWalletAdapter
import com.repanda.wallet.session.SessionManager
import com.repanda.wallet.verification.CredentialVerifier
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class VerificationService(
    private val repository: VerificationRepository,
    private val sessionManager: SessionManager,
    private val mockWalletAdapter: MockWalletAdapter,
    private val credentialVerifier: CredentialVerifier
) {

    @Transactional
    fun createVerification(req: VerificationRequest): VerificationResponse {
        val session = sessionManager.createSession(req.purpose ?: "", req.requestedClaims ?: emptyList())
        repository.save(session)

        val requestUrl = mockWalletAdapter.createRequest(session)
        session.requestUrl = requestUrl
        repository.save(session)

        return VerificationResponse(session.id, session.status, session.requestUrl)
    }

    @Transactional(readOnly = true)
    fun getVerification(id: UUID): VerificationResponse {
        val session = repository.findById(id).orElseThrow { NoSuchElementException("Verification not found") }
        return VerificationResponse(session.id, session.status, session.requestUrl)
    }

    @Transactional
    fun handlePresentation(id: UUID, presentation: PresentationRequest): VerificationResponse {
        val session = repository.findById(id).orElseThrow { NoSuchElementException("Verification not found") }
        if (session.status != VerificationStatus.PENDING) {
            return VerificationResponse(session.id, session.status, session.requestUrl)
        }

        // For the mock flow we use a simple verifier
        val verified = credentialVerifier.verify(presentation)
        session.status = if (verified) VerificationStatus.VERIFIED else VerificationStatus.REJECTED
        session.presentation = presentation
        repository.save(session)

        return VerificationResponse(session.id, session.status, session.requestUrl)
    }
}
