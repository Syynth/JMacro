package com.syynth.jmacro.input;

/**
 * @author syynth
 * @since 3/5/14
 */
public interface SmartRobot {

	public void click();
	public void move(int x, int y);
	public void scroll(int delta);
	public void type(String text);
	public void type(int key);
	public void press(int key);
	public void release(int key);

}
