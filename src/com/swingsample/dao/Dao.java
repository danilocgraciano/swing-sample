package com.swingsample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.swingsample.entity.BaseEntity;

@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class Dao<T extends BaseEntity> {

	private static EntityManager manager = ConnectionFactory.getEntityManager();

	private Class clazz;

	public Dao(Class clazz) {
		this.clazz = clazz;
	}

	public T findById(Long id) {
		return (T) manager.find(clazz, id);
	}

	public void saveOrUpdate(T obj) {
		try {
			manager.getTransaction().begin();
			if (obj.getId() == null) {
				manager.persist(obj);
			} else {
				manager.merge(obj);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public void remove(Long id) {
		T t = findById(id);
		try {
			manager.getTransaction().begin();
			manager.remove(t);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
		}
	}

	public List<T> findAll(Integer limit, Integer offset) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
		Root<T> rootQuery = criteriaQuery.from(clazz);
		CriteriaQuery<T> all = criteriaQuery.select(rootQuery);
		TypedQuery<T> allQuery = manager.createQuery(all);

		if (limit != null && offset != null) {
			allQuery.setMaxResults(limit);
			allQuery.setFirstResult(offset);
		}

		return allQuery.getResultList();
	}

	public Long count() {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		criteriaQuery.select(builder.count(criteriaQuery.from(clazz)));
		return manager.createQuery(criteriaQuery).getSingleResult();
	}

}
