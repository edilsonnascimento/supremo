package br.com.expoente.supremo.dao;

import br.com.expoente.supremo.entity.Ministro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ednascimento
 */
@Stateless
public class MinistroDao {

    @PersistenceContext
    private EntityManager manager;

    public void salvar(Ministro ministro) {
        manager.persist(ministro);
    }

    public Ministro buscaMinistro(Integer id) {
        return manager.find(Ministro.class, id);
    }
}
