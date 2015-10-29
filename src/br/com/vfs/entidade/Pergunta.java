/**
 * 
 */
package br.com.vfs.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Entity
@Table(name = "pergunta")
public class Pergunta extends DefaultEntidade {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4306561018313764302L;

	@Id
	@NotNull
	@Column(name = "idPergunta", insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPergunta;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idQuestionario")
	private Questionario questionario;

	@NotNull
	@Column(name = "codPergunta")
	private Integer codPergunta;

	@NotNull
	@Column(name = "descricao", length = 500)
	private String descricao;

	@NotNull
	@Column(name = "objetiva")
	private Boolean objetiva = Boolean.FALSE;
	
	@OneToMany(mappedBy="pergunta", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<OpcaoRespostaObjetiva> listaResposta;
	
	// Elementos nao mapeados pois fazem parte da logica para preencher a resposta do usuario
	// nao estao na tabela pergunta
	private String respostaDiscursiva;
	
	private Integer respostaObjetiva;
	
	/**
	 * 
	 */
	public Pergunta() {
		super();
	}

	/**
	 * @return the idPergunta
	 */
	public Integer getIdPergunta() {
		return idPergunta;
	}

	/**
	 * @param idPergunta the idPergunta to set
	 */
	public void setIdPergunta(Integer idPergunta) {
		this.idPergunta = idPergunta;
	}

	/**
	 * @return the questionario
	 */
	public Questionario getQuestionario() {
		return questionario;
	}

	/**
	 * @param questionario the questionario to set
	 */
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	/**
	 * @return the codPergunta
	 */
	public Integer getCodPergunta() {
		return codPergunta;
	}

	/**
	 * @param codPergunta the codPergunta to set
	 */
	public void setCodPergunta(Integer codPergunta) {
		this.codPergunta = codPergunta;
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

	/**
	 * @return the objetiva
	 */
	public Boolean getObjetiva() {
		return objetiva;
	}

	/**
	 * @param objetiva the objetiva to set
	 */
	public void setObjetiva(Boolean objetiva) {
		this.objetiva = objetiva;
	}

	/**
	 * @return the listaResposta
	 */
	public List<OpcaoRespostaObjetiva> getListaResposta() {
		return listaResposta;
	}

	/**
	 * @param listaResposta the listaResposta to set
	 */
	public void setListaResposta(List<OpcaoRespostaObjetiva> listaResposta) {
		this.listaResposta = listaResposta;
	}

	/**
	 * @return the respostaDiscursiva
	 */
	public String getRespostaDiscursiva() {
		return respostaDiscursiva;
	}

	/**
	 * @param respostaDiscursiva the respostaDiscursiva to set
	 */
	public void setRespostaDiscursiva(String respostaDiscursiva) {
		this.respostaDiscursiva = respostaDiscursiva;
	}

	/**
	 * @return the respostaObjetiva
	 */
	public Integer getRespostaObjetiva() {
		return respostaObjetiva;
	}

	/**
	 * @param respostaObjetiva the respostaObjetiva to set
	 */
	public void setRespostaObjetiva(Integer respostaObjetiva) {
		this.respostaObjetiva = respostaObjetiva;
	}
}
