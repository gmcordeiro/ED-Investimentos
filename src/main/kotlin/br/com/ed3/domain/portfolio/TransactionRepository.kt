package br.com.ed3.domain.portfolio

import java.util.*

interface TransactionRepository {

	fun findAll(): List<Transaction>
	fun findByID(transactionID: UUID): Transaction?
	fun insert(transaction: Transaction): Boolean
	fun delete(transactionID: UUID): Boolean

}