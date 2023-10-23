package br.com.ed3.adapters.jdbc

import br.com.ed3.domain.transaction.Transaction
import br.com.ed3.domain.transaction.TransactionRepository
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

	override fun findAll(): List<Transaction> {
		val transaction = try {
			db.query(TransactionSQLExpressions.sqlSelectAll(), rowMapperFind())
		} catch (ex: Exception){
			LOGGER.error { "Houve um erro ao consultar as transações: ${ex.message}" }
			throw ex
		}
		return transaction
	}

	override fun findByID(transactionID: UUID): Transaction? {
		val params = MapSqlParameterSource("id", transactionID.toString())
		val transaction = try{
			db.query(TransactionSQLExpressions.sqlSelectByID(), params, rowMapperFind()).firstOrNull()
		} catch (ex: Exception){
			LOGGER.error { "Houve um erro ao consultar as transações: ${ex.message}" }
			throw ex
		}
		return transaction
	}

	override fun insert(transaction: Transaction): Boolean {
		try {
			val params = mapParameterSource(transaction)
			return db.update(TransactionSQLExpressions.sqlInsert(), params) > 0
		}catch (ex: Exception){
			LOGGER.error { "Erro ao inseri a transação: ${ex.message}" }
			throw ex
		}
	}

	override fun update(transaction: Transaction): Boolean {
		try {
			val params = mapParameterSource(transaction)
			return db.update(TransactionSQLExpressions.sqlUpdate(), params) > 0
		}catch (ex: Exception){
			LOGGER.error { "Erro ao inseri a transação: ${ex.message}" }
			throw ex
		}
	}

	override fun delete(transactionID: UUID): Boolean {
		return try {
			val params = MapSqlParameterSource("id", transactionID.toString())
			val linhasDeletadas = db.update(TransactionSQLExpressions.sqlDeleteByID(), params)
			return linhasDeletadas == 1
		}catch (ex: Exception){
			LOGGER.error { "Houve um erro ao deletar a transação" }
			throw ex
		}
	}

	private fun rowMapperFind() = org.springframework.jdbc.core.RowMapper<Transaction> { rs, _ ->
		val transactionID = UUID.fromString(rs.getString("id"))
		Transaction(
			id = transactionID,
			transactionType = rs.getString("transactionType"),
			assetID = UUID.fromString(rs.getString("assetID")),
			numberAssets = rs.getInt("numberAssets"),
			transactionValue = rs.getDouble("transactionValue"),
		)
	}

	private fun mapParameterSource(transaction: Transaction): MapSqlParameterSource{
		val params = MapSqlParameterSource()
		params.addValue("id", transaction.id.toString())
		params.addValue("transactionType", transaction.transactionType)
		params.addValue("assetID", transaction.assetID.toString())
		params.addValue("numberAssets", transaction.numberAssets)
		params.addValue("transactionValue", transaction.transactionValue)
		return params;
	}
}