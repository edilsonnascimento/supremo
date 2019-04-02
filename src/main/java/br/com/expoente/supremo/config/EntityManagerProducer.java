package br.com.expoente.supremo.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rgnoatto
 */
public class EntityManagerProducer {
	
	@PersistenceContext
    private EntityManager entityManager;
 
    @ApplicationScoped
    @Produces
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
