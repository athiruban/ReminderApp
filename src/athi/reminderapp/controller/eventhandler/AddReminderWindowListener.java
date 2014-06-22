package athi.reminderapp.controller.eventhandler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import athi.reminderapp.controller.IOManager;

public class AddReminderWindowListener extends WindowAdapter{
	@Override
	public void windowClosing(WindowEvent arg0) {
		synchronized(this){
			new IOManager().saveReminders();
		}
	}
}