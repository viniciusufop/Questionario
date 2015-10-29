/**
 * 
 */
package br.com.vfs.bo;

import java.util.List;

import br.com.vfs.dao.DaoFactory;
import br.com.vfs.dao.OpcaoRespostaObjetivaDao;
import br.com.vfs.entidade.OpcaoRespostaObjetiva;
import br.com.vfs.entidade.Pergunta;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public class OpcaoRespostaObjetivaBO extends DefaultBO {
	
	private OpcaoRespostaObjetivaDao dao;
	/**
	 * 
	 */
	public OpcaoRespostaObjetivaBO() {
		dao = DaoFactory.getIntance().criarOpcaoRespostaObjetivaDao();
	}
	public List<OpcaoRespostaObjetiva> obterListaRespostaPorPergunta(Pergunta pergunta) throws DefaultException {
		return dao.obterListaRespostaPorPergunta(pergunta);
	}
	
	public void salvar(OpcaoRespostaObjetiva resposta) throws DefaultException{
		if(null == resposta || null == resposta.getCodResposta() ||
				null == resposta.getPergunta() || null == resposta.getDescricao()){
			throw new DefaultException("Objeto não está preenchido corretamente");
		}
		
		dao.saveEntity(resposta);
	}
	public void excluir(OpcaoRespostaObjetiva resposta) throws DefaultException{
		if(null == resposta || null == resposta.getIdOpcaoRespostaObjetiva()){
			throw new DefaultException("Objeto não está preenchido corretamente");
		}
		dao.removeEntity(resposta.getIdOpcaoRespostaObjetiva(), OpcaoRespostaObjetiva.class);
		
	}
	public void atualizarOutrasRespostas(OpcaoRespostaObjetiva resposta) throws DefaultException {
		List<OpcaoRespostaObjetiva> lista = dao.obterListaRespostaPorPergunta(resposta.getPergunta());
		if(null != lista){
			int contador = 1;
			for (OpcaoRespostaObjetiva respostaRestante : lista) {
				if(!respostaRestante.getCodResposta().equals(contador)){
					respostaRestante.setCodResposta(contador);
					dao.updateEntity(respostaRestante);
				}
				contador++;
			}	
		}
	}

}
