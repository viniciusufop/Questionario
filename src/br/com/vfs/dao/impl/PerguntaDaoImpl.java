/**
 * 
 */
package br.com.vfs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.vfs.dao.PerguntaDao;
import br.com.vfs.entidade.Pergunta;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius.Faria
 *
 */
public class PerguntaDaoImpl extends DefaultDaoImpl<Pergunta> implements PerguntaDao{

	/**
	 * 
	 */
	public PerguntaDaoImpl() {
		super();
	}

	public List<Pergunta> obterListaPerguntaPorQuestionario(Questionario questionario) throws DefaultException {
		try{
			Query query =  getEntityManager().createQuery("from br.com.vfs.entidade.Pergunta perg where perg.questionario.idQuestionario = :idQuestionario order by perg.codPergunta");
			query.setParameter("idQuestionario", questionario.getIdQuestionario());
			@SuppressWarnings("unchecked")
			List<Pergunta> lista = (List<Pergunta>) query.getResultList();
			if(null == lista){
				lista = new ArrayList<Pergunta>();
			}
			return lista;
		}catch (Exception e) {
			throw new DefaultException(e);
		}
		finally{
			fecharEntityManager();
		}
	}

}
