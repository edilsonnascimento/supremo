package br.com.expoente.supremo.repository;

import br.com.expoente.supremo.entity.Ministro;
import java.io.Serializable;
import java.util.List;
import org.apache.deltaspike.data.api.FullEntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 *
 * @author ednascimento
 */
@Repository
public interface MinistroRepository extends FullEntityRepository<Ministro, Serializable> {

	List<Ministro> findOptionalByNomeLike(String nome);

	List<Ministro> findOptionalByPresidente(String presidente);

}
