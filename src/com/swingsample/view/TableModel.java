package com.swingsample.view;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings({ "serial" })
public abstract class TableModel<E> extends AbstractTableModel {

	private int limit = 25;

	public abstract void load(Long page);

	public abstract Long getTotalPages();
	
	public abstract Long getTotalItens();

	public Long getTotalPages(Long count) {
		return (long) Math.ceil(count / Double.parseDouble(String.valueOf(getLimit())));
	}

	public abstract E get(int rowIndex);

	protected Integer getLimit() {
		return limit;
	}

	protected Integer getOffset(Long page) {
		return (int) (limit * (page - 1));
	}

}
