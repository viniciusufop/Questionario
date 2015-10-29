/**
 * 
 */
package br.com.vfs.bo;

import java.util.List;

import br.com.vfs.dao.DaoFactory;
import br.com.vfs.dao.QuestionarioDao;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public class QuestionarioBO extends DefaultBO {
	
	private QuestionarioDao dao;
	/**
	 * 
	 */
	public QuestionarioBO() {
		dao = DaoFactory.getIntance().criarQuestionarioDao();
	}
	
	public void salvar(Questionario questionario) throws DefaultException{
		if(null == questionario){
			throw new DefaultException();
		}
		if(questionario.getIdQuestionario() == null || questionario.getIdQuestionario().equals(0)){
			dao.saveEntity(questionario);
		}else{
			dao.updateEntity(questionario);
		}
	}

	public List<Questionario> listarQuestionariosUsuario(Usuario usuario) throws DefaultException{
		return dao.listarQuestionariosUsuario(usuario);
	}

	public List<Questionario> listarQuestionarios() throws DefaultException{
		return dao.listarQuestionarios();
	}
}
