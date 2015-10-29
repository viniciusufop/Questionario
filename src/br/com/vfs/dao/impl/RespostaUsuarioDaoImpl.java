/**
 * 
 */
package br.com.vfs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.vfs.dao.RespostaUsuarioDao;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.entidade.UsuarioResposta;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius.Faria
 *
 */
public class RespostaUsuarioDaoImpl extends DefaultDaoImpl<UsuarioResposta> implements RespostaUsuarioDao {

	/**
	 * 
	 */
	public RespostaUsuarioDaoImpl() {
		super();
	}

	public void salvarLista(List<UsuarioResposta> listaResposta) throws DefaultException{
		for (UsuarioResposta usuarioResposta : listaResposta) {
			saveEntity(usuarioResposta);
		}
	}

	public Boolean questionarioRespondidoPorUsuario(Usuario user, Questionario questionario) throws DefaultException{
		try{
			Query query =  getEntityManager().createQuery("from br.com.vfs.entidade.UsuarioResposta ur where ur.pk.questionario.idQuestionario = :idQuestionario and ur.pk.usuario.idUsuario = :idUsuario");
			query.setParameter("idQuestionario", questionario.getIdQuestionario());
			query.setParameter("idUsuario", user.getIdUsuario());
			@SuppressWarnings("unchecked")
			List<UsuarioResposta> lista = (List<UsuarioResposta>) query.getResultList();
			if(null == lista || lista.size() == 0){
				return Boolean.FALSE;
			}
			return Boolean.TRUE;
		}catch (Exception e) {
			throw new DefaultException(e);
		}
		finally{
			fecharEntityManager();
		}
	}

	@Override
	public List<Usuario> obterUsuarioRespondeuQuestionario(Questionario questionario) throws DefaultException {
		try{
			Query query =  getEntityManager().createQuery("from br.com.vfs.entidade.UsuarioResposta ur where ur.pk.questionario.idQuestionario = :idQuestionario");
			query.setParameter("idQuestionario", questionario.getIdQuestionario());
			@SuppressWarnings("unchecked")
			List<UsuarioResposta> lista = (List<UsuarioResposta>) query.getResultList();
			List<Usuario> listaretorno = new ArrayList<>();
			if(null == lista || lista.size() == 0){
				return listaretorno;
			}
			for (UsuarioResposta usuarioResposta : lista) {
				boolean jaInserido = false;
				for (Usuario usuario : listaretorno) {
					if(usuarioResposta.getPk().getUsuario().getIdUsuario().equals(usuario.getIdUsuario())){
						jaInserido = true;
					}
				}
				if(!jaInserido){
					listaretorno.add(usuarioResposta.getPk().getUsuario());
				}
			}
			return listaretorno;
		}catch (Exception e) {
			throw new DefaultException(e);
		}
		finally{
			fecharEntityManager();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioResposta> obterLista(Usuario user, Questionario questionario) throws DefaultException {
		try{
			Query query =  getEntityManager().createQuery("from br.com.vfs.entidade.UsuarioResposta ur where ur.pk.questionario.idQuestionario = :idQuestionario and ur.pk.usuario.idUsuario = :idUsuario");
			query.setParameter("idQuestionario", questionario.getIdQuestionario());
			query.setParameter("idUsuario", user.getIdUsuario());
			return (List<UsuarioResposta>) query.getResultList();
		}catch (Exception e) {
			throw new DefaultException(e);
		}
		finally{
			fecharEntityManager();
		}
	}
}
