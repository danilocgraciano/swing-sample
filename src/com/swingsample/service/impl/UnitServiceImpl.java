package com.swingsample.service.impl;

import java.util.List;

import com.swingsample.dao.UnitDao;
import com.swingsample.dao.impl.UnitDaoImpl;
import com.swingsample.entity.Unit;
import com.swingsample.service.UnitService;

public class UnitServiceImpl implements UnitService {

	private UnitDao dao;

	public UnitServiceImpl() {
		dao = new UnitDaoImpl();
	}

	@Override
	public void saveOrUpdate(Unit e) {
		dao.saveOrUpdate(e);
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
	}

	@Override
	public Unit findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Unit> findAll(Integer limit, Integer offset) {
		return dao.findAll(limit, offset);
	}

	@Override
	public Long count() {
		return dao.count();
	}

}
