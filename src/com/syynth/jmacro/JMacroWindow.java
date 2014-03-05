package com.syynth.jmacro;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.javasoft.plaf.synthetica.*;

import java.text.ParseException;

/**
 * @author bcochrane
 * @since 3/4/14
 */
public class JMacroWindow {

	public static final String VERSION = "0.1";

	//region Private UI Fields
	private JButton resetButton;
	private JTextField dataPreview;
	private JButton selectData;
	private JButton editData;
	private JButton createData;
	private JTextField scriptPreview;
	private JButton selectScript;
	private JButton editScript;
	private JButton createScript;
	private JButton previousButton;
	private JButton nextButton;
	private JTextField currentDataPreview;
	private JButton infoButton;
	private JButton startButton;
	private JPanel rootPanel;
	private JButton showConsole;
	private JRadioButton continuousRadioButton;
	private JRadioButton singleInsertRadioButton;
	//endregion

	public JMacroWindow() {
		showConsole.addActionListener(e -> Console.start());
		editData.addActionListener(e -> { if (!"".equals(dataPreview.getText())) editFile(dataPreview.getText()); });
		selectData.addActionListener(e -> selectFile(dataPreview, "CSV Files", "csv", JFileChooser.OPEN_DIALOG));
		createData.addActionListener(e -> {
			selectFile(dataPreview, "CSV Files", "csv", JFileChooser.SAVE_DIALOG);
			if (!"".equals(dataPreview.getText())) editFile(dataPreview.getText());
		});
		editScript.addActionListener(e -> { if (!"".equals(scriptPreview.getText())) editFile(scriptPreview.getText()); });
		selectScript.addActionListener(e -> selectFile(scriptPreview, "Macro Files", "mfl", JFileChooser.OPEN_DIALOG));
		createScript.addActionListener(e -> {
			selectFile(scriptPreview, "Macro Files", "mfl", JFileChooser.SAVE_DIALOG);
			if (!"".equals(scriptPreview.getText())) editFile(scriptPreview.getText());
		});
	}

	private JMacroWindow selectFile(JTextField btn, String description, String extension, int type) {
		btn.setText(browse(description, extension, type));
		return this;
	}

	private JMacroWindow editFile(String path) {
		Console.log("Opening file " + path);
		new Editor(path);
		return this;
	}

	private String browse(String description, String extension, int save) {
		JFileChooser f = new JFileChooser(System.getProperty("user.dir"));
		f.setFileSelectionMode(JFileChooser.FILES_ONLY);
		f.setFileFilter(new FileNameExtensionFilter(description, extension));
		f.setDialogType(save);
		f.showDialog(f, "JyMacro - " + VERSION);
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
	}

}