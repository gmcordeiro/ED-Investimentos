package br.com.ed3.domain.portfolio

import java.util.*

interface AssetsRepository {

	fun findAll(): List<FinancialAssets>

	fun findByID(assetID: UUID): FinancialAssets?


	fun insertAsset(asset: FinancialAssets): Boolean

}