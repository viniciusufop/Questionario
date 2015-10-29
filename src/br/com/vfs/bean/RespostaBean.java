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
import br.com.vfs.entidade.OpcaoRespostaObjetiva;
import br.com.vfs.entidade.Pergunta;
import br.com.vfs.exception.DefaultException;
import br.com.vfs.util.ContextUtil;

/**
 * @author Vinicius
 *
 */
@ManagedBean(name="respostaBean")
@RequestScoped
public class RespostaBean extends DefaultBean {
	
	private OpcaoRespostaObjetiva resposta;
	private List<OpcaoRespostaObjetiva> listaRespostaPergunta;
	/**
	 * 
	 */
	public RespostaBean() {
		super(RespostaBean.class);
		resposta = new OpcaoRespostaObjetiva();
	}
	public void inserirResposta(){
		FacesContext context = FacesContext.getCurrentInstance();
		OpcaoRespostaObjetivaBO bo = new OpcaoRespostaObjetivaBO();
		try {
			Pergunta pergunta  = ContextUtil.getContextoBean().getPerguntaSelecionada();
			
			List<OpcaoRespostaObjetiva> listaResposta = bo.obterListaRespostaPorPergunta(pergunta);
			if(null == listaResposta){
				resposta.setCodResposta(1);
			} else {
				resposta.setCodResposta(listaResposta.size()+1);
			}
			
			resposta.setPergunta(pergunta);
			bo.salvar(resposta);
			this.resposta = new OpcaoRespostaObjetiva();
			this.listaRespostaPergunta = null;
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao inserir resposta no banco de dados",""));
		}
	}
	
	public void excluirResposta(){
		FacesContext context = FacesContext.getCurrentInstance();
		OpcaoRespostaObjetivaBO bo = new OpcaoRespostaObjetivaBO();
		try {
			bo.excluir(resposta);
			bo.atualizarOutrasRespostas(resposta);
			listaRespostaPergunta = null;
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao excluir resposta no banco de dados",""));
		}
	}
	/**
	 * @return the resposta
	 */
	public OpcaoRespostaObjetiva getResposta() {
		return resposta;
	}
	/**
	 * @param resposta the resposta to set
	 */
	public void setResposta(OpcaoRespostaObjetiva resposta) {
		this.resposta = resposta;
	}
	/**
	 * @return the listaRespostaPergunta
	 */
	public List<OpcaoRespostaObjetiva> getListaRespostaPergunta() {
		if(null == listaRespostaPergunta){
			FacesContext context = FacesContext.getCurrentInstance();
			OpcaoRespostaObjetivaBO bo = new OpcaoRespostaObjetivaBO();
			try {
				listaRespostaPergunta = bo.obterListaRespostaPorPergunta(ContextUtil.getContextoBean().getPerguntaSelecionada());
			} catch (DefaultException e) {
				getLogger().log(Level.SEVERE, e.getMessage(), e);
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao obter lista de respostas no banco de dados",""));
			}
		}
		
		return listaRespostaPergunta;
	}
	/**
	 * @param listaRespostaPergunta the listaRespostaPergunta to set
	 */
	public void setListaRespostaPergunta(List<OpcaoRespostaObjetiva> listaRespostaPergunta) {
		this.listaRespostaPergunta = listaRespostaPergunta;
	}
}
