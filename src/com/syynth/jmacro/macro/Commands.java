package com.syynth.jmacro.macro;

/**
 * @author syynth
 * @since 3/5/14
 */
public final class Commands {

	private Commands() {}

	public static Command getCommand(String commandText) {
		String text = commandText.toLowerCase();
		if (text.startsWith("!") || text.startsWith("#"))
			return new CommentCommand("");
		if (text.startsWith("assign"))
			return new AssignCommand(text.replaceFirst("assign", "").trim());
		if (text.startsWith("click"))
			return new ClickCommand(text.replaceFirst("click", "").trim());
		if (text.startsWith("if"))
			return new IfCommand(text.replaceFirst("if", "").trim());
		if (text.startsWith("load"))
			return new LoadCommand(text.replaceFirst("load", "").trim());
		if (text.startsWith("move"))
			return new MoveCommand(text.replaceFirst("move", "").trim());
		if (text.startsWith("press"))
			return new PressCommand(text.replaceFirst("press", "").trim());
		if (text.startsWith("release"))
			return new ReleaseCommand(text.replaceFirst("release", "").trim());
		if (text.startsWith("repeat"))
			return new RepeatCommand(text.replaceFirst("repeat", "").trim());
		if (text.startsWith("run"))
			return new RunCommand(text.replaceFirst("run", "").trim());
		if (text.startsWith("scroll"))
			return new ScrollCommand(text.replaceFirst("scroll", "").trim());
		if (text.startsWith("skip"))
			return new SkipCommand(text.replaceFirst("skip", "").trim());
		if (text.startsWith("type"))
			return new TypeCommand(text.replaceFirst("type", "").trim());
		if (text.startsWith("wait"))
			return new WaitCommand(text.replaceFirst("wait", "").trim());
		return null;
	}

}
