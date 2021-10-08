package co.edu.eam.ingesoft.onlinestore.services

import co.edu.eam.ingesoft.onlinestore.exceptions.BusinessException
import co.edu.eam.ingesoft.onlinestore.models.Product
import co.edu.eam.ingesoft.onlinestore.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class ProductService {

    @Autowired
    lateinit var productRepository: ProductRepository

    @Autowired
    lateinit var entityManager: EntityManager

    fun createProduct(product: Product) {
        val productById = productRepository.find(product.id)

        if(productById != null){
            throw BusinessException("This Product already exists")
        }

        val productByNamE = productRepository.findName(product.name)

        if(productByNamE != null){
            throw BusinessException("This Product already exists")
        }

        productRepository.create(product)
    }


    fun editProduct(product: Product){
        val productById = productRepository.find(product.id)

        if (productById == null) {
            throw BusinessException("This product does not exist")
        }

        val productByName= productRepository.findName(product.name)

        if(productByName!=null){
            throw BusinessException("This product with this name already exists")
        }

        productRepository.update(product)
    }

}