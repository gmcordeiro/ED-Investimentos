package br.com.ed3.adapters.jdbc

object UserSQLExpressions {

    fun sqlSelectAll() = """
		SELECT 
			id,
			login,
			name,
			email,
			password
		FROM
			tbl_user
    """.trimIndent()

    fun sqlSelectByLogin() = """
		SELECT
			id,
			login,
			name,
			email,
			password
	    FROM
			tbl_user
	    where
			login = :login
    """.trimIndent()

    fun sqlInsert() = """
        INSERT INTO tbl_user
			(id, login, name, email, password)
		VALUE 
			(:id, :login, :name, :email, :password)
    """.trimIndent()

    fun sqlDeleteByLogin() = """
		DELETE FROM tbl_user WHERE login = :login
	""".trimIndent()

    fun sqlUpdate() = """
		UPDATE
			tbl_user
		SET
			id = :id,
			login = :login,
			name = :name,
			email = :email,
			password = :password
		WHERE
			login = :login
	""".trimIndent()

}