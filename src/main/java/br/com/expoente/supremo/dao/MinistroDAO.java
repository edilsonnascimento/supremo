package br.com.expoente.supremo.dao;

import br.com.expoente.supremo.entity.Ministro;
import java.util.List;
import java.util.stream.Collectors;
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

    private static final Integer IDADEAPOSENTAR = 75;

    public void salvar(Ministro ministro) {
        manager.persist(ministro);
    }

    public Ministro buscaMinistro(Integer id) {
        return manager.find(Ministro.class, id);
    }

    public List<Ministro> buscaMinistros() {
        return manager.createQuery("SELECT m FROM Ministro m", Ministro.class)
                .getResultList();
    }

    public List<Ministro> ministroMaisVelho(Integer quantidadeAnos) {
        return this.buscaMinistros()
                .stream()
                .filter(m -> m.getIdade() + quantidadeAnos >= IDADEAPOSENTAR)
                .collect(Collectors.toList());
    }

    public List<Ministro> ministroIndicados(String presidente) {
        return this.buscaMinistros()
                .stream()
                .filter(m -> presidente.equals(m.getPresidente()))
                .collect(Collectors.toList());
    }
}
