/**
 * 
 */
package br.com.vfs.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import br.com.vfs.dao.DefaultDao;
import br.com.vfs.entidade.DefaultEntidade;
import br.com.vfs.exception.DefaultException;

/**
 * @author Vinicius
 *
 */
public abstract class DefaultDaoImpl<T extends DefaultEntidade> implements DefaultDao<T> {

	private static EntityManagerFactory eManagerFactory;
	protected static EntityManager eManager;

	{
		if (eManagerFactory == null || !eManagerFactory.isOpen())
			eManagerFactory = Persistence.createEntityManagerFactory("emQuestionario");
	}

	/**
	 * 
	 */
	public DefaultDaoImpl() {
		super();
	}

	public Boolean saveEntity(T entidade) throws DefaultException {
		Boolean retorno = false;
		try {
			eManager = eManagerFactory.createEntityManager();
			eManager.getTransaction().begin();
			eManager.persist(entidade);
			eManager.getTransaction().commit();
			retorno = true;
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DefaultException(e);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefaultException(e);
		} finally {
			eManager.close();
		}
		return retorno;
	}

	public Boolean updateEntity(T entidae) throws DefaultException {
		Boolean retorno = false;
		try {
			eManager = eManagerFactory.createEntityManager();
			eManager.getTransaction().begin();
			eManager.merge(entidae);
			eManager.getTransaction().commit();
			retorno = true;
		} catch (PersistenceException e) {
			e.printStackTrace();
			throw new DefaultException();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefaultException();
		} finally {
			eManager.close();
		}
		return retorno;
	}

	public T getEntity(Object id, Class<? extends T> entidade) {
		eManager = eManagerFactory.createEntityManager();
		eManager.getTransaction().begin();
		T spotLostEntity = eManager.find(entidade, id);
		comitarEFecharTransacao();
		return spotLostEntity;
	}

	public boolean removeEntity(Object id, Class<? extends T> entidade) 
			throws DefaultException {
		Boolean retorno = false;
		try {
			eManager = eManagerFactory.createEntityManager();
			eManager.getTransaction().begin();
			T objeto = eManager.find(entidade, id);
			eManager.remove(objeto);
			retorno = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DefaultException();
		} finally {
			comitarEFecharTransacao();
		}
		return retorno;
	}

	protected EntityManager getEntityManager() {
		if (eManager == null || !eManager.isOpen())
			abrirTransacao();

		return eManager;
	}

	protected void fecharEntityManager() {
		eManager.close();
	}

	protected void abrirTransacao() {
		eManager = eManagerFactory.createEntityManager();
		eManager.getTransaction().begin();
	}

	protected void comitarEFecharTransacao() {
		eManager.getTransaction().commit();
	}

	protected void rollbackTransacao() {
		eManager.getTransaction().rollback();
	}

}
