package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;

/**
 * @author syynth
 * @since 3/7/14
 */
class RepeatCommand extends Command {

	private int reps;
	private Command command;

	public RepeatCommand(String commandText) {
		super(commandText);
		reps = Integer.parseInt(commandText.split(" ")[0]);
		command = Commands.getCommand(commandText.replaceFirst("\\d*", "").trim());
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		for (int i = 0; i < reps; ++i)
			command.execute(macroManager, robot, dataManager);
	}
}
