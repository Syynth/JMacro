package com.syynth.jmacro.macro;

import com.syynth.jmacro.input.SmartRobot;

/**
 * @author syynth
 * @since 3/5/14
 */
public abstract class Command {

	public Command(String commandText) {}

	public abstract void execute(MacroManager manager, SmartRobot robot);

}
