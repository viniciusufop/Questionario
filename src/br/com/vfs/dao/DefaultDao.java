/**
 * 
 */
package br.com.vfs.dao;

import br.com.vfs.entidade.DefaultEntidade;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public interface DefaultDao<T extends DefaultEntidade>{
	
	/**
	 * @param entidade
	 * @return
	 * @throws DefaultException
	 */
	Boolean saveEntity(T entidade)
			throws DefaultException;
	
	/**
	 * @param entidae
	 * @return
	 * @throws DefaultException
	 */
	Boolean updateEntity(T entidae)
			throws DefaultException;
	/**
	 * @param id
	 * @param entidade
	 * @return
	 */
	T getEntity(Object id,
			Class<? extends T> entidade);
	/**
	 * @param id
	 * @param entidade
	 * @return
	 * @throws DefaultException
	 */
	boolean removeEntity(Object id, Class<? extends T> entidade) 
			throws DefaultException;
}
