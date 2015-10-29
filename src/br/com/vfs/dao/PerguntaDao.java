package br.com.vfs.dao;

import java.util.List;

import br.com.vfs.entidade.Pergunta;
import br.com.vfs.entidade.Questionario;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public interface PerguntaDao extends DefaultDao<Pergunta> {
	/**
	 * @param questionario
	 * @return
	 * @throws DefaultException
	 */
	List<Pergunta> obterListaPerguntaPorQuestionario(Questionario questionario) throws DefaultException;
}
