package br.com.ed3.adapters.http

import br.com.ed3.application.asset.AssetCreateCommand
import br.com.ed3.application.transaction.TransactionCreateCommand
import br.com.ed3.application.transaction.TransactionService
import br.com.ed3.domain.portfolio.FinancialAssets
import br.com.ed3.domain.portfolio.Transaction
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionHandler(
	private val transactionService: TransactionService
) {
	fun findByID(transactionID: String): ResponseEntity<Transaction> {
		val transaction = transactionService.findByID(UUID.fromString(transactionID))
		return ResponseEntity.ok(transaction)
	}
	fun insert(transaction: TransactionCreateCommand): ResponseEntity<Transaction> {
		val objTransaction = transactionService.insert(transaction)
		return ResponseEntity.status(HttpStatus.CREATED).body(objTransaction)
	}
}