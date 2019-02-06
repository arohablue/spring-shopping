package com.lti.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Component
@Repository
public class GenericRepository {

	@PersistenceContext
	protected EntityManager entityManager;

	@Transactional
	public Object store(Object ob) {
		entityManager.persist(ob);
		return ob;
	}

	@Transactional
	public <E> E fetchById(Class<E> classname, Object pk) {
			E e = entityManager.find(classname, pk);
			return e ;
	}
	
	@Transactional
	public <E> E fetchAll(Class<E> classname, Object pk) {
		return (E) entityManager.createQuery("select c from CarPart as c", classname).getResultList();
	}
}
