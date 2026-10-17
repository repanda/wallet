package com.repanda.wallet.protocol

import com.repanda.wallet.domain.VerificationSession

interface WalletProtocolAdapter {
    fun createRequest(session: VerificationSession): String
}
