package br.com.ed3.adapters.http.error

import java.util.UUID

class ErrorResponse(
	val id: UUID? = null,
	val message: String,
)