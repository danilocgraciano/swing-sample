package com.swingsample.view.unit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.swingsample.entity.Unit;
import com.swingsample.service.UnitService;
import com.swingsample.service.impl.UnitServiceImpl;
import com.swingsample.view.OrderColumn;
import com.swingsample.view.TableModel;

@SuppressWarnings("serial")
public class UnitTableModel extends TableModel<Unit> {

	private static final int ID = 0;
	private static final int DESCRIPTION = 1;

	private List<Unit> data;
	private List<OrderColumn> columns;

	private UnitService service;

	private OrderColumn orderedColumn;

	public UnitTableModel() {
		service = new UnitServiceImpl();
		data = new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public void addRow(Unit... units) {

		for (Unit unit : units) {
			this.data.add(unit);
		}

		this.fireTableDataChanged();
	}

	@Override
	public void removeAllRows() {
		this.data.clear();
	}

	@Override
	public void removeRow(int rowIndex) {
		this.data.remove(rowIndex);
		this.fireTableRowsDeleted(rowIndex, rowIndex);
	}

	@Override
	public Unit get(int rowIndex) {
		return this.data.get(rowIndex);
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (aValue == null)
			return;

		switch (columnIndex) {
		case DESCRIPTION:
			data.get(rowIndex).setDescription((String) aValue);
			break;
		}
		this.fireTableRowsUpdated(rowIndex, rowIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case ID:
			return data.get(rowIndex).getId();
		case DESCRIPTION:
			return data.get(rowIndex).getDescription();
		}
		return null;
	}

	@Override
	public void load(Long page, int orderColumn, String order) {

		List<OrderColumn> orderedColumns = new ArrayList<>();
		if (orderColumn != -1) {
			orderedColumn = getOrderedColumn(orderColumn, order);
			orderedColumns.add(orderedColumn);
		}
		List<Unit> aux = service.findAll(getLimit(), getOffset(page), orderedColumns);
		removeAllRows();
		addRow(aux.toArray(new Unit[aux.size()]));
	}

	@Override
	public Long getTotalPages() {
		return getTotalPages(getTotalItens());
	}

	@Override
	public Long getTotalItens() {
		return service.count();
	}

	@Override
	public List<OrderColumn> getColumns() {
		if (columns == null) {
			columns = new LinkedList<>();
			columns.add(new OrderColumn("#", "id", Long.class, false, 10));

			OrderColumn description = new OrderColumn("Descrição", "description", String.class, false, 90);
			description.setOrder(OrderColumn.ASC);
			columns.add(description);
		}
		return columns;
	}

}