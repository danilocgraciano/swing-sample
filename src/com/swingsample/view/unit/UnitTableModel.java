package com.swingsample.view.unit;

import java.util.ArrayList;
import java.util.List;

import com.swingsample.entity.Unit;
import com.swingsample.service.UnitService;
import com.swingsample.service.impl.UnitServiceImpl;
import com.swingsample.view.TableModel;

@SuppressWarnings("serial")
public class UnitTableModel extends TableModel<Unit> {

	private static final int ID = 0;
	private static final int DESCRIPTION = 1;

	private List<Unit> data;
	private String[] columns = { "ID", "Descrição" };

	private UnitService service;

	public UnitTableModel() {
		service = new UnitServiceImpl();
		data = new ArrayList<>();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	public void addRow(Unit... units) {

		for (Unit unit : units) {
			this.data.add(unit);
		}

		this.fireTableDataChanged();
	}

	public void removeAllRows() {
		this.data.clear();
	}

	public void removeRow(int rowIndex) {
		this.data.remove(rowIndex);
		this.fireTableRowsDeleted(rowIndex, rowIndex);
	}

	public Unit get(int rowIndex) {
		return this.data.get(rowIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		// switch (columnIndex) {
		// case ID:
		// return false;
		// case DESCRIPTION:
		// return true;
		// }
		return false;
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

	public void load(Long page) {

		List<Unit> aux = service.findAll(getLimit(), getOffset(page));
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
	public Class<?> getColumnClass(int columnIndex) {
		if (data.isEmpty()) {
			return Object.class;
		}
		return getValueAt(0, columnIndex).getClass();
	}

}