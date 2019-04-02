package br.com.expoente.supremo.entity;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ednascimento
 */
@Entity
public class Ministro implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dataNascimento;

	private String presidente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getPresidente() {
		return presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	public Integer getIdade() {
		Calendar dataAtual = Calendar.getInstance();
		return (dataAtual.get(Calendar.YEAR) - this.getDataNascimento().get(Calendar.YEAR));
	}

	public Integer getNumeroProcessos() {
		return 300;
	}

}
