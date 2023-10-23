package br.com.ed3.adapters.http.error

import br.com.ed3.application.asset.exceptions.AssetIsNotFoundException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.UUID

private val LOGGER = KotlinLogging.logger { }
@ControllerAdvice
class ErrorHandler {
	@ExceptionHandler(Exception::class)
	fun handlerException(ex: Exception): ResponseEntity<ErrorResponse>{
		return ex.toServerResponse()
	}
}

private fun Throwable.toResponse(): Pair<HttpStatus, ErrorResponse> =
	when (this) {
		is AssetIsNotFoundException -> toResponse(
			this.assetID,
			HttpStatus.NOT_FOUND,
		)
		else -> {
			toResponse(
				statusCode = HttpStatus.BAD_REQUEST
			)
		}
	}

private fun Throwable.toResponse(
	id: UUID? = null,
	statusCode: HttpStatus,
	message: String = this.message ?: "",
): Pair<HttpStatus, ErrorResponse> {
	val response = ErrorResponse(
		id,
		message
	)

	val fullMessage = "[${statusCode.value()}] [${this.javaClass.simpleName}] $message"
	if (statusCode.is5xxServerError){
		LOGGER.error(this){fullMessage}
	} else {
		LOGGER.warn {fullMessage}
	}

	return statusCode to response
}


fun Throwable.toServerResponse(): ResponseEntity<ErrorResponse>{
	val (statusCode, response) = toResponse()
	return ResponseEntity.status(statusCode).body(response)
}