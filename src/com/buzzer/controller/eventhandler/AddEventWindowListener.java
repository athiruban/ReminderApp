package com.buzzer.controller.eventhandler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.buzzer.controller.IOManager;

public class AddEventWindowListener extends WindowAdapter {
	@Override
	public void windowClosing(WindowEvent arg0) {
		synchronized (this) {
			new IOManager().saveReminders();
		}
	}
}