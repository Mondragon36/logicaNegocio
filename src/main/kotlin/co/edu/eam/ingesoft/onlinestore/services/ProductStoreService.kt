package co.edu.eam.ingesoft.onlinestore.services

import co.edu.eam.ingesoft.onlinestore.exceptions.BusinessException
import co.edu.eam.ingesoft.onlinestore.models.ProductStore
import co.edu.eam.ingesoft.onlinestore.repositories.ProductStoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class ProductStoreService {

    @Autowired
    lateinit var productStoreRepository: ProductStoreRepository

    @Autowired
    lateinit var entityManager: EntityManager

    fun createProductStore(productStore: ProductStore) {
        val productById = productStoreRepository.findByProduct(productStore.id)

        if (productById.size != 0) {
            throw BusinessException("This product already exists on the store")
        }
        productStoreRepository.create(productStore)
    }

    fun  decreaseStockProductStore(productStore: ProductStore) {
        val productStore = entityManager.find(ProductStore::class.java, productStore?.id)
        if (productStore.stock > 0){
            productStore.stock = productStore.stock - 1
            productStoreRepository.update(productStore)
        }
        else{
            throw BusinessException("There is not stock")
        }
    }

    fun  increaseStockProductStore(productStore: ProductStore) {
        val productStore = entityManager.find(ProductStore::class.java, productStore?.id)
        if (productStore.stock >= 0){
            productStore.stock = productStore.stock + 1
            productStoreRepository.update(productStore)
        }
        else{
            throw BusinessException("There is not stock")
        }
    }
}