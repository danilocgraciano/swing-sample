package com.swingsample.view;

import java.awt.Window;

import javax.swing.JOptionPane;

public class Messages {

	public static void info(Window parent, String title, String message) {
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

	public static void error(Window parent, String title, String message) {
		JOptionPane.showMessageDialog(parent, message, title, JOptionPane.ERROR_MESSAGE);
	}

	public static boolean confirm(Window parent, String title, String message) {
		Object[] options = { "Sim", "Não" };
		int resp = JOptionPane.showOptionDialog(parent, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		return resp == JOptionPane.YES_OPTION;
	}

}
