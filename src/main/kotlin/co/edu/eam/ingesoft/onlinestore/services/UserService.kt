package co.edu.eam.ingesoft.onlinestore.services

import co.edu.eam.ingesoft.onlinestore.exceptions.BusinessException
import co.edu.eam.ingesoft.onlinestore.models.User
import co.edu.eam.ingesoft.onlinestore.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var entityManager: EntityManager

    fun createUser(user: User){
        val userById = userRepository.find(user.id)

        if(userById != null){
            throw BusinessException("This person already exists")
        }
        userRepository.create(user)
    }


    fun editUser(user: User) {
        val userById = userRepository.find(user.id)

        if (userById == null) {
            throw BusinessException("This user does not exist")
        }

        userRepository.update(user)
    }
}