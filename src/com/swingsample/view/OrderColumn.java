package com.swingsample.view;

@SuppressWarnings("rawtypes")
public class OrderColumn {

	public static final String ASC = "ASC";
	public static final String DESC = "DESC";

	private String name;

	private String title;

	private Class columnClass;

	private boolean editable;

	private String order;

	public OrderColumn(String title, String name, Class columnClass, boolean editable) {
		this.name = name;
		this.title = title;
		this.columnClass = columnClass;
		this.editable = editable;
	}

	public String getName() {
		return name;
	}

	public Class getColumnClass() {
		return this.columnClass;
	}

	public boolean isEditable() {
		return editable;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getTitle() {
		return title;
	}

}
