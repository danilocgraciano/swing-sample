package com.swingsample.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TablePaginator extends JPanel {

	private Table table;
	private Long page = 1l;
	private JButton btnFirst;
	private JButton btnPrevious;
	private JTextField txtPage;
	private JLabel lblOf;
	private JLabel lblTotal;
	private JButton btnNext;
	private JButton btnLast;
	private JButton btnReload;

	private JPanel leftPanel;
	private JPanel rightPanel;

	private JLabel lblShowingItens;

	public Long getActualPage() {
		return page;
	}

	public Long getNextPage() {
		if (page <= getLastPage())
			return page;
		return ++page;
	}

	public Long getPreviousPage() {
		if (page != 1)
			--page;
		return page;
	}

	public Long getFirstPage() {
		page = 1l;
		return page;
	}

	public Long getLastPage() {
		page = table.getTotalPages();
		return page;
	}

	public Long getTotalItens() {
		return table.getTotalItens();
	}

	private JButton getBtnFirst() {
		if (btnFirst == null) {
			btnFirst = new JButton("<<");
			btnFirst.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					table.load(getFirstPage());
					getTxtPage().setText(String.valueOf(getActualPage()));
					refresh();
				}
			});
		}

		return btnFirst;
	}

	private JButton getBtnPrevious() {
		if (btnPrevious == null) {
			btnPrevious = new JButton("<");
			btnPrevious.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					table.load(getPreviousPage());
					getTxtPage().setText(String.valueOf(getActualPage()));
					refresh();
				}
			});
		}
		return btnPrevious;
	}

	private JTextField getTxtPage() {
		if (txtPage == null) {
			txtPage = new JTextField(String.valueOf(getActualPage()));
			txtPage.setFocusable(false);
		}
		return txtPage;
	}

	private JLabel getLblOf() {
		if (lblOf == null)
			lblOf = new JLabel("de");
		return lblOf;
	}

	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel(String.valueOf(getLastPage()));
		}
		return lblTotal;
	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton(">");
			btnNext.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					table.load(getNextPage());
					getTxtPage().setText(String.valueOf(getActualPage()));
					refresh();
				}
			});
		}
		return btnNext;
	}

	private JButton getBtnLast() {
		if (btnLast == null) {
			btnLast = new JButton(">>");
			btnLast.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					table.load(getLastPage());
					getTxtPage().setText(String.valueOf(getActualPage()));
					refresh();
				}
			});
		}
		return btnLast;
	}

	private JButton getBtnReload() {
		if (btnReload == null) {
			btnReload = new JButton("F5");
			btnReload.setMnemonic(KeyEvent.VK_F5);
			btnReload.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					refresh();
				}
			});
		}
		return btnReload;
	}

	public void refresh() {

		if (page > table.getTotalPages())
			page = getLastPage();

		table.load(getActualPage());
		getTxtPage().setText(String.valueOf(getActualPage()));
		lblTotal = null;
		getLblTotal();

		String start = String.valueOf(1 + table.getOffset(getActualPage()));
		String finish = String.valueOf(table.getPageSize());
		String total = String.valueOf(table.getTotalItens());

		if (getActualPage() > 1)
			finish = String.valueOf(Long.parseLong(start) + table.getPageSize());

		if (Long.parseLong(finish) > Long.parseLong(total))
			finish = total;

		String text = String.format("Mostrando %s - %s de %s", start, finish, total);
		getLblShowItens().setText(text);

	}

	public TablePaginator(Table table) {

		this.table = table;
		setLayout(new BorderLayout());
		add(getLeftPanel(), BorderLayout.WEST);
		add(getRightPanel(), BorderLayout.EAST);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				table.load(getFirstPage());
				refresh();
			}
		});

	}

	private JPanel getLeftPanel() {
		if (leftPanel == null) {
			leftPanel = new JPanel();
			leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			leftPanel.add(getBtnFirst());
			leftPanel.add(getBtnPrevious());
			leftPanel.add(getTxtPage());
			leftPanel.add(getLblOf());
			leftPanel.add(getLblTotal());
			leftPanel.add(getBtnNext());
			leftPanel.add(getBtnLast());
			leftPanel.add(getBtnReload());

		}
		return leftPanel;
	}

	private JPanel getRightPanel() {
		if (rightPanel == null) {
			rightPanel = new JPanel();
			rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			rightPanel.add(getLblShowItens());
		}
		return rightPanel;
	}

	private JLabel getLblShowItens() {
		if (lblShowingItens == null) {
			lblShowingItens = new JLabel();
		}
		return lblShowingItens;
	}

}
