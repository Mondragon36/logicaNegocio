package co.edu.eam.ingesoft.onlinestore.repositories

import co.edu.eam.ingesoft.onlinestore.models.Store
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
@Transactional
class StoreRepository {

    @Autowired
    lateinit var em: EntityManager

    fun create(store: Store){
        em.persist(store)
    }

    fun find(id: String): Store?{
        return em.find(Store::class.java, id)
    }

    fun update(store: Store) {
        em.merge(store)
    }

    fun delete(id: String) {
        val store = find(id)
        if (store!=null) {
            em.remove(store)
        }
    }

}