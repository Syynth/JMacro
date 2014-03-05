package com.syynth.jmacro.input;

import com.syynth.jmacro.ui.Console;

import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

/**
 * @author syynth
 * @since 3/5/14
 */
public final class SmartRobots {

	private SmartRobots() {};

	public static int characterToKey(char c) {
		try {
			Field f = KeyEvent.class.getField("VK_" + Character.toUpperCase(c));
			f.setAccessible(true);
			try {
				return (Integer) f.get(null);
			} catch (IllegalArgumentException | IllegalAccessException ex) {
				Console.error("Something went wrong! We were unable to convert " + c + " to a key!");
			}
		} catch (NoSuchFieldException | SecurityException ex) {
				Console.error("Something went wrong! We were unable to convert " + c + " to a key!");
		}
		return 0;
	}

	public static int getKeyByName(String name) {
		int code = 0;
		if (name.equals("tab")) { code = KeyEvent.VK_TAB; }
		if (name.equals("enter")) { code = KeyEvent.VK_ENTER; }
		if (name.equals("forward")) { code = KeyEvent.VK_F3; }
		if (name.equals("back")) { code = KeyEvent.VK_F9; }
		if (name.equals("backspace")) { code = KeyEvent.VK_BACK_SPACE; }
		if (name.equals("home")) { code = KeyEvent.VK_HOME; }
		if (name.equals("end")) { code = KeyEvent.VK_END; }
		if (name.equals("up")) { code = KeyEvent.VK_UP; }
		if (name.equals("down")) { code = KeyEvent.VK_DOWN; }
		if (name.equals("left")) { code = KeyEvent.VK_LEFT; }
		if (name.equals("right")) { code = KeyEvent.VK_RIGHT; }
		if (name.equals("capslock")) { code = KeyEvent.VK_CAPS_LOCK; }
		if (name.equals("ctrl")) { code = KeyEvent.VK_CONTROL; }
		if (name.equals("shift")) { code = KeyEvent.VK_SHIFT; }
		if (name.equals("alt")) { code = KeyEvent.VK_ALT; }
		if (name.equals("escape")) { code = KeyEvent.VK_ESCAPE; }
		if (name.equals("f1")) { code = KeyEvent.VK_F1; }
		if (name.equals("f2")) { code = KeyEvent.VK_F2; }
		if (name.equals("f3")) { code = KeyEvent.VK_F3; }
		if (name.equals("f4")) { code = KeyEvent.VK_F4; }
		if (name.equals("f5")) { code = KeyEvent.VK_F5; }
		if (name.equals("f6")) { code = KeyEvent.VK_F6; }
		if (name.equals("f7")) { code = KeyEvent.VK_F7; }
		if (name.equals("f8")) { code = KeyEvent.VK_F8; }
		if (name.equals("f9")) { code = KeyEvent.VK_F9; }
		if (name.equals("f10")) { code = KeyEvent.VK_F10; }
		if (name.equals("f11")) { code = KeyEvent.VK_F11; }
		if (name.equals("f12")) { code = KeyEvent.VK_F12; }
		if (name.equals("insert")) { code = KeyEvent.VK_INSERT; }
		if (name.equals("delete")) { code = KeyEvent.VK_DELETE; }
		if (name.equals("pageup")) { code = KeyEvent.VK_PAGE_UP; }
		if (name.equals("pagedown")) { code = KeyEvent.VK_PAGE_DOWN; }
		if (name.equals("super")) { code = KeyEvent.VK_WINDOWS; }
		return code;
	}

}
