package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;

/**
 * @author syynth
 * @since 3/7/14
 */
class CommentCommand extends Command {

	public CommentCommand(String commandText) {
		super(commandText);
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		return;
	}
}
