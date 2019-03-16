package com.swingsample.view.unit;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.swingsample.entity.Unit;
import com.swingsample.service.UnitService;
import com.swingsample.service.impl.UnitServiceImpl;
import com.swingsample.view.FormListener;
import com.swingsample.view.Messages;
import com.swingsample.view.Table;
import com.swingsample.view.TablePaginator;

@SuppressWarnings("serial")
public class UnitList extends JPanel {

	private JScrollPane scrollPane;
	private Table table;

	private JLabel lblTitle;

	private JPanel buttonPanel;
	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnDelete;

	private TablePaginator navigationPanel;
	private Window parent;

	private UnitService service;

	/**
	 * Create the panel.
	 */
	public UnitList(Window parent) {
		this.parent = parent;
		this.service = new UnitServiceImpl();
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
			table.setAutoCreateRowSorter(true);
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
			buttonPanel.add(getBtnDelete());

		}
		return buttonPanel;
	}

	private JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton("Novo");
			btnNew.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					openForm("Nova Unidade", new Unit());
				}
			});
		}
		return btnNew;
	}

	private JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("Editar");
			btnEdit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int indexRow = getTable().getSelectedRow();
					if (indexRow == -1) {
						Messages.error(parent, "Atenção", "Selecione o item que deseja alterar.");
						return;
					}
					openForm("Editar Unidade", (Unit) getTable().get(indexRow));
				}
			});
		}
		return btnEdit;
	}

	private JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Deletar");
			btnDelete.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int indexRow = getTable().getSelectedRow();
					if (indexRow == -1) {
						Messages.error(parent, "Atenção", "Selecione o item que deseja deletar.");
						return;
					}
					if (Messages.confirm(parent, "Atenção", "Deseja realmente deletar?")) {
						Long id = ((Unit) getTable().get(indexRow)).getId();
						UnitList.this.service.remove(id);
						Messages.info(parent, "Sucesso", "Deletado com sucesso!");
						getNavigationPanel().refresh();
					}
				}
			});
		}
		return btnDelete;
	}

	private void openForm(String title, Unit unit) {
		JFrame form = new JFrame(title);
		form.setResizable(false);
		form.setContentPane(new UnitForm(form, unit, new FormListener<Unit>() {

			@Override
			public void onSave(Unit e) {
				UnitList.this.service.saveOrUpdate(e);
				Messages.info(form, "Sucesso", "Salvo com sucesso!");
				getNavigationPanel().refresh();
			}
		}));
		form.pack();
		form.setLocationRelativeTo(null);
		form.setVisible(true);
	}

	private TablePaginator getNavigationPanel() {
		if (navigationPanel == null) {
			navigationPanel = new TablePaginator(getTable());
		}
		return navigationPanel;
	}

}
