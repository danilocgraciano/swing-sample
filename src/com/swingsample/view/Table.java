package com.swingsample.view;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

@SuppressWarnings({ "serial", "rawtypes" })
public class Table extends JTable {

	private TableModel model;

	public Table(TableModel model) {
		super(model);
		this.model = model;
	}

	public void load(Long page) {
		this.model.load(page);
	}

	public Long getTotalPages() {
		return this.model.getTotalPages();
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
	
	public Object get(int rowIndex){
		return this.model.get(rowIndex);
	}

	public void setColumnsWidth(int tablePreferredWidth, double... percentages) {
		double total = 0;
		for (int i = 0; i < this.getColumnModel().getColumnCount(); i++) {
			total += percentages[i];
		}

		for (int i = 0; i < this.getColumnModel().getColumnCount(); i++) {
			TableColumn column = this.getColumnModel().getColumn(i);
			column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
		}
	}

}
