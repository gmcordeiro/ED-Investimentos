package br.com.ed3.application.transaction.excepions

import java.util.*

sealed class TransactionExcepion(message: String): Exception(message){
	abstract val transactionID: UUID?
}

data class TransactionIsNotFoundException(
	override val transactionID: UUID?
): TransactionExcepion("Transação $transactionID não encontrada!")

data class TransactionNotInsertException(
	override val transactionID: UUID?
): TransactionExcepion("Não foi possivel inserir a transação de ID: $transactionID!")
