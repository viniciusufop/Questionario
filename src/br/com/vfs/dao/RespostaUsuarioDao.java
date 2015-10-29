/**
 * 
 */
package br.com.vfs.dao;

import java.util.List;

import br.com.vfs.entidade.Questionario;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.entidade.UsuarioResposta;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public interface RespostaUsuarioDao extends DefaultDao<UsuarioResposta> {
	
	/**
	 * @param listaResposta
	 * @throws DefaultException
	 */
	void salvarLista(List<UsuarioResposta> listaResposta) throws DefaultException;
	
	/**
	 * @param user
	 * @param questionario
	 * @return
	 * @throws DefaultException
	 */
	Boolean questionarioRespondidoPorUsuario(Usuario user, Questionario questionario) throws DefaultException;
	
	/**
	 * @param user
	 * @param questionario
	 * @return
	 * @throws DefaultException
	 */
	List<Usuario> obterUsuarioRespondeuQuestionario(Questionario questionario) throws DefaultException;
	
	/**
	 * @param user
	 * @param questionario
	 * @throws DefaultException
	 */
	List<UsuarioResposta> obterLista(Usuario user, Questionario questionario) throws DefaultException;
}
