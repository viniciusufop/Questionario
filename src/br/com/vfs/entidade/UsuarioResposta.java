/**
 * 
 */
package br.com.vfs.entidade;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.vfs.entidade.chaveprimaria.UsuarioRespostaChavePrimaria;

/**
 * @author Vinicius.Faria
 *
 */
@Entity
@Table(name="usuarioResposta")
public class UsuarioResposta extends DefaultEntidade{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7156492015116033029L;
	
	@EmbeddedId
	private UsuarioRespostaChavePrimaria pk;
	
	@Column(name="respostaDiscursiva", length=100)
	private String respostaDiscursiva;
	
	@Column(name="respostaObjetiva")
	private Integer respostaObjetiva;
	
	/**
	 * 
	 */
	public UsuarioResposta() {
		super();
	}
	
	/**
	 * @return the pk
	 */
	public UsuarioRespostaChavePrimaria getPk() {
		return pk;
	}



	/**
	 * @param pk the pk to set
	 */
	public void setPk(UsuarioRespostaChavePrimaria pk) {
		this.pk = pk;
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
