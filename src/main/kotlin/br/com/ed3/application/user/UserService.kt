package br.com.ed3.application.user

import br.com.ed3.application.user.exceptions.UserNotFoundException
import br.com.ed3.domain.user.User
import br.com.ed3.domain.user.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun findAll(): List<User>{
        return userRepository.findAll()
    }

    fun findByLogin(userLogin: String): User {
        return userRepository.findByLogin(userLogin) ?: throw UserNotFoundException(userLogin)
    }

    fun insert(user: UserCommand): User {
        val userDomain = user.toUser()
        userRepository.insert(userDomain)

        return findByLogin(userDomain.login)
    }

    fun update(user: UserCommand, userName: String): User {
        val userOld = findByLogin(userName)
        val userDomain = user.toUser(userOld.id)
        userRepository.update(userDomain)

        return findByLogin(userDomain.login)
    }

    fun delete(userLogin: String){
        userRepository.findByLogin(userLogin) ?: throw UserNotFoundException(userLogin)
        userRepository.delete(userLogin)
    }
}