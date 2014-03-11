package com.syynth.jmacro;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.GlobalKeyListener;
import com.syynth.jmacro.macro.MacroManager;
import com.syynth.jmacro.macro.RunModes;
import com.syynth.jmacro.ui.Console;
import com.syynth.jmacro.ui.Editor;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @author syynth
 * @since 3/4/14
 */
public class JMacroWindow {

	public static final String VERSION = "0.5.0";
	private GlobalKeyListener keyListener;
	private DataManager dataManager;
	private MacroManager macroManager;
	private RunModes mode;
	private Color color;

	//region Private UI Fields
	private JButton reset;
	private JTextField dataPreview;
	private JButton selectData;
	private JButton editData;
	private JButton createData;
	private JTextField scriptPreview;
	private JButton selectScript;
	private JButton editScript;
	private JButton createScript;
	private JButton previous;
	private JButton next;
	private JTextField item;
	private JButton info;
	private JButton start;
	private JPanel rootPanel;
	private JButton showConsole;
	private JRadioButton continuousMode;
	private JRadioButton singleInsertMode;
	private JLabel progress;
	private JPanel panel;
	//endregion

	public JMacroWindow() {
		mode = RunModes.Continuous;
		showConsole.addActionListener(e -> {
			if (showConsole.getText().equals("Show Log")) {
				Console.start();
				showConsole.setText("Hide Log");
			} else {
				Console.stop();
				showConsole.setText("Show Log");
			}
		});
		editData.addActionListener(e -> { if (!"".equals(dataPreview.getText())) editFile(dataPreview.getText()); });
		selectData.addActionListener(e -> selectFile(dataPreview, "CSV Files", "csv", JFileChooser.OPEN_DIALOG).updatePreview(true));
		createData.addActionListener(e -> {
			selectFile(dataPreview, "CSV Files", "csv", JFileChooser.SAVE_DIALOG);
			if (!"".equals(dataPreview.getText())) editFile(dataPreview.getText());
		});
		editScript.addActionListener(e -> { if (!"".equals(scriptPreview.getText())) editFile(scriptPreview.getText()); });
		selectScript.addActionListener(e -> selectFile(scriptPreview, "Macro Files", "mfl", JFileChooser.OPEN_DIALOG).parseScript());
		createScript.addActionListener(e -> {
			selectFile(scriptPreview, "Macro Files", "mfl", JFileChooser.SAVE_DIALOG);
			if (!"".equals(scriptPreview.getText())) editFile(scriptPreview.getText());
		});
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
			Console.start();
			Console.error("Unable to gain native access to the keyboard. Please restart JMacro");
		}
		next.addActionListener(e -> increment(true));
		previous.addActionListener(e -> increment(false));
		keyListener = new GlobalKeyListener();
		start.addActionListener(e -> startMacro());
		continuousMode.addActionListener(e -> mode = RunModes.Continuous);
		singleInsertMode.addActionListener(e -> mode = RunModes.Discrete);
	}

	private JMacroWindow startMacro() {
		keyListener.addF5Listener(e -> { if (macroManager != null) macroManager.run(mode); });
		keyListener.addF6Listener(e -> { keyListener.clearListeners(); macroManager.stop(); clearReady();});
		getReady();
		return this;
	}

	private void clearReady() {
		panel.setBackground(color);
	}

	private void getReady() {
		color = panel.getBackground();
		panel.setForeground(Color.red);
	}

	private JMacroWindow selectFile(JTextField btn, String description, String extension, int type) {
		btn.setText(browse(description, extension, type));
		return this;
	}

	private JMacroWindow editFile(String path) {
		Console.log("Opening file " + path);
		new Editor(path, this);
		return this;
	}

	public String browse(String description, String extension, int save) {
		JFileChooser f = new JFileChooser(System.getProperty("user.dir"));
		f.setFileSelectionMode(JFileChooser.FILES_ONLY);
		f.setFileFilter(new FileNameExtensionFilter(description, extension));
		f.setDialogType(save);
		f.showDialog(f, stringName(save));
		if (f.getSelectedFile() != null) {
			if (save == JFileChooser.SAVE_DIALOG && !f.getSelectedFile().toString().endsWith("." + extension)) {
				Console.log("Selected file " + f.getSelectedFile().toString() + "." + extension);
				return f.getSelectedFile().toString() + "." + extension;
			}
			Console.log("Selected file " + f.getSelectedFile().toString());
			return f.getSelectedFile().toString();
		}
		return "";
	}

	public JMacroWindow updatePreview(boolean reload) {
		if (!"".equals(dataPreview.getText())) {
			if (dataManager == null || reload) {
				String path = dataPreview.getText();
				dataManager = new DataManager(path);
			}
			item.setText(dataManager.current());
			progress.setText("[" + dataManager.getCurrentIndex() + "/" + dataManager.size() + "]");
		}
		return this;
	}

	public JMacroWindow parseScript() {
		if (!"".equals(scriptPreview.getText())) {
			String path = scriptPreview.getText();
			macroManager = new MacroManager(path);
		}
		return this;
	}

	private JMacroWindow increment(boolean sign) {
		if (dataManager != null) {
			if (sign) {
				dataManager.next();
			} else {
				dataManager.previous();
			}
			updatePreview(false);
		}
		return this;
	}

	private String stringName(int type) {
		if (type == JFileChooser.SAVE_DIALOG) return "Save";
		if (type == JFileChooser.OPEN_DIALOG) return "Open";
		return "JMacro - " + VERSION;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
		}
		JFrame frame = new JFrame("JMacroWindow");
		frame.setContentPane(new JMacroWindow().rootPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowListener() {
			@Override public void windowIconified(WindowEvent e) {} @Override public void windowDeiconified(WindowEvent e) {}
			@Override public void windowActivated(WindowEvent e) {} @Override public void windowDeactivated(WindowEvent e) {}
			@Override public void windowOpened(WindowEvent e) {} @Override public void windowClosing(WindowEvent e) {}
			@Override	public void windowClosed(WindowEvent e) {
				GlobalScreen.unregisterNativeHook();
			}
		});
	}

}