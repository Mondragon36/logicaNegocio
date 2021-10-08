package co.edu.eam.ingesoft.onlinestore.repositories

import co.edu.eam.ingesoft.onlinestore.models.City
import co.edu.eam.ingesoft.onlinestore.models.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        userRepository.create(User("1", "cacique", "Cardona","Juan", city))

        val user = entityManager.find(User::class.java, "1")
        Assertions.assertNotNull(user)
        Assertions.assertEquals("Cardona", user.name)
        Assertions.assertEquals("Juan", user.lastName)
        Assertions.assertEquals("cacique", user.address)
        Assertions.assertEquals("1", user.id)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        userRepository.create(User("1", "cacique", "Cardona","Juan", city))

        //ejecutando...
        val user = entityManager.find(User::class.java, "1")
        user.name = "Juan"
        user.lastName = "Perez"
        user.address = "Recreacional"

        userRepository.update(user)

        //assersiones
        val userAssert = entityManager.find(User::class.java, "1")
        Assertions.assertEquals("Juan", userAssert.name)
        Assertions.assertEquals("Perez", userAssert.lastName)
        Assertions.assertEquals("Recreacional ", userAssert.address)
    }

    @Test
    fun findTest() {
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        userRepository.create(User("1", "cacique", "Cardona","Juan", city))

        val user = userRepository.find("1")

        Assertions.assertNotNull(user)
        Assertions.assertEquals("Juan", user?.name)
        Assertions.assertEquals("Cardona", user?.lastName)
        Assertions.assertEquals("cacique", user?.address)
    }

    @Test
    fun testDelete() {
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        userRepository.create(User("1", "cacique", "Cardona","Juan", city))

        userRepository.delete("1")

        val user = entityManager.find(User::class.java, "1")
        Assertions.assertNull(user)
    }

}