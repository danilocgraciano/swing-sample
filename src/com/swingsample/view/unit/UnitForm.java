package com.swingsample.view.unit;

import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.swingsample.entity.Unit;
import com.swingsample.view.FormListener;

@SuppressWarnings("serial")
public class UnitForm extends JPanel {

	private Unit unit;
	private JLabel lblTitle = null;
	private JTextField txtDescription = null;
	private JLabel lblDescription = null;
	private JPanel panBottom = null;
	private JButton btnSave = null;
	private JButton btnExit = null;
	private Window parent;
	private FormListener<Unit> listener;

	/**
	 * Create the panel.
	 */
	public UnitForm(Window parent, Unit unit, FormListener<Unit> listener) {

		this.parent = parent;
		this.unit = unit;
		this.listener = listener;

		if (this.unit == null)
			throw new IllegalArgumentException("Unit cannot be null");

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalStrut(10));
		add(getLblTitle());
		add(Box.createVerticalStrut(20));

		JPanel panDescription = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panDescription.add(getLblDescription());
		panDescription.add(getTxtDescription());
		add(panDescription);
		add(Box.createVerticalStrut(20));

		add(getPanBottom());

		refresh(this.unit);

	}

	private JLabel getLblTitle() {
		if (lblTitle == null) {
			if (this.unit.getId() != null)
				lblTitle = new JLabel("Editar Unidade");
			else
				lblTitle = new JLabel("Nova Unidade");
			lblTitle.setAlignmentX(CENTER_ALIGNMENT);
			lblTitle.setAlignmentY(CENTER_ALIGNMENT);
		}
		return lblTitle;
	}

	private JTextField getTxtDescription() {
		if (txtDescription == null) {
			txtDescription = new JTextField(30);
			txtDescription.setSelectionStart(0);
		}
		return txtDescription;
	}

	private JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel("Descrição");
		}
		return lblDescription;
	}

	private JPanel getPanBottom() {
		if (panBottom == null) {
			panBottom = new JPanel();
			panBottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
			panBottom.add(getBtnExit());
			panBottom.add(getBtnSave());

		}
		return panBottom;
	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Salvar");
			btnSave.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					flush(UnitForm.this.unit);
					parent.dispose();
					listener.onSave(UnitForm.this.unit);
				}
			});
		}
		return btnSave;
	}

	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Sair");
			btnExit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					parent.dispose();
				}
			});
		}
		return btnExit;
	}

	private void refresh(Unit unit) {
		getTxtDescription().setText(unit.getDescription());
	}

	private void flush(Unit unit) {
		unit.setDescription(getTxtDescription().getText().trim());
	}

}
