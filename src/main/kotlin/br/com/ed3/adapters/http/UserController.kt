package br.com.ed3.adapters.http

import br.com.ed3.application.user.UserCommand
import br.com.ed3.domain.user.User
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

private const val UUID_REGEX = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}"

@RestController
class UserController(
	private val userHandler: UserHandler
) {
	@GetMapping("/users")
	fun findAll(): ResponseEntity<List<User>>{
		return userHandler.findAll()
	}

	@GetMapping("/users/{userLogin}")
	fun findByLogin(@PathVariable userLogin: String): ResponseEntity<User>{
		return userHandler.findByLogin(userLogin)
	}

	@PostMapping("/users")
	fun insert(@RequestBody userCommand: UserCommand): ResponseEntity<User>{
		return userHandler.insert(userCommand)
	}

	@PutMapping("/users/{userLogin}")
	fun update(@RequestBody userCommand: UserCommand, @PathVariable userLogin: String): ResponseEntity<User>{
		return userHandler.update(userCommand, userLogin)
	}

	@DeleteMapping("/users/{userLogin}")
	fun delete(@PathVariable userLogin: String): ResponseEntity<String>{
		return userHandler.delete(userLogin)
	}

}