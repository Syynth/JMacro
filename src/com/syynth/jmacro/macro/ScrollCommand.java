package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;

/**
 * @author syynth
 * @since 3/7/14
 */
class ScrollCommand extends Command {

	private int delta;

	public ScrollCommand(String commandText) {
		super(commandText);
		delta = Integer.parseInt(commandText);
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		robot.scroll(delta);
	}
}
