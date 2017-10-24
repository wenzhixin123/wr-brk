package com.sinotrans.framework.core.support.hibernate;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.Dialect;
import org.hibernate.impl.SessionImpl;
import org.hibernate.type.Type;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;

import com.sinotrans.framework.core.model.BaseModel;
import com.sinotrans.framework.core.query.BaseQueryItem;
import com.sinotrans.framework.core.support.CustomBeanWrapper;
import com.sinotrans.framework.core.support.PagingInfo;
import com.sinotrans.framework.core.util.EntityUtils;
import com.sinotrans.framework.core.util.ParameterUtils;
import com.sinotrans.framework.core.util.SqlUtils;

/**
 * Helper class that simplifies Hibernate data access code. 
 * @author cj
 *
 */
public class CustomHibernateTemplate extends HibernateTemplate {

	/**
	 * Create a new CustomHibernateTemplate instance.
	 */
	public CustomHibernateTemplate() {
	}

	/**
	 * Create a new CustomHibernateTemplate instance.
	 * @param sessionFactory SessionFactory to create Sessions
	 */
	public CustomHibernateTemplate(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * Create a new CustomHibernateTemplate instance.
	 * @param sessionFactory SessionFactory to create Sessions
	 * @param allowCreate if a non-transactional Session should be created when no
	 * transactional Session can be found for the current thread
	 */
	public CustomHibernateTemplate(SessionFactory sessionFactory, boolean allowCreate) {
		super(sessionFactory, allowCreate);
	}
	
	
	/**
	 * Get objects count of a particular type.
	 * 
	 * @param modelClass
	 * @return
	 * @throws DataAccessException
	 */
	public int getRowCount(final Class entityClass) throws DataAccessException {
		return (Integer) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(entityClass);
			    criteria.setProjection(Projections.rowCount());
			    return criteria.uniqueResult();
			}
		});
	}
	
	/**
	 * get all objects of a particular type.
	 * 
	 * @param modelClass
	 * @param orderBy
	 * @param pagingInfo
	 * @return
	 * @throws DataAccessException
	 */
	public List loadAll(final Class entityClass,
			final String orderBy, final PagingInfo pagingInfo) throws DataAccessException {
		return (List) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(entityClass);
				prepareCriteria(criteria);
				if (pagingInfo != null) {
					criteria.setProjection(Projections.rowCount());
					pagingInfo.setTotalRows((Integer) criteria.uniqueResult());
					criteria.setProjection(null);
					setPagingInfo(criteria, pagingInfo);
				}
				addOrderBy(criteria, orderBy);
				return criteria.list();
			}
		});
	}


	public int getRowCountBySqlCondition(final Class entityClass,
			final String sqlCondition, final Object[] parameterValues) throws DataAccessException {
		return (Integer) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(entityClass);
				
				addSqlCondtion(session, criteria, sqlCondition, parameterValues);
				
			    criteria.setProjection(Projections.rowCount());
			    return criteria.uniqueResult();
			}
		});
	}
	
	public List findBySqlCondition(final Class entityClass,
			final String sqlCondition, final Object[] parameterValues,
			final String orderBy, final PagingInfo pagingInfo) throws DataAccessException {
		return (List) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(entityClass);
				
				addSqlCondtion(session, criteria, sqlCondition, parameterValues);
				
				prepareCriteria(criteria);
				if (pagingInfo != null) {
					criteria.setProjection(Projections.rowCount());
					pagingInfo.setTotalRows((Integer) criteria.uniqueResult());
					criteria.setProjection(null);
					setPagingInfo(criteria, pagingInfo);
				}
				addOrderBy(criteria, orderBy);
				return criteria.list();
			}
		});
	}

	/**
	 * Get objects count by an example model.
	 * 
	 * @param entityName
	 * @param exampleEntity
	 * @return
	 * @throws DataAccessException
	 */
	public int getRowCountByExample(final String entityName, final Object exampleEntity,
			final String sqlCondition, final Object[] parameterValues) throws DataAccessException {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		return (Integer) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = entityName != null ?
						session.createCriteria(entityName) :
							session.createCriteria(exampleEntity.getClass());
				
				addExample(criteria, exampleEntity);
				
				addSqlCondtion(session, criteria, sqlCondition, parameterValues);
			    
				criteria.setProjection(Projections.rowCount());
			    return criteria.uniqueResult();
			}
		});
	}
	
	/**
	 * Find objects by an example model.
	 * 
	 * @param entityName
	 * @param exampleEntity
	 * @param sqlCondition
	 * @param parameterValues
	 * @param orderBy
	 * @param pagingInfo
	 * @return
	 * @throws DataAccessException
	 */
	public List findByExample(final String entityName, final Object exampleEntity,
			final String sqlCondition, final Object[] parameterValues,
			final String orderBy, final PagingInfo pagingInfo) throws DataAccessException {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		return (List) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Criteria criteria = entityName != null ?
						session.createCriteria(entityName) :
							session.createCriteria(exampleEntity.getClass());
				
				addExample(criteria, exampleEntity);
				
				addSqlCondtion(session, criteria, sqlCondition, parameterValues);
				
				prepareCriteria(criteria);
				if (pagingInfo != null) {
					criteria.setProjection(Projections.rowCount());
					pagingInfo.setTotalRows((Integer) criteria.uniqueResult());
					criteria.setProjection(null);
					setPagingInfo(criteria, pagingInfo);
				}
				addOrderBy(criteria, orderBy);
				return criteria.list();
			}
		});
	}

	/**
	 * Merge entities.
	 * 
	 * @param entityName
	 * @param entities
	 * @return
	 * @throws DataAccessException
	 */
	public List mergeAll(final String entityName, final Collection entities) throws DataAccessException {
		return (List) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				List result = new ArrayList();
				for (Iterator it = entities.iterator(); it.hasNext();) {
					result.add(session.merge(entityName, it.next()));
				}
				return result;
			}
		});
	}

	/**
	 * Delete entities
	 * 
	 * @param entityName
	 * @param entities
	 * @throws DataAccessException
	 */
	public void deleteAll(final String entityName, final Collection entities) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				for (Iterator it = entities.iterator(); it.hasNext();) {
					session.delete(entityName, it.next());
				}
				return null;
			}
		});
	}

	/**
	 * Get row count by a sql query.
	 * 
	 * @param sql
	 * @param valueBean
	 * @return
	 * @throws DataAccessException
	 */
	public int getRowCountBySqlQueryAndValueBean(final String sql, final Object valueBean,
			final String extraSqlCondition, final Object[] parameterValues) throws DataAccessException {
		return (Integer) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {

				String sqlCondition = convertOrdinalParametersToNamedParameters(extraSqlCondition);
				String runSql = SqlUtils.addExtraConditions(sql, sqlCondition);
				runSql = SqlUtils.addDataFilterConditions(runSql);

				runSql = "select count(*) as COUNT__ from (" + runSql + ") T__COUNT__";
				SQLQuery queryObject = session.createSQLQuery(runSql);
				if (parameterValues != null) {
					queryObject.setProperties(convertOrdinalParametersToNamedParameters(parameterValues));
				}
				queryObject.setProperties(convertSqlParameterValueBeanToNamedParameters(valueBean));
				queryObject.addScalar("COUNT__", Hibernate.INTEGER);
			    return queryObject.uniqueResult();
			}
		});
	}
	
	/**
	 * Get objects by a sql query.
	 * 
	 * @param sql
	 * @param valueBean
	 * @param resultEntityClass
	 * @param pagingInfo
	 * @return
	 * @throws DataAccessException
	 */
	public List findBySqlQueryAndValueBean(final String sql, final Object valueBean,
			final String extraSqlCondition, final Object[] parameterValues,
			final Class resultEntityClass, final String orderBy, final PagingInfo pagingInfo) throws DataAccessException {
		return (List) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {

				String sqlCondition = convertOrdinalParametersToNamedParameters(extraSqlCondition);
				String runSql = SqlUtils.addExtraConditions(sql, sqlCondition);
				runSql = SqlUtils.addDataFilterConditions(runSql);

				runSql = addOrderBy(runSql, orderBy);
				Dialect dialect = ((SessionImpl) session).getFactory().getDialect();
				String selectGuidString = dialect.getSelectGUIDString();
				selectGuidString = selectGuidString.replace("select ", "");
				selectGuidString = selectGuidString.replace(" from dual", "");
				runSql = "select T__UUID__.*, " + selectGuidString + " as UUID__ from (" + runSql + ") T__UUID__";
				SQLQuery queryObject = session.createSQLQuery(runSql);
				prepareQuery(queryObject);
				if (parameterValues != null) {
					queryObject.setProperties(convertOrdinalParametersToNamedParameters(parameterValues));
				}
				queryObject.setProperties(convertSqlParameterValueBeanToNamedParameters(valueBean));
				queryObject.addEntity(resultEntityClass);
				if (pagingInfo != null) {
					pagingInfo.setTotalRows(getRowCountBySqlQueryAndValueBean(runSql, valueBean, null, parameterValues));
					setPagingInfo(queryObject, pagingInfo);
				}
				List result = queryObject.list();
				for (int i = 0; i < result.size(); i++) {
					Object resultItem = result.get(i);
					if (resultItem instanceof BaseQueryItem) {
						((BaseQueryItem) resultItem).setRownum(i + 1);
					}
				}
				return result;
			}
		});
	}

	public int getRowCountByNamedSqlQueryAndValueBean(String queryName, Object valueBean,
			String extraSqlCondition, Object[] parameterValues) throws DataAccessException {
		String sql = SqlUtils.getDynamicNamedSql(queryName, valueBean);
		return getRowCountBySqlQueryAndValueBean(sql, valueBean, extraSqlCondition, parameterValues);
	}
	
	public List findByNamedSqlQueryAndValueBean(String queryName, Object valueBean,
			String extraSqlCondition, Object[] parameterValues,
			Class resultEntityClass, String orderBy, PagingInfo pagingInfo) throws DataAccessException {
		String sql = SqlUtils.getDynamicNamedSql(queryName, valueBean);
		return findBySqlQueryAndValueBean(sql, valueBean, extraSqlCondition, parameterValues, resultEntityClass, orderBy, pagingInfo);
	}
	
	public int updateBySqlAndValueBean(final String sql, final Object valueBean) throws DataAccessException {
		return (Integer) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				SQLQuery queryObject = session.createSQLQuery(sql);
				prepareQuery(queryObject);
				queryObject.setProperties(valueBean);
				return queryObject.executeUpdate();
			}
		});
	}

	public int updateByNamedSqlAndValueBean(String updateName, Object valueBean) throws DataAccessException {
		String sql = SqlUtils.getNamedSql(updateName);
		return updateBySqlAndValueBean(sql, valueBean);
	}
	
	public long getSequenceValue(final String sequenceName) throws DataAccessException {
		return (Long) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Dialect dialect = ((SessionImpl) session).getFactory().getDialect();
				String sql = dialect.getSequenceNextValString(sequenceName);
				int fromIndex = sql.indexOf(" from ");
				if (fromIndex == -1) {
					sql = sql + " as SEQ__";
				} else {
					sql = sql.substring(0, fromIndex) + " as SEQ__" + sql.substring(fromIndex);
				}
				SQLQuery queryObject = session.createSQLQuery(sql);
				prepareQuery(queryObject);
				queryObject.addScalar("SEQ__", Hibernate.LONG);
				return queryObject.uniqueResult();
			}
		});
	}
	
	public Date getSysDate() throws DataAccessException {
		return (Date) executeWithNativeSession(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Dialect dialect = ((SessionImpl) session).getFactory().getDialect();
				String sql = dialect.getCurrentTimestampSelectString();
				int fromIndex = sql.indexOf(" from ");
				if (fromIndex == -1) {
					sql = sql + " as SYSDATE__";
				} else {
					sql = sql.substring(0, fromIndex) + " as SYSDATE__" + sql.substring(fromIndex);
				}
				SQLQuery queryObject = session.createSQLQuery(sql);
				prepareQuery(queryObject);
				queryObject.addScalar("SYSDATE__", Hibernate.TIMESTAMP);
				return new Date(((Timestamp) queryObject.uniqueResult()).getTime());
			}
		});
	}
	
	private void addExample(Criteria criteria, Object exampleEntity) {
		CustomBeanWrapper exampleWrapper = new CustomBeanWrapper(exampleEntity);
		PropertyDescriptor[] propertyDescriptors = exampleWrapper.getPropertyDescriptors();
		List<String> fieldNames = EntityUtils.getFieldNames((Class<? extends BaseModel>) exampleEntity.getClass());
		for (String fieldName : fieldNames) {
			Object value = exampleWrapper.getPropertyValue(fieldName);
			if (ParameterUtils.isParamValid(value)) {
				criteria.add(Restrictions.eq(fieldName, value));
			}
		}
	}

	private void addSqlCondtion(Session session, Criteria criteria, String sqlCondition, Object[] parameterValues) {
		if (sqlCondition == null || sqlCondition.trim().length() == 0) {
			return;
		}
		if (parameterValues == null || parameterValues.length == 0) {
			criteria.add(Restrictions.sqlRestriction(sqlCondition));
		} else {
			boolean hasArrayParameters = false;
			for (Object value : parameterValues) {
				if (value != null && value.getClass().isArray()) {
					hasArrayParameters = true;
					break;
				}
			}
			if (hasArrayParameters) {
				int[] parameterLocations = SqlUtils.getOrdinalParameterLocations(sqlCondition);
				StringBuilder sqlConditionSb = new StringBuilder(sqlCondition);
				List<Object> parameterValuesList = new ArrayList<Object>();
				for (int i = parameterValues.length - 1; i >= 0; i--) {
					Object value = parameterValues[i];
					if (value != null && value.getClass().isArray()
							&& Array.getLength(value) == 0) {
						value = null;
					}
					if (value != null && value.getClass().isArray()) {
						int size = Array.getLength(value);
						if (size > 1) {
							sqlConditionSb.insert(parameterLocations[i], StringUtils.repeat("?, ", size - 1));
						}
						for (int j = size - 1; j >= 0; j--) {
							parameterValuesList.add(0, Array.get(value, j));
						}
					} else {
						parameterValuesList.add(0, value);
					}
				}
				parameterValues = parameterValuesList.toArray();
				sqlCondition = sqlConditionSb.toString();
			}
			
			Type[] parameterTypes = this.getParameterTypes(parameterValues);
			criteria.add(Restrictions.sqlRestriction(sqlCondition, parameterValues, parameterTypes));
		}
	}
	
	private Type[] getParameterTypes(Object[] parameterValues) {
		Type[] parameterTypes = new Type[parameterValues.length];
		for (int i = 0; i < parameterValues.length; i++) {
			Object value = parameterValues[i];
			if (value instanceof String) {
				parameterTypes[i] = Hibernate.STRING;
			} else if (value instanceof Double) {
				parameterTypes[i] = Hibernate.DOUBLE;
			} else if (value instanceof Integer) {
				parameterTypes[i] = Hibernate.INTEGER;
			} else if (value instanceof Long) {
				parameterTypes[i] = Hibernate.LONG;
			} else if (value instanceof Date) {
				parameterTypes[i] = Hibernate.TIMESTAMP;
			} else {
				parameterTypes[i] = Hibernate.STRING;
			}
		}
		return parameterTypes;
	}
	
	private static final String NAMED_PARAMETER_PREFIX = "$param$";
	
	private String convertOrdinalParametersToNamedParameters(String sql) {
		if (sql == null || sql.trim().length() == 0) {
			return sql;
		}
		int[] ordinalParameterLocations = SqlUtils.getOrdinalParameterLocations(sql);
		StringBuilder resultSql = new StringBuilder(sql);
		for (int i = ordinalParameterLocations.length - 1; i >= 0; i--) {
			int location = ordinalParameterLocations[i];
			resultSql.delete(location, location + 1);
			resultSql.insert(location, ":" + NAMED_PARAMETER_PREFIX + i);
		}
		return resultSql.toString();		
	}
	
	private Map<String, Object> convertOrdinalParametersToNamedParameters(Object[] parameterValues) {
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		for (int i = 0; i < parameterValues.length; i++) {
			Object value = parameterValues[i];
			if (value == null) {
				value = "";
			}
			if (value.getClass().isArray()) {
				List<Object> notNullArrayItems = new ArrayList<Object>();
				for (int j = 0; j < Array.getLength(value); j++) {
					Object itemValue = Array.get(value, j);
					if (itemValue != null) {
						notNullArrayItems.add(itemValue);
					}
				}
				value = notNullArrayItems.toArray();
				if (Array.getLength(value) == 0) {
					value = "";
				}
			}
			namedParameters.put(NAMED_PARAMETER_PREFIX + i, value);
		}
		return namedParameters;
	}
	
	private Map<String, Object> convertSqlParameterValueBeanToNamedParameters(Object valueBean) {
		CustomBeanWrapper beanWrapper = new CustomBeanWrapper(valueBean);
		PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String propertyName = propertyDescriptor.getName();
			if ("class".equals(propertyName)) {
				continue;
			}
			Object value = beanWrapper.getPropertyValue(propertyName);
			if (value == null) {
				value = "";
			}
			if (value.getClass().isArray()) {
				List<Object> notNullArrayItems = new ArrayList<Object>();
				for (int i = 0; i < Array.getLength(value); i++) {
					Object itemValue = Array.get(value, i);
					if (itemValue != null) {
						notNullArrayItems.add(itemValue);
					}
				}
				value = notNullArrayItems.toArray();
				if (Array.getLength(value) == 0) {
					value = "";
				}
			}
			namedParameters.put(propertyName, value);
		}
		return namedParameters;
	}
	
	private void addOrderBy(Criteria criteria, String orderBy) {
		if (orderBy != null && orderBy.trim().length() != 0) {
			Order[] orders = SqlUtils.parseOrderByToHibernateOrders(orderBy);
			for (Order order : orders) {
				criteria.addOrder(order);
			}
		}
	}
	
	private String addOrderBy(String sql, String orderBy) {
		if (orderBy != null && orderBy.trim().length() != 0) {
			return "select * from (" + sql + ") T__ORDER__ order by " + SqlUtils.parseOrderByToSqlStyle(orderBy);
		} else {
			return sql;
		}
	}
	
	private void setPagingInfo(Criteria criteria, PagingInfo pagingInfo) {
		if (pagingInfo != null) {
			if (pagingInfo.getPageSize() <= 0) {
				pagingInfo.setPageSize(10);
			}
			if (pagingInfo.getCurrentPage() > pagingInfo.getTotalPages()) {
				pagingInfo.setCurrentPage(pagingInfo.getTotalPages());
			}
			if (pagingInfo.getCurrentPage() <= 0) {
				pagingInfo.setCurrentPage(1);
			}
			criteria.setFirstResult(pagingInfo.getCurrentRow());
			criteria.setMaxResults(pagingInfo.getPageSize());
		}
	}
	
	private void setPagingInfo(Query queryObject, PagingInfo pagingInfo) {
		if (pagingInfo != null) {
			if (pagingInfo.getPageSize() <= 0) {
				pagingInfo.setPageSize(10);
			}
			if (pagingInfo.getCurrentPage() > pagingInfo.getTotalPages()) {
				pagingInfo.setCurrentPage(pagingInfo.getTotalPages());
			}
			if (pagingInfo.getCurrentPage() <= 0) {
				pagingInfo.setCurrentPage(1);
			}
			queryObject.setFirstResult(pagingInfo.getCurrentRow());
			queryObject.setMaxResults(pagingInfo.getPageSize());
		}
	}
	
}
