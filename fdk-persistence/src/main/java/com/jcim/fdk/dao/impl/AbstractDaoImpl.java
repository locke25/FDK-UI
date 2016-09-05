package com.jcim.fdk.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.jcim.fdk.dao.AbstractDao;
import com.jcim.fdk.model.EntityBase;

/**
 * Abstract DAO implementing basic methods and functions for all entities.
 * 
 * @author o.prinz@binaere-bauten.de
 * 
 * @param <E>
 *            Type of entity.
 */
@Transactional(readOnly = true)
public abstract class AbstractDaoImpl<E extends EntityBase> implements AbstractDao<E>
{
	
	private static final Logger log = LoggerFactory.getLogger(AbstractDaoImpl.class);
	
	private SessionFactory sessionFactory;	
	
	public AbstractDaoImpl(ApplicationContext context)
	{}
	
	protected SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	
	public void setSessionFactory(final SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
		
	
	protected Class<?> getGenericClassType()
	{
		ParameterizedType superclass = (ParameterizedType) getClass().getGenericSuperclass();
		return (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
	}
	
	@Transactional(readOnly = false)
	protected long create(E entity)
	{
		final Session session = sessionFactory.getCurrentSession();
		
		setCreationUpdateDate(entity);
		session.save(entity);
		session.flush();
		log.debug("created Obj {}", entity);
		
		return entity.getId();
	}
	
	@SuppressWarnings("unchecked")
	public E retrieve(long id)
	{
		final Session session = sessionFactory.getCurrentSession();
		return (E) session.get(getGenericClassType(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> retrieveAll()
	{
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(getGenericClassType());
		return (List<E>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<E> retrieveAllSinceDate(Date date)
	{
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(getGenericClassType());
		criteria.add(Restrictions.eq("lastUpdated", date));
		return (List<E>) criteria.list();
	};
	
	@Transactional(readOnly = false)
	public void update(E entity)
	{
		final Session session = sessionFactory.getCurrentSession();
		entity.setLastUpdated(new Date());
		session.update(entity);
		session.flush(); // force update of entity
		log.debug("update finished");		
		
	}
	
	@Override
	public void delete(long id)
	{
		E entity = retrieve(id);
		delete(entity);
	}
	
	@Transactional(readOnly = false)
	public void delete(E entity)
	{
		final Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
		
	}
	
	// @Transactional(readOnly = false)
	// public void indexing(Class c) {
	// final Session session = sessionFactory.getCurrentSession();
	// FullTextSession fullTextSession = Search.getFullTextSession(session);
	// fullTextSession.setFlushMode(FlushMode.MANUAL);
	// fullTextSession.setCacheMode(CacheMode.IGNORE);
	// ScrollableResults results =
	// session.createCriteria(c).scroll(ScrollMode.FORWARD_ONLY);
	//
	// int index = 0;
	// // Removes all Incidents from Index use with care
	// fullTextSession.purgeAll(Incident.class);
	// while (results.next()) {
	// index++;
	// fullTextSession.index(results.get(0));
	// if (index % 100 == 0) {
	// fullTextSession.flushToIndexes();
	// fullTextSession.clear();
	// }
	// }
	// }
	
	public long countEntitys()
	{
		final Session session = sessionFactory.getCurrentSession();
		Long value = (Long) session.createQuery("select count(*) from " + getGenericClassType().getSimpleName())
				.uniqueResult();
		return value == null ? 0 : value;
	}
	
	protected void setCreationUpdateDate(EntityBase entity)
	{
		Date timeStamp = new Date();
		entity.setCreationDate(timeStamp);
		entity.setLastUpdated(timeStamp);
	}
}
