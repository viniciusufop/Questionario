/**
 * 
 */
package br.com.vfs.bo;

import br.com.vfs.dao.DaoFactory;
import br.com.vfs.dao.UsuarioDao;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public class UsuarioBO extends DefaultBO {
	
	private UsuarioDao usuarioDao;
	
	/**
	 * 
	 */
	public UsuarioBO() {
		super();
		usuarioDao = DaoFactory.getIntance().criarUsuarioDao();
	}
	
	public void salvar(Usuario usuario) throws DefaultException{
		if(null == usuario){
			throw new DefaultException();
		}
		if(usuario.getIdUsuario() == null || usuario.getIdUsuario().equals(0)){
			usuarioDao.saveEntity(usuario);
		}else{
			usuarioDao.updateEntity(usuario);
		}
	}

	public Usuario logar(Usuario usuario) throws DefaultException {
		Usuario usuarioBanco = obter(usuario);
		if(!usuario.getSenha().equals(usuarioBanco.getSenha())){
			throw new DefaultException("senha não é igual");
		}
		return usuarioBanco;
	}

	public Usuario obter(Usuario usuario) throws DefaultException {
		return usuarioDao.obterUsuario(usuario);
	}

}
