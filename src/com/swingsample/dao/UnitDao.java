package com.swingsample.dao;

import com.swingsample.entity.Unit;

public abstract class UnitDao extends Dao<Unit> {

	public UnitDao() {
		super(Unit.class);
	}

}
