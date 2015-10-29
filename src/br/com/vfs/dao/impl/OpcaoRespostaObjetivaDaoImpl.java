/**
 * 
 */
package br.com.vfs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.vfs.dao.OpcaoRespostaObjetivaDao;
import br.com.vfs.entidade.OpcaoRespostaObjetiva;
import br.com.vfs.entidade.Pergunta;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public class OpcaoRespostaObjetivaDaoImpl extends DefaultDaoImpl<OpcaoRespostaObjetiva> implements OpcaoRespostaObjetivaDao{

	/**
	 * 
	 */
	public OpcaoRespostaObjetivaDaoImpl() {
		super();
	}

	public List<OpcaoRespostaObjetiva> obterListaRespostaPorPergunta(Pergunta pergunta) throws DefaultException {
		try{
			Query query =  getEntityManager().createQuery("from br.com.vfs.entidade.OpcaoRespostaObjetiva opc where opc.pergunta.idPergunta = :idPergunta order by opc.codResposta");
			query.setParameter("idPergunta", pergunta.getIdPergunta());
			@SuppressWarnings("unchecked")
			List<OpcaoRespostaObjetiva> lista = (List<OpcaoRespostaObjetiva>) query.getResultList();
			if(null == lista){
				lista = new ArrayList<OpcaoRespostaObjetiva>();
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
