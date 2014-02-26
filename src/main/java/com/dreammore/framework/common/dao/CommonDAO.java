package com.dreammore.framework.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CommonDAO extends HibernateDaoSupport implements ICommonDAO {

	@Override
	public void bulkUpdate(String hql, Object... value) {
		getHibernateTemplate().bulkUpdate(hql, value);
	}

	@Override
	public void clear() {
		getHibernateTemplate().clear();
	
	}

	@Override
	public <T> void delete(T obj) {
		getHibernateTemplate().delete(obj);
	}
	
	@Override
	public <T> void merge(T obj) {
		getHibernateTemplate().merge(obj);
		
	}

	@Override
	public void deleteAll(String hql, Object... value) {
		getHibernateTemplate().deleteAll(find(hql, value));
	}

	@Override
	public void evict(Object obj) {
		getHibernateTemplate().evict(obj);
	}

	@Override
	public <T> void evictList(List<T> list) {
		for (Object object : list) {
			evict(object);
		}
	}

	@Override
	public void executeHQL(String hql, Object[] params) {

		Session session = getSession();
		System.out.println("------------------" + getSessionFactory().getAllCollectionMetadata());
		Query query = session.createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
		query.executeUpdate();
		
		releaseSession(session);
		System.out.println("------------------" + getSessionFactory().getStatistics().getConnectCount());
	}

	@Override
	public void executeSQL(String sql, Object[] params) {
		Session session = getSession();
		System.out.println("------------------" + getSessionFactory().getStatistics().getConnectCount());
		Query query = getSession().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
		query.executeUpdate();
		
		releaseSession(session);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBySQL(String sql, Object[] params) {
		Session session = getSession();
		System.out.println("------------------" + getSessionFactory().getStatistics().getConnectCount());
		Query query = session.createSQLQuery(sql);
		if (params != null) {
			for (int i = 0; i < params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
		List<Object[]> result = query.list();
		releaseSession(session);
		System.out.println("------------------" + getSessionFactory().getStatistics().getConnectCount());
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> findBySQL(String sql, Object[] params, Class<T> entity) {
		Session session = getSession();
		
		Query query = session.createSQLQuery(sql).addEntity(entity);
		if (params != null) {
			for (int i = 0; i < params.length; i++)
				query.setParameter(i, params[i]);
		}
		List<T> result = query.list();
		
		releaseSession(session);
		return result;
	}

	@Override
	public void findBySQL(String sql, @SuppressWarnings("rawtypes") PageBean pageBean) {
		getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(sql, pageBean, true));
	}
	
	@Override
	public void findBySQL(String sql,  Object[] params, @SuppressWarnings("rawtypes") PageBean pageBean) {
		getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(sql, params, pageBean, true));
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> find(String hql) {
		return getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> find(String hql, Object... value) {
		return getHibernateTemplate().find(hql, value);
	}

	@Override
	public void find(String hql, @SuppressWarnings("rawtypes") PageBean pageObj, Object... values) {
		getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(hql, pageObj, values));
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> find(String hql, String[] paramNames, Object[] values) {
		return getHibernateTemplate().findByNamedParam(hql, paramNames, values);
	}

	@Override
	public void find(String hql, @SuppressWarnings("rawtypes") PageBean pageObj, String[] paramNames, Object[] values) {
		getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(hql, pageObj, values, paramNames));
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findTop(String hql, int topNum, Object... value) {
		return getHibernateTemplate().executeFind(new HibernateCallbackByPageImpl(hql, topNum, value));
	}

	@Override
	public void flush() {
		getHibernateTemplate().flush();
	}

	@Override
	public <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public HibernateTemplate getHibertemplate() {
		return getHibernateTemplate();
	}

//	@Override
//	public Session getSessions() {
//		return getSessionFactory().getCurrentSession();
//	}

	@Override
	public void initialize(Object obj) {
		getHibernateTemplate().initialize(obj);
	}

	@Override
	public <T> T load(Class<T> entityClass, Serializable id) {
		return getHibernateTemplate().load(entityClass, id);
	}

	@Override
	public <T> void refresh(T obj) {
		getHibernateTemplate().refresh(obj);
	}

	@Override
	public <T> void save(T obj) {
		getHibernateTemplate().save(obj);
	}

	@Override
	public <T> void update(T obj) {
		getHibernateTemplate().update(obj);
	}

	@Override
	public <T> void saveOrupdate(T obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}
	
	@Override
	public <T> void saveOrUpdateAll(Collection<T> collection) {
		getHibernateTemplate().saveOrUpdateAll(collection);
		
	}

	@Override
	public void execute(HibernateCallback<?> action) {
		getHibernateTemplate().execute(action);
		
	}


	
}
