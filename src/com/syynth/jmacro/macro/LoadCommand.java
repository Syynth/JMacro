package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;
import com.syynth.jmacro.ui.Console;

/**
 * @author syynth
 * @since 3/7/14
 */
class LoadCommand extends Command {

	private String load;

	public LoadCommand(String commandText) {
		super(commandText);
		load = commandText;
		resolve = load.startsWith("@");
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		try {
			dataManager.setCurrentIndex(Integer.parseInt(resolveVariable(macroManager, load, resolve)));
		} catch (Exception e) {
			Console.error("Error parsing number. Could not load line " + load + " of CSV data file.");
			Console.error(load + " must be an integer.");
		}
	}
}
