/**
 * 
 */
package br.com.vfs.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.vfs.dao.UsuarioDao;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public class UsuarioDaoImpl extends DefaultDaoImpl<Usuario> implements UsuarioDao{

	/**
	 * 
	 */
	public UsuarioDaoImpl() {
		super();
	}

	public Usuario obterUsuario(Usuario usuario) throws DefaultException {
		try{
			Query query =  getEntityManager().createQuery("from br.com.vfs.entidade.Usuario usr where usr.email = :email");
			query.setParameter("email", usuario.getEmail());
			usuario = (Usuario) query.getSingleResult();
			return usuario;
		}catch (NoResultException e) {
			return usuario;
		}catch (Exception e) {
			throw new DefaultException(e);
		}
		finally{
			fecharEntityManager();
		}
	}

}
