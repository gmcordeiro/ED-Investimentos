package br.com.ed3.domain.transaction

import java.util.*

interface TransactionRepository {

	fun findAll(): List<Transaction>
	fun findByID(transactionID: UUID): Transaction?
	fun insert(transaction: Transaction): Boolean
	fun delete(transactionID: UUID): Boolean
	fun update(transaction: Transaction): Boolean

}