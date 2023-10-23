package br.com.ed3.adapters.jdbc

object TransactionSQLExpressions {
	fun sqlSelectAll() = """
		SELECT
			id,
			transactionType,
			assetID,
			numberAssets,
			transactionValue
		FROM
			tbl_transaction        
    """.trimIndent()

	fun sqlSelectByID() = """
		SELECT
			id,
			transactionType,
			assetID,
			numberAssets,
			transactionValue
		FROM
			tbl_transaction
		WHERE
			id = :id
    """.trimIndent()

	fun sqlSelectByAssetID() = """
		SELECT
			id,
			transactionType,
			assetID,
			numberAssets,
			transactionValue
		FROM
			tbl_transaction
		WHERE
			assetID = :assetID
    """.trimIndent()

	fun sqlInsert() = """
		INSERT INTO tbl_transaction 
			(id, transactionType, assetID, numberAssets, transactionValue)
		VALUES
			(:id, :transactionType, :assetID, :numberAssets, :transactionValue)
	""".trimIndent()
}