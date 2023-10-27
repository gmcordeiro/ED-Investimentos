package br.com.ed3.application.user

import br.com.ed3.domain.user.User
import java.util.*

data class UserCommand(
	val user: String,
	val name: String,
	val email: String,
	val password: String
)

fun UserCommand.toUser() = User(
	user = user,
	name = name,
	email = email,
	password = password
)

fun UserCommand.toUser(userID: UUID) = User(
	id = userID,
	user = user,
	name = name,
	email = email,
	password = password
)