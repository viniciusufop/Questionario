/**
 * 
 */
package br.com.vfs.dao;

import java.util.List;

import br.com.vfs.entidade.OpcaoRespostaObjetiva;
import br.com.vfs.entidade.Pergunta;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public interface OpcaoRespostaObjetivaDao extends DefaultDao<OpcaoRespostaObjetiva> {
	
	List<OpcaoRespostaObjetiva> obterListaRespostaPorPergunta(Pergunta pergunta) throws DefaultException;
}
