package br.com.ed3.adapters.http

import br.com.ed3.application.transaction.TransactionCreateCommand
import br.com.ed3.domain.transaction.Transaction
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

private const val UUID_REGEX = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}"
@RestController
class TransactionController(
	private val transactionHandler: TransactionHandler
) {
	@GetMapping("/transactions")
	fun findAll(): ResponseEntity<List<Transaction>>{
		return transactionHandler.findAll()
	}

	@GetMapping("/transactions/{transactionID:$UUID_REGEX}")
	fun findByID(@PathVariable transactionID: String): ResponseEntity<Transaction>{
		return transactionHandler.findByID(transactionID)
	}

	@PostMapping("/transactions")
	fun insert(@RequestBody transaction: TransactionCreateCommand): ResponseEntity<Transaction>{
		return transactionHandler.insert(transaction)
	}

	@PutMapping("/transactions/{transactionID:$UUID_REGEX}")
	fun update(@RequestBody transaction: TransactionCreateCommand, @PathVariable transactionID: String): ResponseEntity<Transaction>{
		return transactionHandler.update(transaction, transactionID)
	}

	@DeleteMapping("/transactions/{transactionID:$UUID_REGEX}")
	fun delete(@PathVariable transactionID: String): ResponseEntity<String>{
		return transactionHandler.delete(transactionID)
	}
}