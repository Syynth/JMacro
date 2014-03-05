package com.syynth.jmacro.input;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * @author syynth
 * @since 3/5/14
 */
class AwtSmartRobot extends Robot implements SmartRobot {
	public AwtSmartRobot() throws AWTException {

	}

	@Override
	public void click() {
		mousePress(InputEvent.BUTTON1_MASK);
		mouseRelease(InputEvent.BUTTON1_MASK);
	}

	@Override
	public void move(int x, int y) {
		mouseMove(x, y);
	}

	@Override
	public void scroll(int delta) {
		mouseWheel(delta);
	}

	@Override
	public void type(String text) {
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			int code;
			boolean shift = false;
			if (Character.isLetterOrDigit(c)) {
				code = SmartRobots.characterToKey(c);
				shift = !Character.isLowerCase(c) && Character.isLetter(c);
			} else {
				switch (c) {
					case '_': shift = true;
					case '-': code = KeyEvent.VK_MINUS; break;
					case '+': shift = true;
					case '=': code = KeyEvent.VK_EQUALS; break;
					case '{': shift = true;
					case '[': code = KeyEvent.VK_OPEN_BRACKET; break;
					case '}': shift = true;
					case ']': code = KeyEvent.VK_CLOSE_BRACKET; break;
					case ':': shift = true;
					case ';': code = KeyEvent.VK_SEMICOLON; break;
					case '"': shift = true;
					case '\'': code = KeyEvent.VK_QUOTE; break;
					case '<': shift = true;
					case ',': code = KeyEvent.VK_COMMA; break;
					case '>': shift = true;
					case '.': code = KeyEvent.VK_PERIOD; break;
					case '?': shift = true;
					case '/': code = KeyEvent.VK_SLASH; break;
					case '|': shift = true;
					case '\\': code = KeyEvent.VK_BACK_SLASH; break;
					case '!': shift = true; code = KeyEvent.VK_1; break;
					case '@': shift = true; code = KeyEvent.VK_2; break;
					case '#': shift = true; code = KeyEvent.VK_3; break;
					case '$': shift = true; code = KeyEvent.VK_4; break;
					case '%': shift = true; code = KeyEvent.VK_5; break;
					case '^': shift = true; code = KeyEvent.VK_6; break;
					case '&': shift = true; code = KeyEvent.VK_7; break;
					case '*': shift = true; code = KeyEvent.VK_8; break;
					case '(': shift = true; code = KeyEvent.VK_9; break;
					case ')': shift = true; code = KeyEvent.VK_0; break;
					default: code = KeyEvent.VK_SPACE; break;
				}
			}
			if (shift) {
				super.keyPress(KeyEvent.VK_SHIFT);
			}
			super.keyPress(code);
			super.keyRelease(code);
			if (shift) {
				super.keyRelease(KeyEvent.VK_SHIFT);
			}
		}
	}

	@Override
	public void press(String text) {

	}

	@Override
	public void release(String text) {

	}
}
