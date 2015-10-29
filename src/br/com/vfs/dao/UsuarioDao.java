/**
 * 
 */
package br.com.vfs.dao;

import br.com.vfs.entidade.Usuario;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public interface UsuarioDao extends DefaultDao<Usuario> {
	
	/**
	 * @param usuario
	 * @return
	 * @throws DefaultException
	 */
	Usuario obterUsuario(Usuario usuario) throws DefaultException;
}
