package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;

/**
 * @author syynth
 * @since 3/7/14
 */
class AssignCommand extends Command {

	private String name;
	private String value;

	public AssignCommand(String commandText) {
		super(commandText);
		name = commandText.split(" ")[0];
		value = commandText.split(" ")[1];
		resolve = value.startsWith("@");
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		macroManager.setStateProperty(name, resolveVariable(macroManager, value, resolve));
	}
}
