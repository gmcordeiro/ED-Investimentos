package br.com.ed3.domain.assets

import br.com.ed3.domain.assets.FinancialAssets
import java.util.*

interface AssetsRepository {

	fun findAll(): List<FinancialAssets>

	fun findByID(assetID: UUID): FinancialAssets?


	fun insertAsset(asset: FinancialAssets): Boolean

}