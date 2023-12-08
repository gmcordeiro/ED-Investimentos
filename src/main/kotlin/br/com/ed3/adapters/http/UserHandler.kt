package br.com.ed3.adapters.http

import br.com.ed3.application.user.UserCommand
import br.com.ed3.application.user.UserService
import org.springframework.http.ResponseEntity
import br.com.ed3.domain.user.User
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class UserHandler(
	private val userService: UserService
) {
	fun findAll(): ResponseEntity<List<User>> {
		val user = userService.findAll()
		return ResponseEntity.ok(user)
	}

	fun findByLogin(userLogin: String): ResponseEntity<User>{
		val user = userService.findByLogin(userLogin)
		return ResponseEntity.ok(user)
	}

	fun insert(userCommand: UserCommand): ResponseEntity<User>{
		val user = userService.insert(userCommand)
		return ResponseEntity.status(HttpStatus.CREATED).body(user)
	}

	fun update(userCommand: UserCommand, userName: String): ResponseEntity<User>{
		val user = userService.update(userCommand, userName)
		return ResponseEntity.status(HttpStatus.OK).body(user)
	}

	fun delete(userLogin: String): ResponseEntity<String>{
		userService.delete(userLogin)
		return ResponseEntity.noContent().build()
	}

}