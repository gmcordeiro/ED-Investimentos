package br.com.ed3.application.asset

import br.com.ed3.domain.assets.FinancialAssets
import kotlinx.serialization.Serializable

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