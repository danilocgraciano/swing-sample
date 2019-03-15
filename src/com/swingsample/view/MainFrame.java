package com.swingsample.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.swingsample.view.unit.UnitList;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private UnitList unitList;

	private UnitList getUnitList() {
		if (unitList == null)
			unitList = new UnitList(this);
		return unitList;
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 577);
		setLocationRelativeTo(null);
		setTitle("Swing Sample - 1.0.0");
		setLayout(null);
		setMenu();
	}

	private void setMenu() {

		JMenuBar menubar = new JMenuBar();

		JMenu menu = new JMenu("Cadastros");
		JMenuItem unitMI = new JMenuItem("Unidades");
		unitMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setContentPane(getUnitList());
				revalidate();
			}
		});

		menu.add(unitMI);
		menubar.add(menu);
		setJMenuBar(menubar);
	}

}
