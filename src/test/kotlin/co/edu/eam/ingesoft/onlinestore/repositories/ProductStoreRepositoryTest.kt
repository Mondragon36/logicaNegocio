package co.edu.eam.ingesoft.onlinestore.repositories

import co.edu.eam.ingesoft.onlinestore.models.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class ProductStoreRepositoryTest {

    @Autowired
    lateinit var productStoreRepository: ProductStoreRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        val category = Category(1L,"Frutas")
        entityManager.persist(category)
        val product = Product(1L, "Acido","Limon",category)
        entityManager.persist(product)
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        val store = Store("1","cacique","caciqueStore",city)
        productStoreRepository.create(ProductStore(1L, 15.000,10.0,product,store))

        val productStore = entityManager.find(ProductStore::class.java,1L)
        Assertions.assertNotNull(productStore)
        Assertions.assertEquals(15.000, productStore.price)
        Assertions.assertEquals(1L, productStore.id)
        Assertions.assertEquals(10.0, productStore.stock)
    }

    @Test
    fun testUpdate() {
        val category = Category(1L,"Frutas")
        entityManager.persist(category)
        val product = Product(1L, "Acido","Limon",category)
        entityManager.persist(product)
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        val store = Store("1","cacique","caciqueStore",city)
        productStoreRepository.create(ProductStore(1L, 15.000,10.0,product,store))

        //ejecutando...
        val productStore = entityManager.find(ProductStore::class.java, 1L)
        productStore.price = 20.000
        productStore.stock = 50.0

        productStoreRepository.update(productStore)

        //assersiones
        val productStoreAssert = entityManager.find(ProductStore::class.java, 1L)
        Assertions.assertEquals( 20.000, productStoreAssert.price)
        Assertions.assertEquals(50.0, productStoreAssert.stock)
    }

    @Test
    fun findTest() {
        val category = Category(1L,"Frutas")
        entityManager.persist(category)
        val product = Product(1L, "Acido","Limon",category)
        entityManager.persist(product)
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        val store = Store("1","cacique","caciqueStore",city)
        productStoreRepository.create(ProductStore(1L, 15.000,10.0,product,store))


        val productStore = productStoreRepository.find(1L)

        Assertions.assertNotNull(productStore)
        Assertions.assertEquals(15.000, productStore?.price)
        Assertions.assertEquals(10.0, productStore?.stock)
    }

    @Test
    fun testDelete() {
        val category = Category(1L,"Frutas")
        entityManager.persist(category)
        val product = Product(1L, "Acido","Limon",category)
        entityManager.persist(product)
        val city = City(1L, "Tunja")
        entityManager.persist(city)
        val store = Store("1","cacique","caciqueStore",city)
        productStoreRepository.create(ProductStore(1L, 15.000,10.0,product,store))
        productStoreRepository.delete(1L)

        val productStore = entityManager.find(ProductStore::class.java, 1L)
        Assertions.assertNull(productStore)
    }

}