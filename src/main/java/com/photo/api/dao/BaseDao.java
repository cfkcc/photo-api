package com.photo.api.dao;

import java.util.Collection;
import java.util.List;

public interface BaseDao<T> {
	
	T findOne(String statement);
	
	T findOne(Object parameter);
	
	T findOne(String statement, Object parameter);
	
	
	Object find(Object parameter);
	
	Object find(String statement, Object parameter);
	
	Collection<T> findList();
	
	Collection<T> findList(Object obj);
	
	Collection<T> findList(String statement);
	
	Collection<T> findList(String statement, Object obj);
	
	int add(T t);
	
	int add(String statement, T t);
	
	int addBatch(Collection<T> t);
	
	int addBatch(String statement, Collection<T> t);
	
	int update(T t);
	
	int update(String statement, T t);
	
	int updateBatch(Collection<T> t);
	
	int updateBatch(String statement, Collection<T> t);
	
	int delete(Object[] parameter);
	
	int delete(String statement, Object[] parameter);
	
	int deleteList(Collection<T> t);
	
	int deleteList(String statement, Collection<T> t);

}
