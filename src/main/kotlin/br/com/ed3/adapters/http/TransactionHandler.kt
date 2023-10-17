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
import kotlin.collections.List

@Component
class TransactionHandler(
	private val transactionService: TransactionService
) {
	fun findAll(): ResponseEntity<List<Transaction>>{
		val transactions = transactionService.findAll()
		return ResponseEntity.ok(transactions)
	}

	fun findByID(transactionID: String): ResponseEntity<Transaction> {
		val transaction = transactionService.findByID(UUID.fromString(transactionID))
		return ResponseEntity.ok(transaction)
	}
	fun insert(transaction: TransactionCreateCommand): ResponseEntity<Transaction> {
		val objTransaction = transactionService.insert(transaction)
		return ResponseEntity.status(HttpStatus.CREATED).body(objTransaction)
	}

	fun update(transaction: TransactionCreateCommand, transactionID: String): ResponseEntity<Transaction> {
		val objTransaction = transactionService.update(transaction, UUID.fromString(transactionID))
		return ResponseEntity.status(HttpStatus.CREATED).body(objTransaction)
	}

	fun delete(transactionID: String): ResponseEntity<String>{
		transactionService.delete(UUID.fromString(transactionID))
		return ResponseEntity.noContent().build()
	}
}