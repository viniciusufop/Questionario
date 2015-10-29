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
import br.com.vfs.entidade.Pergunta;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.exception.DefaultException;
import br.com.vfs.util.ContextUtil;

/**
 * @author Vinicius.Faria
 *
 */
@ManagedBean(name="perguntaBean")
@RequestScoped
public class PerguntaBean extends DefaultBean {
	
	Pergunta pergunta;
	private List<Pergunta> listaPerguntaQuestionario;
	
	/**
	 * 
	 */
	public PerguntaBean() {
		super(PerguntaBean.class);
		pergunta = new Pergunta();
	}
	
	
	
	/**
	 * @return
	 */
	public void inserirPergunta(){
		FacesContext context = FacesContext.getCurrentInstance();
		PerguntaBO bo = new PerguntaBO();
		try {
			Questionario questionario = ContextUtil.getContextoBean().getQuestionarioSelecionado();
			
			List<Pergunta> listaPerguntaQuestionario = bo.obterListaPerguntaPorQuestionario(questionario);
			if(null == listaPerguntaQuestionario){
				pergunta.setCodPergunta(1);
			} else {
				pergunta.setCodPergunta(listaPerguntaQuestionario.size()+1);
			}
			
			pergunta.setQuestionario(questionario);
			bo.salvar(pergunta);
			this.pergunta = new Pergunta();
			this.listaPerguntaQuestionario  = null;
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao inserir questionario no banco de dados",""));
		}
	}
	
	/**
	 * @return
	 */
	public String edicaoPergunta(){
		ContextoBean contextoBean = ContextUtil.getContextoBean();
		contextoBean.setPerguntaSelecionada(pergunta);
		
		return "alterarPergunta";
	}
	
	/**
	 * @return
	 */
	public String inserirRespostasPergunta(){
		ContextoBean contextoBean = ContextUtil.getContextoBean();
		contextoBean.setPerguntaSelecionada(pergunta);
		
		return "editarResposta";
	}	
	
	
	/**
	 * @return
	 */
	public String editarPergunta(){
		PerguntaBO bo = new PerguntaBO();
		try {
			bo.alterar(ContextUtil.getContextoBean().getPerguntaSelecionada());
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
		}
		return "alterarQuestionario";
	}
	
	/**
	 * @return
	 */
	public void excluirPergunta(){
		PerguntaBO bo = new PerguntaBO();
		try {
			bo.excluir(pergunta);
			bo.atualizarOutrasPerguntas(pergunta);
			listaPerguntaQuestionario = null;
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	
	public List<Pergunta> getListaPerguntaQuestionario() {
		if(null == listaPerguntaQuestionario){
			FacesContext context = FacesContext.getCurrentInstance();
			PerguntaBO bo = new PerguntaBO();
			try {
				listaPerguntaQuestionario = bo.obterListaPerguntaPorQuestionario(ContextUtil.getContextoBean().getQuestionarioSelecionado());
			} catch (DefaultException e) {
				getLogger().log(Level.SEVERE, e.getMessage(), e);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao inserir questionario no banco de dados",""));
			}
		}
		return listaPerguntaQuestionario;
	}
	
	public void setListaPerguntaQuestionario(List<Pergunta> listaPerguntaQuestionario) {
		this.listaPerguntaQuestionario = listaPerguntaQuestionario;
	}
}
