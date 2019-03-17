package com.swingsample.view;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class Table extends JTable {

	private TableModel model;

	public Table(TableModel model) {
		super(model);
		this.model = model;
		this.getTableHeader().setReorderingAllowed(false);
	}

	public void load(Long page, int orderColumn, String order) {
		this.model.load(page, orderColumn, order);
	}

	public Long getTotalPages(Long totalItens) {
		return this.model.getTotalPages(totalItens);
	}

	public Long getTotalItens() {
		return this.model.getTotalItens();
	}

	public Integer getOffset(Long page) {
		return this.model.getOffset(page);
	}

	public Integer getPageSize() {
		return this.model.getLimit();
	}

	public Object get(int rowIndex) {
		return this.model.get(rowIndex);
	}

	public void setColumnsWidth(int tablePreferredWidth) {

		List<OrderColumn> columns = this.model.getColumns();
		double total = 0;
		for (OrderColumn orderColumn : columns) {
			total += orderColumn.getSize();
		}
		for (int i = 0; i < this.getColumnModel().getColumnCount(); i++) {
			TableColumn column = this.getColumnModel().getColumn(i);
			column.setPreferredWidth((int) (tablePreferredWidth * (columns.get(i).getSize() / total)));
		}
	}

}
