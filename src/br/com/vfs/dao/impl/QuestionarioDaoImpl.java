/**
 * 
 */
package br.com.vfs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.vfs.dao.QuestionarioDao;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public class QuestionarioDaoImpl extends DefaultDaoImpl<Questionario> implements QuestionarioDao{

	/**
	 * 
	 */
	public QuestionarioDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public List<Questionario> listarQuestionariosUsuario(Usuario usuario) throws DefaultException{
		try{
			Query query =  getEntityManager().createQuery("from br.com.vfs.entidade.Questionario quest where quest.usuario.idUsuario = :idUsuario");
			query.setParameter("idUsuario", usuario.getIdUsuario());
			@SuppressWarnings("unchecked")
			List<Questionario> lista = (List<Questionario>) query.getResultList();
			if(null == lista){
				lista = new ArrayList<Questionario>();
			}
			return lista;
		}catch (Exception e) {
			throw new DefaultException(e);
		}
		finally{
			fecharEntityManager();
		}
	}
	
	public List<Questionario> listarQuestionarios() throws DefaultException{
		try{
			Query query =  getEntityManager().createQuery("from br.com.vfs.entidade.Questionario quest");
			
			@SuppressWarnings("unchecked")
			List<Questionario> lista = (List<Questionario>) query.getResultList();
			if(null == lista){
				lista = new ArrayList<Questionario>();
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
