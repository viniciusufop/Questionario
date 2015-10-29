/**
 * 
 */
package br.com.vfs.bean;

import java.util.List;
import java.util.logging.Level;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.vfs.bo.UsuarioBO;
import br.com.vfs.entidade.Pergunta;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.entidade.UsuarioResposta;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
@ManagedBean(name="contextoBean")
@SessionScoped
public class ContextoBean extends DefaultBean{
	
	private Usuario usuarioLogado;
	private Usuario usuario;
	private Questionario questionarioSelecionado;
	private Pergunta perguntaSelecionada;
	private List<UsuarioResposta> listaRespostaUsuario;

	/**
	 * 
	 */
	public ContextoBean() {
		super(ContextoBean.class);
	}

	/**
	 * @return the usuarioLogado
	 */
	public Usuario getUsuarioLogado() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		UsuarioBO bo = new UsuarioBO();
		try {
			if(null == usuarioLogado || !usuarioLogado.getEmail().equals(login)){
				usuarioLogado = new Usuario();
				usuarioLogado.setEmail(login);
				usuarioLogado = bo.obter(usuarioLogado);
			}
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
		}
		
		return usuarioLogado;
	}

	/**
	 * @param usuarioLogado the usuarioLogado to set
	 */
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Questionario getQuestionarioSelecionado() {
		return questionarioSelecionado;
	}

	public void setQuestionarioSelecionado(Questionario questionarioSelecionado) {
		this.questionarioSelecionado = questionarioSelecionado;
	}

	/**
	 * @return the perguntaSelecionada
	 */
	public Pergunta getPerguntaSelecionada() {
		return perguntaSelecionada;
	}

	/**
	 * @param perguntaSelecionada the perguntaSelecionada to set
	 */
	public void setPerguntaSelecionada(Pergunta perguntaSelecionada) {
		this.perguntaSelecionada = perguntaSelecionada;
	}
	
	
	/**
	 * @return the listaRespostaUsuario
	 */
	public List<UsuarioResposta> getListaRespostaUsuario() {
		return listaRespostaUsuario;
	}

	/**
	 * @param listaRespostaUsuario the listaRespostaUsuario to set
	 */
	public void setListaRespostaUsuario(List<UsuarioResposta> listaRespostaUsuario) {
		this.listaRespostaUsuario = listaRespostaUsuario;
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
}
