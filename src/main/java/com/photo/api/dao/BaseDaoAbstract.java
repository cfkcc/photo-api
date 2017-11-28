package com.photo.api.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import com.photo.api.common.constant.CommonConsts;


public abstract class BaseDaoAbstract<T> implements BaseDao<T> {
	@Resource(name="sqlSession")
	private SqlSession sqlSession;
	private final static String POSTFIX = "Mapper";
	private final static String CONTAIN_DAO = "Dao";
	
	
	protected String getDefaultSqlNamespace() {
		Class<?> genericClass = null;
		Type t = this.getClass().getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			genericClass = (Class<?>) p[0];
		}
		String simpleName = genericClass.getSimpleName();
		if (StringUtils.isNotBlank(simpleName)) {
			simpleName = simpleName.substring(0, simpleName.indexOf(CONTAIN_DAO)==-1?simpleName.length():simpleName.indexOf(CONTAIN_DAO));
		}
		return simpleName+POSTFIX+".";
	}


	
	public T findOne(String statement) {
		return sqlSession.selectOne(getDefaultSqlNamespace()+statement);
	}
	
	
	public T findOne(Object parameter) {
		return findOne(CommonConsts.FIND_BY_ID, parameter);
	}

	
	public T findOne(String statement, Object parameter) {
		return sqlSession.selectOne(getDefaultSqlNamespace()+statement, parameter);
	}

	
	public Object find(Object parameter) {
		return find(CommonConsts.FIND,parameter);
	}

	
	public Object find(String statement, Object parameter) {
		return sqlSession.selectOne(getDefaultSqlNamespace()+statement, parameter);
	}

	
	public Collection<T> findList() {
		return findList(CommonConsts.FINDLIST);
	}
	
	
	public Collection<T> findList(Object parameter) {
		return findList(CommonConsts.FINDLIST, parameter);
	}
	
	
	public Collection<T> findList(String statement) {
		return sqlSession.selectList(getDefaultSqlNamespace()+statement);
	}

	
	public Collection<T> findList(String statement, Object parameter) {
		return sqlSession.selectList(getDefaultSqlNamespace()+statement, parameter);
	}

	
	public int add(T t) {
		return add(CommonConsts.ADD, t);
	}

	
	public int add(String statement, T t) {
		return sqlSession.insert(getDefaultSqlNamespace()+statement, t);
	}

	
	public int addBatch(Collection<T> t) {
		return addBatch(CommonConsts.ADDBATCH,t);
	}

	
	public int addBatch(String statement, Collection<T> t) {
		return sqlSession.insert(getDefaultSqlNamespace()+statement, t);
	}

	
	public int update(T t) {
		return update(CommonConsts.UPDATE, t);
	}

	
	public int update(String statement, T t) {
		return sqlSession.update(getDefaultSqlNamespace()+statement, t);
	}

	
	public int updateBatch(Collection<T> t) {
		return updateBatch(CommonConsts.UPDATEBATCH, t);
	}

	
	public int updateBatch(String statement, Collection<T> t) {
		return sqlSession.update(getDefaultSqlNamespace()+statement, t);
	}

	
	public int delete(Object[] parameter) {
		return delete(CommonConsts.DELETE, parameter);
	}

	
	public int delete(String statement, Object[] parameter) {
		return sqlSession.delete(getDefaultSqlNamespace()+statement, parameter);
	}
	
	public int deleteList(Collection<T> t) {
		return deleteList(CommonConsts.DELETELIST, t);
	}
	
	
	public int deleteList(String statement, Collection<T> t) {
		return sqlSession.delete(getDefaultSqlNamespace()+statement, t);
	}
}
