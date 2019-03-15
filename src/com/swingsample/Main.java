package com.swingsample;

import java.awt.EventQueue;

import com.swingsample.dao.ConnectionFactory;
import com.swingsample.view.MainFrame;

public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		// TODO colocar um splash screen para que fique transparente ao usuário
		ConnectionFactory.getEntityManager();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
