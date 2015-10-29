/**
 * 
 */
package br.com.vfs.bean;

import java.util.logging.Logger;

/**
 * 
 * @author Vinicius
 *
 */
public class DefaultBean{
	
	private Logger logger;
	
	/**
	 * Contrutor default
	 */
	public DefaultBean(Class<? extends DefaultBean> classe) {
		super();
		logger = Logger.getLogger(classe.getName());
	}

	/**
	 * @return the logger
	 */
	protected Logger getLogger() {
		return logger;
	}
}
