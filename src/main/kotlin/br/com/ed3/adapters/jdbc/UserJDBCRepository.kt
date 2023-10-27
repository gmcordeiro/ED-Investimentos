package br.com.ed3.adapters.jdbc

import br.com.ed3.domain.user.User
import br.com.ed3.domain.user.UserRepository
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations
import org.springframework.stereotype.Repository

@Repository
class UserJDBCRepository (
    private val db: NamedParameterJdbcOperations
): UserRepository {
    override fun findAll(): List<User> {
        TODO("Not yet implemented")
    }

    override fun findByName(userName: String): User? {
        TODO("Not yet implemented")
    }

    override fun insert(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(userName: String): Boolean {
        TODO("Not yet implemented")
    }
}