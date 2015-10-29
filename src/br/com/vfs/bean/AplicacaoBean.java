/**
 * 
 */
package br.com.vfs.bean;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import br.com.vfs.entidade.Pergunta;
import br.com.vfs.entidade.Questionario;

/**
 * @author Vinicius.Faria
 *
 */
@ManagedBean(name="aplicacaoBean")
@ApplicationScoped
public class AplicacaoBean extends DefaultBean {
	
	private Questionario questionario;
	private List<Pergunta> listaPerguntaQuestionario;
	/**
	 * 
	 */
	public AplicacaoBean() {
		super(AplicacaoBean.class);
	}
	
	public Questionario getQuestionario() {
		return questionario;
	}
	public void setQuestionario(Questionario questionario) {
		this.questionario = questionario;
	}

	public List<Pergunta> getListaPerguntaQuestionario() {
		return listaPerguntaQuestionario;
	}

	public void setListaPerguntaQuestionario(List<Pergunta> listaPerguntaQuestionario) {
		this.listaPerguntaQuestionario = listaPerguntaQuestionario;
	}
}
