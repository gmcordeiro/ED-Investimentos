package br.com.ed3.application.user.exceptions


sealed class UserException(message: String): Exception(message){
    abstract val userName: String?
}

data class UserNotFoundException(
    override val userName: String?
): UserException("Usuário $userName não encontrado!")

data class UserNotInsertException(
    override val userName: String?
): UserException("Não foi possivel inserir o usuário: $userName!")
