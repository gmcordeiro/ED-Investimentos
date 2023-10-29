package br.com.ed3.adapters.jdbc

import br.com.ed3.domain.user.User
import br.com.ed3.domain.user.UserRepository
import mu.KotlinLogging
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserJDBCRepository (
    private val db: NamedParameterJdbcOperations
): UserRepository {

    private companion object {
        val LOGGER = KotlinLogging.logger {}
    }

    override fun findAll(): List<User> {
        val user = try {
        	db.query(UserSQLExpressions.sqlSelectAll(), rowMapperFind())
        } catch (ex: Exception){
            LOGGER.error { "Houve um erro ao buscar os usuários: ${ex.message}" }
            throw ex
        }
        return user
    }

    override fun findByLogin(userLogin: String): User? {
        val params = MapSqlParameterSource("login", userLogin)
        val user = try{
            db.query(UserSQLExpressions.sqlSelectByLogin(), params, rowMapperFind()).firstOrNull()
        } catch (ex: Exception){
            LOGGER.error { "Houve um erro buscar os usuários: ${ex.message}" }
            throw ex
        }
        return user
    }

    override fun insert(user: User): Boolean {
        try {
            val params = mapParameterSource(user)
            return db.update(UserSQLExpressions.sqlInsert(), params) > 0
        }catch (ex: Exception){
            LOGGER.error { "Erro ao inseri a transação: ${ex.message}" }
            throw ex
        }
    }

    override fun update(user: User): Boolean {
        try {
            val params = mapParameterSource(user)
            return db.update(UserSQLExpressions.sqlUpdate(), params) > 0
        }catch (ex: Exception){
            LOGGER.error { "Erro ao inseri a transação: ${ex.message}" }
            throw ex
        }
    }

    override fun delete(userLogin: String): Boolean {
        return try {
            val params = MapSqlParameterSource("login", userLogin)
            val linhasDeletadas = db.update(UserSQLExpressions.sqlDeleteByLogin(), params)
            return linhasDeletadas == 1
        }catch (ex: Exception){
            LOGGER.error { "Houve um erro ao deletar a transação" }
            throw ex
        }
    }

    private fun rowMapperFind() = org.springframework.jdbc.core.RowMapper<User> { rs, _ ->
        val userID = UUID.fromString(rs.getString("id"))
        User(
            id = userID,
            login = rs.getString("login"),
            name = rs.getString("name"),
            email = rs.getString("email"),
            password = rs.getString("password"),
        )
    }

    private fun mapParameterSource(user: User): MapSqlParameterSource {
        val params = MapSqlParameterSource()
        params.addValue("id", user.id.toString())
        params.addValue("login", user.login)
        params.addValue("name", user.name)
        params.addValue("email", user.email)
        params.addValue("password", user.password)
        return params;
    }

}