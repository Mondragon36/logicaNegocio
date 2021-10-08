package co.edu.eam.ingesoft.onlinestore.repositories

import co.edu.eam.ingesoft.onlinestore.models.Category
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class CategoryRepositoryTest {

    @Autowired
    lateinit var categoryRepository: CategoryRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads() {
    }

    @Test
    fun testCreate() {
        categoryRepository.create(Category(1L, "Frutas"))

        val category = entityManager.find(Category::class.java,1L)
        Assertions.assertNotNull(category)
        Assertions.assertEquals("Frutas", category.name)
        Assertions.assertEquals(1L, category.id)
    }

    @Test
    fun testUpdate() {
        //prerequisito
        entityManager.persist(Category(1L, "Frutas"))

        //ejecutando...
        val category = entityManager.find(Category::class.java, 1L)
        category.name = "Verduras"

        categoryRepository.update(category)

        //assersiones
        val categoryAssert = entityManager.find(Category::class.java, 1L)
        Assertions.assertEquals("Verduras", categoryAssert.name)
    }

    @Test
    fun findTest() {
        entityManager.persist(Category(1L, "Frutas"))

        val category = categoryRepository.find(1L)

        Assertions.assertNotNull(category)
        Assertions.assertEquals("Frutas", category?.name)
    }

    @Test
    fun testDelete() {
        entityManager.persist(Category(1L, "Frutas"))
        categoryRepository.delete(1L)

        val category = entityManager.find(Category::class.java, 1L)
        Assertions.assertNull(category)
    }

}