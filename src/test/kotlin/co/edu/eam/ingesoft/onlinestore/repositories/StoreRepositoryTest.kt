package co.edu.eam.ingesoft.onlinestore.repositories

import co.edu.eam.ingesoft.onlinestore.models.City
import co.edu.eam.ingesoft.onlinestore.models.Store
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class StoreRepositoryTest {

    @Autowired
    lateinit var storeRepository: StoreRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        storeRepository.create(Store("1", "cacique", "caciqueStore", city))

        val store = entityManager.find(Store::class.java, "1")
        Assertions.assertNotNull(store)
        Assertions.assertEquals("caciqueStore", store.name)
        Assertions.assertEquals("cacique", store.address)
        Assertions.assertEquals("1", store.id)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        entityManager.persist(Store("1", "cacique", "caciqueStore",city))

        //ejecutando...
        val store = entityManager.find(Store::class.java, "1")
        store.name = "caciqueFruits"
        store.address = "caciqueStadium"

        storeRepository.update(store)

        //assersiones
        val storeAssert = entityManager.find(Store::class.java, "1")
        Assertions.assertEquals("caciqueFruits", storeAssert.name)
        Assertions.assertEquals("caciqueStadium", storeAssert.address)
    }

    @Test
    fun findTest() {
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        entityManager.persist(Store("1", "cacique", "caciqueStore",city))

        val user = storeRepository.find("1")

        Assertions.assertNotNull(user)
        Assertions.assertEquals("caciqueStore", user?.name)
        Assertions.assertEquals("cacique", user?.address)
    }

    @Test
    fun testDelete() {
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        entityManager.persist(Store("1", "cacique", "caciqueStore", city))
        storeRepository.delete("1")

        val category = entityManager.find(Store::class.java, "1")
        Assertions.assertNull(category)
    }

}