/**
 * 
 */
package br.com.vfs.exception;

/**
 * @author Vinicius
 *
 */
public class DefaultException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2268073358678976483L;

	/**
	 * 
	 */
	public DefaultException() {
	}

	/**
	 * @param arg0
	 */
	public DefaultException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public DefaultException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DefaultException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public DefaultException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
