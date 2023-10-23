package br.com.ed3.adapters.http

import br.com.ed3.application.asset.AssetCreateCommand
import br.com.ed3.application.asset.AssetService
import br.com.ed3.domain.assets.FinancialAssets
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class AssetHandler (
	private val assetService: AssetService
) {
	fun findAll(): ResponseEntity<List<FinancialAssets>>{
		val assets = assetService.findAll()
		return ResponseEntity.ok(assets)
	}
	fun findByID(assetID: String): ResponseEntity<FinancialAssets> {
		val asset = assetService.findByID(UUID.fromString(assetID))
		return ResponseEntity.ok(asset)
	}
	fun insert(asset: AssetCreateCommand): ResponseEntity<FinancialAssets>{
		val objAsset = assetService.insert(asset)
		return ResponseEntity.status(HttpStatus.CREATED).body(objAsset)
	}
}