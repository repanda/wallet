package com.repanda.wallet.persistence

import com.repanda.wallet.domain.VerificationSession
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface VerificationRepository : JpaRepository<VerificationSession, UUID>
