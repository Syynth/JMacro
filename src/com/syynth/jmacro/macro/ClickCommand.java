package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;

/**
 * @author syynth
 * @since 3/7/14
 */
class ClickCommand extends Command {
	public ClickCommand(String command) {
		super(command);
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		robot.click();
	}
}