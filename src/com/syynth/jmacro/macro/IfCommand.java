package com.syynth.jmacro.macro;

import com.syynth.jmacro.data.DataManager;
import com.syynth.jmacro.input.SmartRobot;
import com.syynth.jmacro.ui.Console;

/**
 * @author syynth
 * @since 3/7/14
 */
class IfCommand extends Command {

	private String lhs;
	private String rhs;
	private boolean resolveLhs;
	private boolean resolveRhs;
	private Operators operator;
	private boolean hasElse;
	private Command trueCommand;
	private Command falseCommand;

	public IfCommand(String commandText) {
		super(commandText);
		lhs = commandText.split(" ")[0];
		rhs = commandText.split(" ")[2];
		resolveLhs = lhs.startsWith("@");
		resolveRhs = rhs.startsWith("@");
		String op = commandText.split(" ")[1];
		switch (op.toLowerCase()) {
			case "==": case "eq": operator = Operators.Equal; break;
			case "!=": case "neq": operator = Operators.NotEqual; break;
			case "<": case "lt": operator = Operators.LessThan; break;
			case ">": case "gt": operator = Operators.GreaterThan; break;
			case "<=":case "lte": operator = Operators.LessThanEquals; break;
			case ">=":case "gte": operator = Operators.GreaterThanEquals; break;
			default: operator = Operators.Equal;
				Console.error("Invalid logical operator in command: if " + commandText); break;
		}
		trueCommand = Commands.getCommand(commandText.split("then")[0].split("else")[0].trim());
		hasElse = commandText.contains("else");
		if (hasElse) {
			falseCommand = Commands.getCommand(commandText.split("then")[0].split("else")[1].trim());
		}
	}

	@Override
	public void execute(MacroManager macroManager, SmartRobot robot, DataManager dataManager) {
		String rlhs = resolveVariable(macroManager, lhs, resolveLhs);
		String rrhs = resolveVariable(macroManager, rhs, resolveRhs);
		switch (operator) {
			case Equal:
				runCommand(macroManager, robot, dataManager, rlhs.equals(rrhs));
				break;
			case LessThan:
				runCommand(macroManager, robot, dataManager, rlhs.compareToIgnoreCase(rrhs) < 0);
				break;
			case LessThanEquals:
				runCommand(macroManager, robot, dataManager, rlhs.compareToIgnoreCase(rrhs) <= 0);
				break;
			case GreaterThan:
				runCommand(macroManager, robot, dataManager, rlhs.compareToIgnoreCase(rrhs) > 0);
				break;
			case GreaterThanEquals:
				runCommand(macroManager, robot, dataManager, rlhs.compareToIgnoreCase(rrhs) >= 0);
				break;
			case NotEqual:
				runCommand(macroManager, robot, dataManager, !rlhs.equals(rrhs));
				break;
		}
	}

	private void runCommand(MacroManager macroManager, SmartRobot robot, DataManager dataManager, boolean result) {
		if (result) {
			trueCommand.execute(macroManager, robot, dataManager);
		} else if (hasElse) {
			falseCommand.execute(macroManager, robot, dataManager);
		}
	}

	private enum Operators {
		Equal,
		LessThan,
		LessThanEquals,
		GreaterThan,
		GreaterThanEquals,
		NotEqual
	}
}
