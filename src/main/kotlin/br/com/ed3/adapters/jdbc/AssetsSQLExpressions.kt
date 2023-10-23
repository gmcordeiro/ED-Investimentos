package br.com.ed3.adapters.jdbc

import java.util.UUID
import javax.security.auth.kerberos.KerberosTicket

object AssetsSQLExpressions {

    fun sqlSelectAll() = """
		SELECT
			id,
			ticket,
			enterpriseName,
			unitAssetValue,
			numberAssets
		FROM
			tbl_financial_assets        
    """.trimIndent()

    fun sqlSelectByID() = """
		SELECT
			id,
			ticket,
			enterpriseName,
			unitAssetValue,
			numberAssets
		FROM
			tbl_financial_assets
		WHERE
			id = :id
    """.trimIndent()

	fun sqlSelectByTicket() = """
		SELECT
			id,
			ticket,
			enterpriseName,
			unitAssetValue,
			numberAssets
		FROM
			tbl_financial_assets
		WHERE
			ticket = :ticket
    """.trimIndent()

	fun sqlInsert() = """
		INSERT INTO tbl_financial_assets
			(id, ticket, enterpriseName, unitAssetValue, numberAssets)
		VALUES
			(:id, :ticket, :enterpriseName, :unitAssetValue, :numberAssets)
	""".trimIndent()
}