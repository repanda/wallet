package com.repanda.wallet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WalletConnectorApplication

fun main(args: Array<String>) {
    runApplication<WalletConnectorApplication>(*args)
}
