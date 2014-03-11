package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;
import com.syynth.jmacro.ui.Console;

/**
 * @author syynth
 * @since 3/7/14
 */
class WaitCommand extends Command {

	private String time;

	public WaitCommand(String commandText) {
		super(commandText);
		time = commandText;
		resolve = commandText.startsWith("@");
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		try {
			macroManager.wait(Long.parseLong(resolveVariable(macroManager, time, resolve)));
		} catch (InterruptedException e) {
			e.printStackTrace();
			Console.error("Somehow there was an error trying to wait!");
		}
	}
}
