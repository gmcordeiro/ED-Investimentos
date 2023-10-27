package br.com.ed3.adapters.http

import br.com.ed3.application.transaction.TransactionCommand
import br.com.ed3.application.transaction.TransactionService
import br.com.ed3.domain.transaction.Transaction
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
	fun insert(transactionCommand: TransactionCommand): ResponseEntity<Transaction> {
		val transaction = transactionService.insert(transactionCommand)
		return ResponseEntity.status(HttpStatus.CREATED).body(transaction)
	}

	fun update(transaction: TransactionCommand, transactionID: String): ResponseEntity<Transaction> {
		val objTransaction = transactionService.update(transaction, UUID.fromString(transactionID))
		return ResponseEntity.status(HttpStatus.OK).body(objTransaction)
	}

	fun delete(transactionID: String): ResponseEntity<String>{
		transactionService.delete(UUID.fromString(transactionID))
		return ResponseEntity.noContent().build()
	}
}