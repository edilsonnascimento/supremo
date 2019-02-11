package br.com.expoente.supremo.dao;

import br.com.expoente.supremo.entity.Ministro;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ednascimento
 */
@Stateless
public class MinistroDAO {

    @PersistenceContext
    private EntityManager manager;

    public void salvar(Ministro ministro) {
        manager.persist(ministro);
    }

    public Ministro buscaMinistro(Integer id) {
        return manager.find(Ministro.class, id);
    }
    
    public List<Ministro> buscaMinistros(){
        return manager.createQuery("SELECT m FROM Ministro m", Ministro.class)
                .getResultList();
    }
    
    public Ministro ministroMaisVelho(){
        return manager.createQuery("SELECT m2 FROM Ministro m2 WHERE m2.dataNascimento = (SELECT MIN(m.dataNascimento) FROM Ministro m)", Ministro.class)
                .getSingleResult();
    }
}
