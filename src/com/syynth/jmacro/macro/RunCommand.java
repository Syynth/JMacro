package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;

/**
 * @author syynth
 * @since 3/7/14
 */
class RunCommand extends Command {

	private String block;

	public RunCommand(String commandText) {
		super(commandText);
		block = commandText;
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		macroManager.executeCommandSet(block);
	}
}
