package com.syynth.jmacro.macro;

import com.syynth.jmacro.ui.Console;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author syynth
 * @since 3/5/14
 */
public class MacroManager {

	private ArrayList<Command> commands;
	private File file;
	private int currentIndex;
	private HashMap<String, ArrayList<Command>> commandSets;

	public MacroManager(String path) {
		commands = new ArrayList<>();
		commandSets = new HashMap<>();
		file = new File(path);
		currentIndex = 0;
		parse();
	}

	public void parse() {
		parse(file.getAbsolutePath());
	}

	public void parse(String path) {
		try {
			file = new File(path);
			Scanner scanner = new Scanner(file);
			StringBuilder builder = new StringBuilder();
			while (scanner.hasNextLine()) {
				builder.append(scanner.nextLine() + "\n");
			}
			parseCommands(builder.toString());
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			Console.error("Unable to find file " + path);
		}
	}

	private void parseCommands(String source) {
		Pattern pattern = Pattern.compile("\\[[a-zA-Z]*\\]+[\\s[a-zA-Z0-9]\"@,!\\$\\^]*\\s+\\[\\/[a-zA-Z]*\\]");
		Matcher matcher = pattern.matcher(source);
		while (matcher.find()) {
			parseCommandSet(matcher.group());
		}
	}

	private void parseCommandSet(String source) {
		Console.log("Parsing: " + source);
		String[] allLines = source.split("\n");
		String tagName = allLines[0].replaceAll("[\\[\\]]", "");
		Console.log("Found named group " + tagName);
		String[] lines = new String[allLines.length - 2];
		System.arraycopy(source.split("\n"), 1, lines, 0, allLines.length - 2);
		ArrayList<Command> commandSet = new ArrayList<>();
		for (String msg : lines) {
			Console.log(msg);
			commandSet.add(Commands.getCommand(msg));
		}
	}

}
