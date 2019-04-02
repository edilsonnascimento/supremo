package br.com.expoente.supremo.service;

import br.com.expoente.supremo.entity.Ministro;
import br.com.expoente.supremo.repository.MinistroRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ednascimento
 */
@Stateless
public class MinistroService {

	@Inject
	private MinistroRepository ministroRepository;

	public void salvar(Ministro ministro) {
		ministroRepository.persist(ministro);
		ministroRepository.flush();
	}

	public Ministro buscaMinistro(Integer id) {
		return ministroRepository.findBy(id);
	}

	public List<Ministro> buscaMinistros() {
		return ministroRepository.findAll();
	}

	public List<Ministro> ministroMaisVelho(Integer quantidadeAnos) {
		return this.buscaMinistros()
				.stream()
				.filter(m -> m.getIdade() + quantidadeAnos >= 75)
				.collect(Collectors.toList());
	}

	public List<Ministro> ministroIndicados(String presidente) {
		return ministroRepository.findOptionalByPresidente(presidente);
	}

	public List<Ministro> buscaNome(String nome) {
		return ministroRepository.findOptionalByNomeLike("%" + nome + "%");
	}
}
