/**
 * 
 */
package br.com.vfs.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

/**
 * @author Vinicius
 *
 */
/**
 * @author Vinicius
 *
 */
@Entity
@Table(name="questionario")
public class Questionario extends DefaultEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5169322718430029942L;
	
	@Id
	@NotNull
	@Column(name = "idQuestionario", insertable = false, updatable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idQuestionario;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;
	
	@NotNull
	@Column(name = "nome", length = 100)
	private String nome;
	
	@OneToMany(mappedBy="questionario", cascade=CascadeType.ALL)
	private List<Pergunta> listaPergunta;
	
	/**
	 * 
	 */
	public Questionario() {
		super();
	}

	/**
	 * @return the idQuestionario
	 */
	public Integer getIdQuestionario() {
		return idQuestionario;
	}

	/**
	 * @param idQuestionario the idQuestionario to set
	 */
	public void setIdQuestionario(Integer idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pergunta> getListaPergunta() {
		return listaPergunta;
	}

	public void setListaPergunta(List<Pergunta> listaPergunta) {
		this.listaPergunta = listaPergunta;
	}
}
