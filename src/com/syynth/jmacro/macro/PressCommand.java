package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;
import com.syynth.jmacro.input.SmartRobots;

/**
 * @author syynth
 * @since 3/7/14
 */
class PressCommand extends Command {

	private int button;

	public PressCommand(String commandText) {
		super(commandText);
		if (commandText.length() > 1)
			button = SmartRobots.getKeyByName(commandText);
		else
			button = SmartRobots.characterToKey(commandText.charAt(0));
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		robot.press(button);
	}
}