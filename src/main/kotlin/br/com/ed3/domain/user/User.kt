package br.com.ed3.domain.user

import com.squareup.moshi.Json
import java.util.UUID

data class User(

	@Json(name = "id")
	val id: UUID = UUID.randomUUID(),

	@Json(name = "user")
    val user: String,

	@Json(name = "name")
	val name: String,

	@Json(name = "email")
	val email: String,

	@Json(name = "password")
	val password: String

)