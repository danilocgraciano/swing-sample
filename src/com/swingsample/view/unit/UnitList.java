package com.swingsample.view.unit;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.swingsample.view.Table;

@SuppressWarnings("serial")
public class UnitList extends JPanel {

	private JScrollPane scrollPane;
	private Table table;

	private JLabel lblTitle;

	private JPanel buttonPanel;
	private JButton btnNew;
	private JButton btnEdit;

	private TablePaginator navigationPanel;

	/**
	 * Create the panel.
	 */
	public UnitList(Window parent) {
		setBounds(0, 30, parent.getWidth(), parent.getHeight());
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(getLblTitle());
		add(getPanActions());
		add(getJScrollPane());
		add(getNavigationPanel());

	}

	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("Cadastro de Unidades");
			lblTitle.setAlignmentX(CENTER_ALIGNMENT);
			lblTitle.setAlignmentY(CENTER_ALIGNMENT);
		}
		return lblTitle;
	}

	private Table getTable() {
		if (table == null) {
			table = new Table(new UnitTableModel());
			table.setColumnsWidth(getWidth(), 10, 90);
		}
		return table;
	}

	private JScrollPane getJScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()));
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}

	private JPanel getPanActions() {
		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			buttonPanel.add(getBtnNew());
			buttonPanel.add(getBtnEdit());

		}
		return buttonPanel;
	}

	private JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton("Novo");
		}
		return btnNew;
	}

	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("Editar");
		}
		return btnEdit;
	}

	private JPanel getNavigationPanel() {
		if (navigationPanel == null) {
			navigationPanel = new TablePaginator(getTable());
		}
		return navigationPanel;
	}

}
