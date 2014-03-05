package com.syynth.jmacro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bcochrane
 * @since 3/5/14
 */
public class Console extends JFrame {

	//region Private UI Components
	private JPanel root;
	private JButton clear;
	private JPanel labelContainer;
	//endregion

	private static ArrayList<JLabel> messages = new ArrayList<>();
	private static Console console = null;

	private static JLabel makeLabel(String message, Color c) {
		JLabel l = new JLabel(message);
		l.setForeground(c);
		return l;
	}

	public static void log(String message) {
		messages.add(makeLabel(message, Color.black));
		console.updateUI();
	}

	public static void error(String message) {
		messages.add(makeLabel(message, Color.red));
		console.updateUI();
	}

	public static void start() {
		if (console == null) console = new Console();
	}

	public static void stop() {
		console = null;
	}

	private Console() {
		setTitle("JMacro Console");
		setContentPane(root);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setSize(480, 320);
		setVisible(true);
		labelContainer.setLayout(new BoxLayout(labelContainer, BoxLayout.Y_AXIS));
		addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {} public void windowClosing(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {} public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {} public void windowDeactivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {
				Console.stop();
			}
		});
		updateUI();
		clear.addActionListener(e -> clear());
	}

	private void updateUI() {
		labelContainer.removeAll();
		for (JLabel msg : messages) {
			labelContainer.add(msg);
		}
		labelContainer.revalidate();
		labelContainer.repaint();
	}

	private void clear() {
		messages.clear();
		updateUI();
	}

}
