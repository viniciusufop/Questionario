/**
 * 
 */
package br.com.vfs.bean;

import java.util.List;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.vfs.bo.OpcaoRespostaObjetivaBO;
import br.com.vfs.bo.RespostaUsuarioBO;
import br.com.vfs.bo.UsuarioBO;
import br.com.vfs.entidade.OpcaoRespostaObjetiva;
import br.com.vfs.entidade.Pergunta;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.entidade.UsuarioResposta;
import br.com.vfs.exception.DefaultException;
import br.com.vfs.util.ContextUtil;

/**
 * @author Vinicius.Faria
 *
 */
@ManagedBean(name = "respostaUsuarioBean")
@RequestScoped
public class RespostaUsuarioBean extends DefaultBean {

	private String email;
	private Questionario questionario;
	private List<Pergunta> listaPerguntaQuestionario;
	private List<Usuario> listaUsuario;
	private Usuario usuario;

	/**
	 * 
	 */
	public RespostaUsuarioBean() {
		super(RespostaUsuarioBean.class);
	}

	public void validaUsuarioQuestionario() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			RespostaUsuarioBO bo = new RespostaUsuarioBO();
			UsuarioBO usuarioBO = new UsuarioBO();
			Usuario user = new Usuario();
			user.setEmail(email);
			Boolean verificacao;
			user = usuarioBO.obter(user);
			if (null == user || null == user.getIdUsuario() || user.getIdUsuario().equals(0)) {
				return;
			}
			verificacao = bo.questionarioRespondidoPorUsuario(user, ContextUtil.getAplicaocaoBean().getQuestionario());
			if (verificacao) {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario já preencheu esse questionario", ""));
			}
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public String inserirResposta() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			UsuarioBO usuarioBO = new UsuarioBO();
			Usuario user = new Usuario();
			user.setEmail(email);
			user = usuarioBO.obter(user);
			if (null == user || null == user.getIdUsuario() || user.getIdUsuario().equals(0)) {
				// criar novo usuario
				usuarioBO.salvar(user);
				user = usuarioBO.obter(user);
			}
			RespostaUsuarioBO bo = new RespostaUsuarioBO();
			Boolean verificacao = bo.salvarRespostaUsuarioQuestionario(user,
					ContextUtil.getAplicaocaoBean().getQuestionario(), listaPerguntaQuestionario);
			if (verificacao) {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario já preencheu esse questionario", ""));
				return "erroResponderQuestionario";
			}

		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao inserir respostas no banco de dados", ""));
			return "erroResponderQuestionario";
		}
		return "questionarioRespondido";
	}

	public String visualizarResposta() {
		try {
			RespostaUsuarioBO bo = new RespostaUsuarioBO();
			ContextUtil.getContextoBean().setUsuario(usuario);
			List<UsuarioResposta> listaResposta = bo.obterRespostaUsuarioQuestionario(usuario,
					ContextUtil.getContextoBean().getQuestionarioSelecionado());
			for (UsuarioResposta usuarioResposta : listaResposta) {
				if(usuarioResposta.getPk().getPergunta().getObjetiva()){
					OpcaoRespostaObjetivaBO opcaoBO = new OpcaoRespostaObjetivaBO();
					List<OpcaoRespostaObjetiva> listOpcao = opcaoBO.obterListaRespostaPorPergunta(usuarioResposta.getPk().getPergunta());
					for (OpcaoRespostaObjetiva opcaoRespostaObjetiva : listOpcao) {
						if(opcaoRespostaObjetiva.getIdOpcaoRespostaObjetiva().equals(usuarioResposta.getRespostaObjetiva())){
							usuarioResposta.setRespostaDiscursiva(opcaoRespostaObjetiva.getDescricao());
						}
					}
					
				}
			}
			ContextUtil.getContextoBean().setListaRespostaUsuario(listaResposta);
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
		}
		return "visualizarRespostaUsuario";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Questionario getQuestionario() {
		if (null == questionario) {
			AplicacaoBean aplicacaoBean = ContextUtil.getAplicaocaoBean();
			questionario = aplicacaoBean.getQuestionario();
		}
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public List<Pergunta> getListaPerguntaQuestionario() {
		if (null == listaPerguntaQuestionario) {
			AplicacaoBean aplicacaoBean = ContextUtil.getAplicaocaoBean();
			listaPerguntaQuestionario = aplicacaoBean.getListaPerguntaQuestionario();
		}
		return listaPerguntaQuestionario;
	}

	public void setListaPerguntaQuestionario(List<Pergunta> listaPerguntaQuestionario) {
		this.listaPerguntaQuestionario = listaPerguntaQuestionario;
	}

	public List<Usuario> getListaUsuario() {
		if (null == listaUsuario) {
			RespostaUsuarioBO bo = new RespostaUsuarioBO();
			try {
				listaUsuario = bo.obterUsuarioQueRespondeuQuestionario(
						ContextUtil.getContextoBean().getQuestionarioSelecionado());
			} catch (DefaultException e) {
				getLogger().log(Level.SEVERE, e.getMessage(), e);
			}
		}
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
