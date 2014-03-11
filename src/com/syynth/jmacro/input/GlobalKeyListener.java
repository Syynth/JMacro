package com.syynth.jmacro.input;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.Native;

/**
 * @author bcochrane
 * @since 3/5/14
 */
public class GlobalKeyListener implements NativeKeyListener {

	private ActionListener f5Listener;
	private ActionListener f6Listener;

	public GlobalKeyListener() {
		f5Listener = null;
		f6Listener = null;
	}

	public void addF5Listener(ActionListener listener) {
		f5Listener = listener;
	}

	public void addF6Listener(ActionListener listener) {
		f6Listener = listener;
	}

	public GlobalKeyListener clearListeners() {
		f5Listener = null;
		f6Listener = null;
		return this;
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
		if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VK_F5 && f5Listener != null) {
			f5Listener.actionPerformed(null);
		} else if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VK_F6 && f6Listener != null) {
			f6Listener.actionPerformed(null);
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {

	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

	}
}
