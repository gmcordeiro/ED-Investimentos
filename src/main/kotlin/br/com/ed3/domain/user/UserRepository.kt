package br.com.ed3.domain.user

interface UserRepository {
	fun findAll(): List<User>
	fun findByLogin(userLogin: String): User?
	fun insert(user: User): Boolean
	fun update(user: User): Boolean
	fun delete(userLogin: String): Boolean

}