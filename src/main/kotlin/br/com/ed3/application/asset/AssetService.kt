package br.com.ed3.application.asset

import br.com.ed3.application.asset.exceptions.AssetNotFoundException
import br.com.ed3.domain.assets.AssetsRepository
import br.com.ed3.domain.assets.FinancialAssets
import org.springframework.stereotype.Service
import java.util.UUID
import br.com.ed3.application.asset.exceptions.AssetNotInsertException

@Service
class AssetService(
	private val assetsRepository: AssetsRepository
) {
	fun findAll(): List<FinancialAssets>{
		return assetsRepository.findAll()
	}

	fun findByID(assetID: UUID): FinancialAssets {
		return assetsRepository.findByID(assetID) ?: throw AssetNotFoundException(assetID)
	}

	fun insert (asset: AssetCommand): FinancialAssets {
		val assetDomain = asset.toAsset()
		assetsRepository.insertAsset(assetDomain) ?: throw AssetNotInsertException(assetDomain.id)

		return findByID(assetDomain.id)
	}
}