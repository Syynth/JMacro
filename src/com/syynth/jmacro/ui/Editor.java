package com.syynth.jmacro.ui;

import com.syynth.jmacro.JMacroWindow;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Scanner;

/**
 * @author syynth
 * @since 3/4/14
 */
public class Editor extends JFrame {

	//region Private UI Components
	private JPanel root;
	private JButton save;
	private JButton saveAs;
	private JTextArea text;
	private JTextArea lineNumbers;
	private String path;
	//endregion

	private final JMacroWindow window;
	private String cache;
	private boolean changed;

	private boolean saveFile() {
		if (!changed) return true;
		com.syynth.jmacro.ui.Console.log("Writing file ...");
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path)));
			writer.write(text.getText());
			writer.close();
			cache = text.getText();
			com.syynth.jmacro.ui.Console.log("Write successful");
			window.updatePreview(true);
			return true;
		} catch (IOException e) {
			com.syynth.jmacro.ui.Console.error("Write failure");
			e.printStackTrace();
			return false;
		}
	}

	private Editor readFile() {
		if (!"".equals(path)) {
			try {
				StringBuilder builder = new StringBuilder();
				Scanner scanner = new Scanner(new File(path));
				while (scanner.hasNext()) {
					builder.append(scanner.nextLine()).append('\n');
				}
				text.setText(builder.toString());
				cache = builder.toString();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				com.syynth.jmacro.ui.Console.error("Tried to read non-existent file: " + path);
				saveFile();
			}
		} else {
			com.syynth.jmacro.ui.Console.log("Path is empty");
		}
		updateLines();
		return this;
	}

	private void updateTitle() {
		if (!cache.equals(text.getText())) {
			setTitle("*" + path);
			changed = true;
		} else {
			setTitle(path);
			changed = false;
		}
	}

	private void updateLines() {
		String data = text.getText();
		int lines = text.getLineCount();
		StringBuilder builder = new StringBuilder();
		for (int i = 1; i <= lines; ++i) {
			builder.append(i).append('\n');
		}
		lineNumbers.setText(builder.toString());
	}

	public Editor(String path, JMacroWindow window) {
		this.window = window;
		this.path = path;
		this.cache = "";
		this.changed = false;
		setTitle(path);
		setContentPane(root);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setSize(640, 480);
		setVisible(true);
		readFile();
		addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {} public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {} public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {} public void windowDeactivated(WindowEvent e) {}
			public void windowClosing(WindowEvent e) {
				if (changed) {
					if (JOptionPane.showConfirmDialog(root, "There are unsaved changes to your file. Would you like to save now?",
						"Unsaved Changes", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						saveFile();
					}
				}
			}
		});
		text.addKeyListener(new KeyListener() {
			@Override public void keyTyped(KeyEvent e) {
				updateTitle();
				updateLines();
			}
			@Override public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
					saveFile();
				}
				updateTitle();
			}
			@Override public void keyReleased(KeyEvent e) {}
		});
	}
}