package com.swingsample.service;

import java.util.List;

public interface BaseService<E> {

	void saveOrUpdate(E e);

	void remove(Long id);

	E findById(Long id);

	List<E> findAll(Integer limit, Integer offset);

	Long count();

}
