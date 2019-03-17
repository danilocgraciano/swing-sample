package com.swingsample.service;

import java.util.List;

import com.swingsample.view.OrderColumn;

public interface BaseService<E> {

	void saveOrUpdate(E e);

	void remove(Long id);

	E findById(Long id);

	List<E> findAll(Integer limit, Integer offset);

	List<E> findAll(Integer limit, Integer offset, List<OrderColumn> listOrder);

	Long count();

}
