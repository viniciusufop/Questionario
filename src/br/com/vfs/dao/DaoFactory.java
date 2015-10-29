/**
 * 
 */
package br.com.vfs.dao;

import br.com.vfs.dao.impl.OpcaoRespostaObjetivaDaoImpl;
import br.com.vfs.dao.impl.PerguntaDaoImpl;
import br.com.vfs.dao.impl.QuestionarioDaoImpl;
import br.com.vfs.dao.impl.RespostaUsuarioDaoImpl;
import br.com.vfs.dao.impl.UsuarioDaoImpl;

/**
 * @author Vinicius
 *
 */
public final class DaoFactory {
	
	private static DaoFactory instance;
	
	/**
	 * 
	 */
	private DaoFactory() {
	}
	
	public synchronized static DaoFactory getIntance(){
		if(null == instance){
			instance = new DaoFactory();
		}
		return instance;
	}
	
	public UsuarioDao criarUsuarioDao(){
		return new UsuarioDaoImpl();
	}
	
	public QuestionarioDao criarQuestionarioDao(){
		return new QuestionarioDaoImpl();
	}
	
	public PerguntaDao criarPerguntaDao(){
		return new PerguntaDaoImpl();
	}
	
	public OpcaoRespostaObjetivaDao criarOpcaoRespostaObjetivaDao(){
		return new OpcaoRespostaObjetivaDaoImpl();
	}
	
	public RespostaUsuarioDao criarRespostaUsuarioDao(){
		return new RespostaUsuarioDaoImpl();
	}
}
