package com.repanda.wallet.domain

import java.time.Instant
import java.util.*
import jakarta.persistence.*

@Entity
@Table(name = "verification_sessions")
data class VerificationSession(
    @Id
    var id: UUID = UUID.randomUUID(),

    var purpose: String = "",

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "verification_requested_claims", joinColumns = [JoinColumn(name = "session_id")])
    @Column(name = "claim")
    var requestedClaims: MutableList<String> = mutableListOf(),

    @Enumerated(EnumType.STRING)
    var status: VerificationStatus = VerificationStatus.PENDING,

    var requestUrl: String? = null,

    var createdAt: Instant = Instant.now(),

    var updatedAt: Instant = Instant.now(),

    @Transient
    var presentation: PresentationRequest? = null
)
