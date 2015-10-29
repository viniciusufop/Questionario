/**
 * 
 */
package br.com.vfs.bo;

import java.util.List;

import br.com.vfs.dao.DaoFactory;
import br.com.vfs.dao.PerguntaDao;
import br.com.vfs.entidade.Pergunta;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius.Faria
 *
 */
public class PerguntaBO extends DefaultBO {
	
	PerguntaDao dao;
	
	/**
	 * 
	 */
	public PerguntaBO() {
		super();
		dao = DaoFactory.getIntance().criarPerguntaDao();
	}
	
	public void salvar(Pergunta pergunta) throws DefaultException{
		if(null == pergunta || null == pergunta.getCodPergunta() ||
				null == pergunta.getDescricao() || null == pergunta.getObjetiva() ||
				null == pergunta.getQuestionario()){
			throw new DefaultException("Objeto não está preenchido corretamente");
		}
		
		dao.saveEntity(pergunta);
	}
	
	public void excluir(Pergunta pergunta) throws DefaultException{
		if(null == pergunta || null == pergunta.getIdPergunta()){
			throw new DefaultException("Objeto não está preenchido corretamente");
		}
		dao.removeEntity(pergunta.getIdPergunta(), Pergunta.class);
	}

	public List<Pergunta> obterListaPerguntaPorQuestionario(Questionario questionario) throws DefaultException{
		return dao.obterListaPerguntaPorQuestionario(questionario);
	}

	public void atualizarOutrasPerguntas(Pergunta pergunta) throws DefaultException {
		List<Pergunta> lista = dao.obterListaPerguntaPorQuestionario(pergunta.getQuestionario());
		if(null != lista){
			int contador = 1;
			for (Pergunta perguntaRestante : lista) {
				if(!perguntaRestante.getCodPergunta().equals(contador)){
					perguntaRestante.setCodPergunta(contador);
					dao.updateEntity(perguntaRestante);
				}
				contador++;
			}	
		}
	}

	public void alterar(Pergunta pergunta) throws DefaultException {
		dao.updateEntity(pergunta);
	}
	
}
