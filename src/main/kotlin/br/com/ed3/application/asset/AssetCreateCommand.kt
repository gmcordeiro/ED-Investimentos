package br.com.ed3.application.asset

import br.com.ed3.domain.portfolio.FinancialAssets
import com.squareup.moshi.Json
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class AssetCreateCommand (
	val ticket: kotlin.String,
	val enterpriseName: kotlin.String,
	val unitAssetValue: kotlin.Double,
	val numberAssets: kotlin.Int,
)

fun AssetCreateCommand.toAsset() = FinancialAssets(
	ticket = ticket,
	enterpriseName = enterpriseName,
	unitAssetValue = unitAssetValue,
	numberAssets = numberAssets
)