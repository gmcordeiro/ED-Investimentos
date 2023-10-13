package br.com.ed3.adapters.jdbc

import br.com.ed3.domain.portfolio.Transaction
import br.com.ed3.domain.portfolio.TransactionRepository
import mu.KotlinLogging
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class TransactionJDBCRepository(
	private val db: NamedParameterJdbcOperations
): TransactionRepository {

	private  companion object {
		val LOGGER = KotlinLogging.logger {}
	}
	/*
	override fun findAll(): List<Transaction> {
		TODO("Not yet implemented")
	}
	*/


	override fun findByID(transactionID: UUID): Transaction? {
		val params = MapSqlParameterSource("id", transactionID.toString())
		val transaction = try{
			db.query(TransactionSQLExpressions.sqlSelectByID(), params, rowMapper()).firstOrNull()
		} catch (ex: Exception){
			LOGGER.error { "Houve um erro ao consultar os produtos: ${ex.message}" }
			throw ex
		}
		return transaction
	}

	override fun insert(transaction: Transaction): Boolean {
		try {
			val params = MapSqlParameterSource()
			params.addValue("id", transaction.id.toString())
			params.addValue("transactionType", transaction.transactionType)
			params.addValue("assetID", transaction.assetID.toString())
			params.addValue("numberAssets", transaction.numberAssets)
			params.addValue("transactionValue", transaction.transactionValue)

			return db.update(TransactionSQLExpressions.sqlInsert(), params) > 0
		}catch (ex: Exception){
			LOGGER.error { "Erro ao inseri o produto: ${ex.message}" }
			throw ex
		}
	}

	private fun rowMapper() = org.springframework.jdbc.core.RowMapper<Transaction> { rs, _ ->
		val transactionID = UUID.fromString(rs.getString("id"))
		Transaction(
			id = transactionID,
			transactionType = rs.getString("transactionType"),
			assetID = UUID.fromString(rs.getString("assetID")),
			numberAssets = rs.getInt("numberAssets"),
			transactionValue = rs.getDouble("transactionValue"),
		)
	}
}