package br.com.ed3.application.transaction

import br.com.ed3.application.transaction.excepions.TransactionIsNotFoundException
import br.com.ed3.application.transaction.excepions.TransactionNotInsertException
import br.com.ed3.domain.transaction.Transaction
import br.com.ed3.domain.transaction.TransactionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService(
	private val transactionRepository: TransactionRepository
) {
	fun findAll(): List<Transaction>{
		return transactionRepository.findAll()
	}

	fun findByID(transactionID: UUID): Transaction {
		return transactionRepository.findByID(transactionID) ?: throw TransactionIsNotFoundException(transactionID)
	}

	fun insert(transaction: TransactionCreateCommand): Transaction {
		val transactionDomain = transaction.toTransaction()
		transactionRepository.insert(transactionDomain) ?: TransactionNotInsertException(transactionDomain.id)

		return findByID(transactionDomain.id)
	}

	fun update (transaction: TransactionCreateCommand, transactionID: UUID): Transaction {
		val transactionDomain = transaction.toTransaction(transactionID)
		transactionRepository.update(transactionDomain) ?: TransactionNotInsertException(transactionDomain.id)

		return findByID(transactionDomain.id)
	}

	fun delete(transactionID: UUID){
		transactionRepository.findByID(transactionID) ?: throw TransactionIsNotFoundException(transactionID)
		transactionRepository.delete(transactionID)
	}
}