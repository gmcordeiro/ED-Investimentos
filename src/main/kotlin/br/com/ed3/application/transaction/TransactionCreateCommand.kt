package br.com.ed3.application.transaction

import br.com.ed3.application.asset.AssetCreateCommand
import br.com.ed3.domain.portfolio.FinancialAssets
import br.com.ed3.domain.portfolio.Transaction
import com.squareup.moshi.Json
import java.util.UUID

data class TransactionCreateCommand(
	val transactionType: String,
	val assetID: java.util.UUID,
	val numberAssets: Int,
	val transactionValue: Double,
)

fun TransactionCreateCommand.toTransaction() = Transaction(
	transactionType = transactionType,
	assetID = assetID,
	numberAssets = numberAssets,
	transactionValue = transactionValue
)

fun TransactionCreateCommand.toTransaction(transactionID: UUID) = Transaction(
	id = transactionID,
	transactionType = transactionType,
	assetID = assetID,
	numberAssets = numberAssets,
	transactionValue = transactionValue
)