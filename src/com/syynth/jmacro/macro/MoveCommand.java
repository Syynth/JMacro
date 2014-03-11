package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;

/**
 * @author syynth
 * @since 3/7/14
 */
class MoveCommand extends Command {

	private String x;
	private String y;
	private boolean resolveX;
	private boolean resolveY;

	public MoveCommand(String commandText) {
		super(commandText);
		x = commandText.split(" ")[0];
		y = commandText.split(" ")[1];
		resolveX = x.startsWith("@");
		resolveY = y.startsWith("@");
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		robot.move(Integer.parseInt(resolveVariable(macroManager, x, resolveX)),
			Integer.parseInt(resolveVariable(macroManager, y, resolveY)));
	}
}
