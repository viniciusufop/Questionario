/**
 * 
 */
package br.com.vfs.bo;

import java.util.ArrayList;
import java.util.List;

import br.com.vfs.dao.DaoFactory;
import br.com.vfs.dao.RespostaUsuarioDao;
import br.com.vfs.entidade.Pergunta;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.entidade.UsuarioResposta;
import br.com.vfs.entidade.chaveprimaria.UsuarioRespostaChavePrimaria;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius.Faria
 *
 */
public class RespostaUsuarioBO extends DefaultBO {
	
	private RespostaUsuarioDao dao;
	/**
	 * 
	 */
	public RespostaUsuarioBO() {
		super();
		dao = DaoFactory.getIntance().criarRespostaUsuarioDao();
	}
	
	public Boolean questionarioRespondidoPorUsuario(Usuario user, Questionario questionario) throws DefaultException{
		return dao.questionarioRespondidoPorUsuario(user,questionario);
	}
	
	public Boolean salvarRespostaUsuarioQuestionario(Usuario user, Questionario questionario,
			List<Pergunta> listaPerguntaQuestionario) throws DefaultException{
		List<UsuarioResposta> listaResposta = new ArrayList<UsuarioResposta>();
		
		if(questionarioRespondidoPorUsuario(user,questionario)){
			return Boolean.TRUE;
		}
		for (Pergunta pergunta : listaPerguntaQuestionario) {
			UsuarioResposta usuarioResposta = new UsuarioResposta();
			usuarioResposta.setPk(new UsuarioRespostaChavePrimaria());
			usuarioResposta.getPk().setPergunta(pergunta);
			usuarioResposta.getPk().setQuestionario(questionario);
			usuarioResposta.getPk().setUsuario(user);
			usuarioResposta.setRespostaDiscursiva(pergunta.getRespostaDiscursiva());
			usuarioResposta.setRespostaObjetiva(pergunta.getRespostaObjetiva());
			listaResposta.add(usuarioResposta);
		}
		
		dao.salvarLista(listaResposta);
		return Boolean.FALSE;
	}
	
	public List<UsuarioResposta> obterRespostaUsuarioQuestionario(Usuario user, Questionario questionario) throws DefaultException{
		return dao.obterLista(user, questionario);
	}

	public List<Usuario> obterUsuarioQueRespondeuQuestionario(Questionario questionarioSelecionado) throws DefaultException {
		return dao.obterUsuarioRespondeuQuestionario(questionarioSelecionado);
	}

}
