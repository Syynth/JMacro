package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;
import com.syynth.jmacro.input.SmartRobots;
import com.syynth.jmacro.ui.Console;

/**
 * @author syynth
 * @since 3/7/14
 */
class TypeCommand extends Command {

	int button;
	String text;

	public TypeCommand(String text) {
		super(text);
		button = -1;
		if (!text.startsWith("\"")) {
			if (!text.endsWith("\"")) {
				Console.error("The command: type " + text + " is malformed");
			} else {
				text = text.replace("\"", "");
			}
			button = SmartRobots.getKeyByName(text);
		} else {
			this.text = text;
		}
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		if (button == -1) {
			robot.type(text);
		} else {
			robot.type(button);
		}
	}
}
