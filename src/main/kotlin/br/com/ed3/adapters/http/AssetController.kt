package br.com.ed3.adapters.http

import br.com.ed3.application.asset.AssetCreateCommand
import br.com.ed3.domain.portfolio.FinancialAssets
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController;

private const val UUID_REGEX = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}"

@RestController
class AssetController(
    private val assetHandler: AssetHandler
) {
    @GetMapping("/asset")
    fun findAll(): ResponseEntity<List<FinancialAssets>>{
        return assetHandler.findAll()
    }

    @GetMapping("/asset/{assetID:$UUID_REGEX}")
    fun findByID(@PathVariable assetID: String): ResponseEntity<FinancialAssets>{
        return assetHandler.findByID(assetID)
    }

    @PostMapping("/asset")
    fun insert(@RequestBody asset: AssetCreateCommand): ResponseEntity<FinancialAssets>{
        return assetHandler.insert(asset)
    }
}