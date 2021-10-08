package co.edu.eam.ingesoft.onlinestore.repositories

import co.edu.eam.ingesoft.onlinestore.models.City
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class CityRepositoryTest {

    @Autowired
    lateinit var cityRepository: CityRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        cityRepository.create(City(1L, "Tunja"))

        val city = entityManager.find(City::class.java,1L)
        Assertions.assertNotNull(city)
        Assertions.assertEquals("Tunja", city.name)
        Assertions.assertEquals(1L, city.id)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        entityManager.persist(City(1L, "Tunja"))

        //ejecutando...
        val city = entityManager.find(City::class.java, 1L)
        city.name = "Cali"

        cityRepository.update(city)

        //assersiones
        val cityAssert = entityManager.find(City::class.java, 1L)
        Assertions.assertEquals("Cali", cityAssert.name)
    }

    @Test
    fun findTest() {
        entityManager.persist(City(1L, "Tunja"))

        val city = cityRepository.find(1L)

        Assertions.assertNotNull(city)
        Assertions.assertEquals("Tunja", city?.name)
    }

    @Test
    fun testDelete() {
        entityManager.persist(City(1L, "Tunja"))
        cityRepository.delete(1L)

        val city = entityManager.find(City::class.java, 1L)
        Assertions.assertNull(city)
    }

}