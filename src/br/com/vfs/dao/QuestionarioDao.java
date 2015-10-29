/**
 * 
 */
package br.com.vfs.dao;

import java.util.List;

import br.com.vfs.entidade.Questionario;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public interface QuestionarioDao extends DefaultDao<Questionario> {
	
	
	/**
	 * @param usuario
	 * @return
	 * @throws DefaultException
	 */
	List<Questionario> listarQuestionariosUsuario(Usuario usuario) throws DefaultException;
		
	/**
	 * @return
	 * @throws DefaultException
	 */
	List<Questionario> listarQuestionarios() throws DefaultException;
}
