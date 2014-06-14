package athi.reminderapp.eventhandler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import athi.reminderapp.config.AppConfig;
import athi.reminderapp.controller.MyApp;
import athi.reminderapp.model.ReminderList;

public class AddReminderWindowListener extends WindowAdapter{
	@Override
	public void windowClosing(WindowEvent arg0) {
		MyApp.getInstance().saveReminders();
	}
}