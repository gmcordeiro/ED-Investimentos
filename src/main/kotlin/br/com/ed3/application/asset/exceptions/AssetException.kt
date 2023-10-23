package br.com.ed3.application.asset.exceptions

import java.util.UUID

sealed class AssetException(message: String): Exception(message) {
	abstract val assetID: UUID?
}

data class AssetIsNotFoundException(
	override val assetID: UUID?
): AssetException("Ativo $assetID não encontrado!")

data class AssetIsNotInsertException(
	override val assetID: UUID?
): AssetException("Não foi possível inserir o Ativo de ID $assetID!")
