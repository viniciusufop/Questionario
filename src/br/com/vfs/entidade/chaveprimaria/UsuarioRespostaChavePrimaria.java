/**
 * 
 */
package br.com.vfs.entidade.chaveprimaria;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.internal.NotNull;

import br.com.vfs.entidade.Pergunta;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.entidade.Usuario;

/**
 * @author Vinicius.Faria
 *
 */
@Embeddable
public class UsuarioRespostaChavePrimaria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1770126193924734367L;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idUsuario")
	private Usuario usuario;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idQuestionario")
	private Questionario questionario;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idPergunta")
	private Pergunta pergunta;
	
	/**
	 * 
	 */
	public UsuarioRespostaChavePrimaria() {
		super();
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
}
