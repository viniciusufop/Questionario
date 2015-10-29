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

import br.com.vfs.bo.PerguntaBO;
import br.com.vfs.bo.QuestionarioBO;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.exception.DefaultException;
import br.com.vfs.util.ContextUtil;

/**
 * @author Vinicius
 *
 */
@ManagedBean(name="questionarioBean")
@RequestScoped
public class QuestionarioBean extends DefaultBean {
	
	private List<Questionario> listaQuestionario;
	private List<Questionario> listaQuestionarioUsuario;
	private String nomeQuestionario;
	private Questionario questionario;
	
	/**
	 * 
	 */
	public QuestionarioBean() {
		super(QuestionarioBean.class);
	}
	
	/**
	 * @return
	 */
	public String criarQuestionario(){
		FacesContext context = FacesContext.getCurrentInstance();
		QuestionarioBO bo = new QuestionarioBO();
		try {
			Questionario questionario = new Questionario();
			questionario.setUsuario(ContextUtil.getContextoBean().getUsuarioLogado());
			questionario.setNome(this.nomeQuestionario);
			bo.salvar(questionario);
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao inserir questionario no banco de dados",""));
			return "criarQuestionario";
		}
		
		return "sucesso";
	}
	
	/**
	 * @return
	 */
	public String editarQuestionario(){
		ContextoBean contextoBean = ContextUtil.getContextoBean();
		contextoBean.setQuestionarioSelecionado(questionario);
		
		return "alterarQuestionario";
	}
	
	/**
	 * @return
	 */
	public String respostaQuestionario(){
		ContextoBean contextoBean = ContextUtil.getContextoBean();
		contextoBean.setQuestionarioSelecionado(questionario);
		
		return "visualizarRespostaQuestionario";
	}
	
	/**
	 * @return
	 */
	public String responderQuestionario(){
		try {
			AplicacaoBean aplicacaoBean = ContextUtil.getAplicaocaoBean();
			PerguntaBO bo = new PerguntaBO();
			aplicacaoBean.setQuestionario(questionario);
			aplicacaoBean.setListaPerguntaQuestionario(bo.obterListaPerguntaPorQuestionario(questionario));
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
			return "listaQuestionario";
		}
		return "responderQuestionario";
	}
	
	public String listarQuestionario(){
		return "listarQuestionario";
	}

	/**
	 * @return the nomeQuestionario
	 */
	public String getNomeQuestionario() {
		return nomeQuestionario;
	}

	/**
	 * @param nomeQuestionario the nomeQuestionario to set
	 */
	public void setNomeQuestionario(String nomeQuestionario) {
		this.nomeQuestionario = nomeQuestionario;
	}

	/**
	 * @return the listaQuestionario
	 */
	public List<Questionario> getListaQuestionario() {
		QuestionarioBO bo = new QuestionarioBO();
		
		try {
			listaQuestionario = bo.listarQuestionarios();
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
		}
		return listaQuestionario;
	}

	/**
	 * @param listaQuestionario the listaQuestionario to set
	 */
	public void setListaQuestionario(List<Questionario> listaQuestionario) {
		this.listaQuestionario = listaQuestionario;
	}

	public Questionario getQuestionario() {
		return questionario;
	}

	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public List<Questionario> getListaQuestionarioUsuario() {
		if(null == listaQuestionarioUsuario){
			FacesContext context = FacesContext.getCurrentInstance();
			QuestionarioBO bo = new QuestionarioBO();
			
			try {
				listaQuestionarioUsuario = bo.listarQuestionariosUsuario(ContextUtil.getContextoBean().getUsuarioLogado());
			} catch (DefaultException e) {
				getLogger().log(Level.SEVERE, e.getMessage(), e);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao obter lista de questionario",""));
			}
		}
		return listaQuestionarioUsuario;
	}

	public void setListaQuestionarioUsuario(List<Questionario> listaQuestionarioUsuario) {
		this.listaQuestionarioUsuario = listaQuestionarioUsuario;
	}
}
