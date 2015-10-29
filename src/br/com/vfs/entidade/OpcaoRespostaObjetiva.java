/**
 * 
 */
package br.com.vfs.entidade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

/**
 * @author Vinicius
 *
 */
@Entity
@Table(name="opcaoRespostaObjetiva")
public class OpcaoRespostaObjetiva extends DefaultEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5665909530263320199L;
	
	@Id
	@NotNull
	@Column(name = "idOpcaoRespostaObjetiva", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idOpcaoRespostaObjetiva;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idPergunta")
	private Pergunta pergunta;
	
	@NotNull
	@JoinColumn(name = "codResposta")
	private Integer codResposta;
	
	@NotNull
	@Column(name = "descricao")
	private String descricao;
	
	/**
	 * 
	 */
	public OpcaoRespostaObjetiva() {
		super();
	}

	/**
	 * @return the idOpcaoRespostaObjetiva
	 */
	public Integer getIdOpcaoRespostaObjetiva() {
		return idOpcaoRespostaObjetiva;
	}

	/**
	 * @param idOpcaoRespostaObjetiva the idOpcaoRespostaObjetiva to set
	 */
	public void setIdOpcaoRespostaObjetiva(Integer idOpcaoRespostaObjetiva) {
		this.idOpcaoRespostaObjetiva = idOpcaoRespostaObjetiva;
	}

	/**
	 * @return the pergunta
	 */
	public Pergunta getPergunta() {
		return pergunta;
	}

	/**
	 * @param pergunta the pergunta to set
	 */
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	/**
	 * @return the codResposta
	 */
	public Integer getCodResposta() {
		return codResposta;
	}

	/**
	 * @param codResposta the codResposta to set
	 */
	public void setCodResposta(Integer codResposta) {
		this.codResposta = codResposta;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
