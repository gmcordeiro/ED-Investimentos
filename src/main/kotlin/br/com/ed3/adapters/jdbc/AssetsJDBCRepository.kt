package br.com.ed3.adapters.jdbc

import br.com.ed3.domain.portfolio.AssetsRepository
import br.com.ed3.domain.portfolio.FinancialAssets
import mu.KotlinLogging
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.UUID
import javax.swing.tree.RowMapper

@Repository
class AssetsJDBCRepository(
    private val db: NamedParameterJdbcOperations
) : AssetsRepository {

	private  companion object {
		val LOGGER = KotlinLogging.logger {}
	}

    override fun findAll(): List<FinancialAssets> {
        val assetList = try {
	        db.query(AssetsSQLExpressions.sqlSelectAll(), rowMapper())
        } catch (ex: Exception){
			LOGGER.error { "Houve um erro ao consultar ativos financeiros: ${ex.message}" }
			throw ex
		}
        return assetList
    }

    override fun findByID(assetID: UUID): FinancialAssets? {
		val params = MapSqlParameterSource("id", assetID.toString())
		val asset = try{
			db.query(AssetsSQLExpressions.sqlSelectByID(), params, rowMapper()).firstOrNull()
		} catch (ex: Exception){
			LOGGER.error { "Houve um erro ao consultar os ativos financeiros: ${ex.message}" }
			throw ex
		}
		return asset
    }

    override fun insertAsset(asset: FinancialAssets): Boolean {
        try {
			val params = MapSqlParameterSource()
	        params.addValue("id", asset.id.toString())
	        params.addValue("ticket", asset.ticket)
	        params.addValue("enterpriseName", asset.enterpriseName)
	        params.addValue("unitAssetValue", asset.unitAssetValue)
	        params.addValue("numberAssets", asset.numberAssets)

	        return db.update(AssetsSQLExpressions.sqlInsert(), params) > 0
        }catch (ex: Exception){
	        LOGGER.error { "Erro ao inserir o ativo financeiro: ${ex.message}" }
	        throw ex
		}
    }

    private fun rowMapper() = org.springframework.jdbc.core.RowMapper<FinancialAssets> { rs, _ ->
        val assetID = UUID.fromString(rs.getString("id"))
        FinancialAssets(
            id = assetID,
            ticket = rs.getString("ticket"),
            enterpriseName = rs.getString("enterpriseName"),
            unitAssetValue = rs.getDouble("unitAssetValue"),
            numberAssets = rs.getInt("numberAssets")
        )
    }
}

