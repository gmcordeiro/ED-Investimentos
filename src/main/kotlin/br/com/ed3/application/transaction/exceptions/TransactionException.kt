package br.com.ed3.application.transaction.exceptions

import java.util.*

sealed class TransactionException(message: String): Exception(message){
	abstract val transactionID: UUID?
}

data class TransactionNotFoundException(
	override val transactionID: UUID?
): TransactionException("Transação $transactionID não encontrada!")

data class TransactionNotInsertException(
	override val transactionID: UUID?
): TransactionException("Não foi possivel inserir a transação de ID: $transactionID!")
