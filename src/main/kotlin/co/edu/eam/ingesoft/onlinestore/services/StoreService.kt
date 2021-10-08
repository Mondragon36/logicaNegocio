package co.edu.eam.ingesoft.onlinestore.services

import co.edu.eam.ingesoft.onlinestore.exceptions.BusinessException
import co.edu.eam.ingesoft.onlinestore.models.Store
import co.edu.eam.ingesoft.onlinestore.repositories.StoreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class StoreService {

    @Autowired
    lateinit var storeRepository: StoreRepository

    @Autowired
    lateinit var entityManager: EntityManager

    fun createStore(store: Store){
        val storeById = storeRepository.find(store.id)

        if(storeById != null){
            throw BusinessException("This store already exists")
        }

        storeRepository.create(store)
    }

    fun editStore(store: Store){
        storeRepository.find(store.id) ?: throw BusinessException("This store does not exists")
        storeRepository.update(store)
    }

}