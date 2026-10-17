package com.repanda.wallet.api

import com.repanda.wallet.application.VerificationService
import com.repanda.wallet.domain.VerificationRequest
import com.repanda.wallet.domain.VerificationResponse
import com.repanda.wallet.domain.PresentationRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/verifications")
class VerificationController(private val verificationService: VerificationService) {

    @PostMapping
    fun createVerification(@RequestBody req: VerificationRequest): ResponseEntity<VerificationResponse> {
        val resp = verificationService.createVerification(req)
        return ResponseEntity.ok(resp)
    }

    @GetMapping("/{id}")
    fun getVerification(@PathVariable id: UUID): ResponseEntity<VerificationResponse> {
        val resp = verificationService.getVerification(id)
        return ResponseEntity.ok(resp)
    }

    @PostMapping("/{id}/presentations")
    fun submitPresentation(@PathVariable id: UUID, @RequestBody presentation: PresentationRequest): ResponseEntity<VerificationResponse> {
        val resp = verificationService.handlePresentation(id, presentation)
        return ResponseEntity.ok(resp)
    }
}
