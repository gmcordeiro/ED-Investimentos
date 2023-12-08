package br.com.ed3.application.transaction

import br.com.ed3.domain.transaction.Transaction
import java.util.UUID

data class TransactionCommand(
	val transactionType: String,
	val assetID: java.util.UUID,
	val numberAssets: Int,
	val transactionValue: Double,
)

fun TransactionCommand.toTransaction() = Transaction(
	transactionType = transactionType,
	assetID = assetID,
	numberAssets = numberAssets,
	transactionValue = transactionValue
)

fun TransactionCommand.toTransaction(transactionID: UUID) = Transaction(
	id = transactionID,
	transactionType = transactionType,
	assetID = assetID,
	numberAssets = numberAssets,
	transactionValue = transactionValue
)