package com.syynth.jmacro.data;

import com.syynth.jmacro.ui.Console;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author syynth
 * @since 3/5/14
 */
public class DataManager {

	private ArrayList<DataModel> models;
	private File file;
	private int currentIndex;

	public DataManager(String path) {
		models = new ArrayList<>();
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
			while (scanner.hasNextLine()) {
				models.add(new DataModel(scanner.nextLine().split(",")));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Console.error("Unable to find file " + path);
		}
	}

	public int size() {
		return models.size();
	}

	public void next() {
		clamp(++currentIndex);
	}

	public int getCurrentIndex() {
		if (models.size() > 0) return currentIndex + 1;
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {
		clamp(currentIndex);
	}

	public String current() {
		if (models.size() > 0) return models.get(currentIndex).toString();
		return "[ ]";
	}

	public void previous() {
		clamp(--currentIndex);
	}

	private void clamp(int index) {
		currentIndex = Math.max(0, Math.min(index, size() - 1));
	}
}
