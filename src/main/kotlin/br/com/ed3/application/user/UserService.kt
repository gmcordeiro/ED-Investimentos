package br.com.ed3.application.user

import br.com.ed3.application.user.exceptions.UserNotFoundException
import br.com.ed3.domain.user.User
import br.com.ed3.domain.user.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun findAll(): List<User>{
        return userRepository.findAll()
    }

    fun findByName(userName: String): User {
        return userRepository.findByName(userName) ?: throw UserNotFoundException(userName)
    }

    fun insert(user: UserCommand): User {
        val userDomain = user.toUser()
        userRepository.insert(userDomain)

        return findByName(userDomain.name)
    }

    fun update(user: UserCommand, userName: String): User {
        val userOld = findByName(userName)
        val userDomain = user.toUser(userOld.id)
        userRepository.update(userDomain)

        return findByName(userDomain.name)
    }

    fun delete(userName: String){
        userRepository.findByName(userName) ?: throw UserNotFoundException(userName)
        userRepository.delete(userName)
    }
}