package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;
import com.syynth.jmacro.ui.Console;

/**
 * @author syynth
 * @since 3/5/14
 */
public abstract class Command {

	protected int line;
	protected boolean resolve;

	public Command(String commandText) {
		Console.log("Parsing " + commandText);
	}

	public abstract void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager);

	protected String resolveVariable(MacroManager manager, String symbol, boolean resolve) {
		if (resolve) {
			return manager.getStateProperty(symbol.replaceFirst("@", ""));
		} else {
			return symbol;
		}
	}

}
